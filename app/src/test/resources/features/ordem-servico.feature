# language: pt
Funcionalidade: Cadastro de Ordens de Serviço

  Cenário: Atualizar status da Ordens de Serviço com sucesso
    Dado que existe uma Ordem de Serviço cadastrada com status "Recebido"
    Quando eu atualizo o status para "Em_diagnostico"
    Então a Ordem de Serviço deve estar na listagem com status atualizado
