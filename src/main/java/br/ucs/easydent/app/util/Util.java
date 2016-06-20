package br.ucs.easydent.app.util;

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

}
