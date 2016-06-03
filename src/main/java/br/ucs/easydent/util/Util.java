package br.ucs.easydent.util;

import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;

import br.ucs.easydent.sessionbean.QueryParams;

public class Util {

	public static void setParams(Query query, Map<String, Object> params) {
		for (Entry<String, Object> param : params.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}
	}

	public static void checkPagination(Query query, QueryParams params) {

		if (params.getQuantidadeRegistros() != null) {
			query.setMaxResults(params.getQuantidadeRegistros());
		}
		if (params.getPrimeiroRegistro() != null) {
			query.setFirstResult(params.getPrimeiroRegistro());
		}

	}

}
