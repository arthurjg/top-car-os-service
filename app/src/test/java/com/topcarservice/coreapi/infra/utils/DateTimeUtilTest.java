package com.topcarservice.coreapi.infra.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DateTimeUtilTest {

	@Test
	void testGetByLocalDateTime() {
		
		Date result = DateTimeUtil.getByLocalDateTime(LocalDateTime.now());
		
		assertNotNull(result);	
	}

	@Test
	void testFormatarDataHora() {
		String result = DateTimeUtil.formatarDataHora(LocalDateTime.now());
		
		assertNotNull(result);
	}

}
