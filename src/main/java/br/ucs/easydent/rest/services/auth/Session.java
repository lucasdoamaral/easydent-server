package br.ucs.easydent.rest.services.auth;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Session {

	private static Map<String, Token> tokens = new HashMap<>();

	public static void storeToken(Token token) {
		tokens.put(token.getValue(), token);
	}

	public static void revalidateToken(String tokenValue) {
		Token token = tokens.get(tokenValue);
		token.revalidate(Calendar.getInstance());
	}

	public static void removeToken(String tokenValue) {
		tokens.remove(tokenValue);
	}

	public static Token getToken(String tokenValue) {
		return tokens.get(tokenValue);
	}

}
