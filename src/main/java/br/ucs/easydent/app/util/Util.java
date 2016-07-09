package br.ucs.easydent.app.util;

import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import br.ucs.easydent.ejb.sessionbean.Options;

public class Util {

	public static void setParams(Query query, Map<String, Object> params) {
		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
	}

	public static void checkPagination(Query query, Options params) {

		if (params.getQuantidadeRegistros() != null) {
			query.setMaxResults(params.getQuantidadeRegistros());
		}
		if (params.getPrimeiroRegistro() != null) {
			query.setFirstResult(params.getPrimeiroRegistro());
		}

	}

	public static Calendar newCalendar(Integer year, Integer month, Integer day) {
		return new Calendar.Builder().set(Calendar.YEAR, year).set(Calendar.MONTH, month)
				.set(Calendar.DAY_OF_MONTH, day).build();
	}

	/**
	 * Sets the hour, minute and seconds for the beginning of the day.
	 */
	public static void beginOfTheDay(Calendar calendar) {
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}

	/**
	 * Sets the hour, minute and seconds for the end of the day.
	 */
	public static void endOfTheDay(Calendar calendar) {
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
	}

}
