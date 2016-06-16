package br.ucs.easydent.rest.services;

import java.util.Base64;
import java.util.Calendar;

public class Token {

	private static final int MINUTOS_VALIDADE = 120 * 8;
	private static final int MILISEGUNDOS_EM_MINUTO = 60000;

	private String value;
	private Calendar expireDate;

	private String username;

	public Token(String username) {
		this.username = username;
		generate();
	}

	public String getValue() {
		return value;
	}
	
	public String getUsername() {
		return username;
	}

	public Calendar getExpireDate() {
		return expireDate;
	}

	public void revalidate(Calendar data) {
		this.expireDate = getExpireDate(data);
	}

	private void generate() {

		Calendar data = Calendar.getInstance();

		String dateText = new Long(data.getTimeInMillis()).toString();
		String randomText = new Double(Math.random() * 99999).toString().replace(".", "");

		String value = randomText + "." + username + "." + dateText + "." + randomText;
		this.value = Base64.getEncoder().encodeToString(value.getBytes());

		this.expireDate = getExpireDate(data);
	}

	private Calendar getExpireDate(Calendar data) {
		Calendar _data = Calendar.getInstance();
		_data.setTimeInMillis(data.getTimeInMillis() + (MILISEGUNDOS_EM_MINUTO * MINUTOS_VALIDADE));
		return _data;
	}

	public boolean isValid() {
		return Calendar.getInstance().before(expireDate);
	}

}
