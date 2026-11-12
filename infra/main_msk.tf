############################
# Security Group
############################
resource "aws_security_group" "msk" {
  name        = "${var.cluster_name}-sg"
  description = "Acesso ao cluster MSK"
  vpc_id      = var.vpc_id

  tags = { Name = "${var.cluster_name}-sg" }
}

# 9098 = SASL/IAM sobre TLS | 9094 = TLS
resource "aws_vpc_security_group_ingress_rule" "from_sg" {
  for_each = {
    for pair in setproduct(var.allowed_security_group_ids, [9094, 9098]) :
    "${pair[0]}-${pair[1]}" => pair
  }

  security_group_id            = aws_security_group.msk.id
  referenced_security_group_id = each.value[0]
  from_port                    = each.value[1]
  to_port                      = each.value[1]
  ip_protocol                  = "tcp"
  description                  = "Kafka ${each.value[1]} a partir de SG autorizado"
}

resource "aws_vpc_security_group_ingress_rule" "from_cidr" {
  for_each = {
    for pair in setproduct(var.msk_allowed_cidr_blocks, [9094, 9098]) :
    "${pair[0]}-${pair[1]}" => pair
  }

  security_group_id = aws_security_group.msk.id
  cidr_ipv4         = each.value[0]
  from_port         = each.value[1]
  to_port           = each.value[1]
  ip_protocol       = "tcp"
  description       = "Kafka ${each.value[1]} a partir de CIDR autorizado"
}

# Comunicação entre brokers
resource "aws_vpc_security_group_ingress_rule" "self" {
  security_group_id            = aws_security_group.msk.id
  referenced_security_group_id = aws_security_group.msk.id
  ip_protocol                  = "-1"
  description                  = "Trafego interno entre brokers"
}

resource "aws_vpc_security_group_egress_rule" "all" {
  security_group_id = aws_security_group.msk.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1"
}

############################
# KMS (criptografia em repouso)
############################
resource "aws_kms_key" "msk" {
  description             = "Chave KMS do cluster ${var.cluster_name}"
  enable_key_rotation     = true
  deletion_window_in_days = 7
}

resource "aws_kms_alias" "msk" {
  name          = "alias/${var.cluster_name}"
  target_key_id = aws_kms_key.msk.key_id
}

############################
# Logs
############################
resource "aws_cloudwatch_log_group" "msk" {
  name              = "/aws/msk/${var.cluster_name}"
  retention_in_days = var.log_retention_days
}

############################
# Configuração do Kafka
############################
resource "aws_msk_configuration" "this" {
  name           = "${var.cluster_name}-config"
  kafka_versions = [var.kafka_version]

  server_properties = <<-PROPERTIES
    auto.create.topics.enable=true
    delete.topic.enable=true
    default.replication.factor=3
    min.insync.replicas=2
    num.partitions=3
    log.retention.hours=168
  PROPERTIES
}

############################
# Cluster MSK
############################
resource "aws_msk_cluster" "this" {
  cluster_name           = var.cluster_name
  kafka_version          = var.kafka_version
  number_of_broker_nodes = var.number_of_broker_nodes

  broker_node_group_info {
    instance_type   = var.broker_instance_type
    client_subnets  = var.subnet_ids
    security_groups = [aws_security_group.msk.id]

    storage_info {
      ebs_storage_info {
        volume_size = var.broker_ebs_volume_size
      }
    }
  }

  configuration_info {
    arn      = aws_msk_configuration.this.arn
    revision = aws_msk_configuration.this.latest_revision
  }

  client_authentication {
    unauthenticated = false

    sasl {
      iam = true
    }
  }

  encryption_info {
    encryption_at_rest_kms_key_arn = aws_kms_key.msk.arn

    encryption_in_transit {
      client_broker = "TLS"
      in_cluster    = true
    }
  }

  enhanced_monitoring = "PER_BROKER"

  open_monitoring {
    prometheus {
      jmx_exporter  { enabled_in_broker = true }
      node_exporter { enabled_in_broker = true }
    }
  }

  logging_info {
    broker_logs {
      cloudwatch_logs {
        enabled   = true
        log_group = aws_cloudwatch_log_group.msk.name
      }
    }
  }

  tags = { Name = var.cluster_name }
}
