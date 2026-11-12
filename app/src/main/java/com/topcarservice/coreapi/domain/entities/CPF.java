package com.topcarservice.coreapi.domain.entities;

import java.util.regex.Pattern;

public class CPF {
	
	private static final String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
	
	private String numero;
	
	public CPF(String numero) {
		if (!validarCPF(numero)) {
			throw new IllegalArgumentException("CPF inválido: " + numero);
		}
		this.numero = numero;
	}
	
	private boolean validarCPF(String numero) {		
		return Pattern.matches(CPF_REGEX, numero); 
	}
	
	public static String formatarCPF(String numero) {
		/*
		 * if (numero == null || numero.length() != 11) { throw new
		 * IllegalArgumentException("Número de CPF deve conter 11 dígitos."); }
		 */
		return String.format("%s.%s.%s-%s", 
				numero.substring(0, 3), 
				numero.substring(3, 6), 
				numero.substring(6, 9), 
				numero.substring(9, 11));
	}

	@Override
	public String toString() {
		return numero;
	}
	
	public String getUnformatted() {
		return numero.replaceAll("\\D+", "");
	}
	
	

}
