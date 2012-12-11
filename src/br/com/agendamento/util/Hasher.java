package br.com.agendamento.util;

import java.security.MessageDigest;

/**
 * @author lucasmachado
 * 
 */
public class Hasher {

	/**
	 * Número de iterações de hash
	 */
	private static final int iterationNb = 5;

	private static final String salt = "sistemaAGENDAMENTO";

	/**
	 * Faz o hash de uma dada String, utilizando salt para maior segurança (Salt
	 * desativado e estático porque seria necessário mudanças futuras na
	 * interface) FIXME
	 * 
	 * @param value
	 *            valor a se fazer hash
	 * @param inactivessalt
	 *            ingrediente para hash
	 * @return o hash
	 */
	public static String hash(String value, String inactivessalt) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt.getBytes("UTF-8"));
			byte[] input = digest.digest(value.getBytes("UTF-8"));
			for (int i = 0; i < iterationNb; i++) {
				digest.reset();
				input = digest.digest(input);
			}
			return new String(input);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
