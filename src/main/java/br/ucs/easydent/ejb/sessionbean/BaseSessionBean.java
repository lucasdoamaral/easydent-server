package br.ucs.easydent.ejb.sessionbean;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ucs.easydent.app.Application;

@Stateless
public class BaseSessionBean {

	@PersistenceContext(unitName = Application.PERSISTENCE_UNIT)
	protected EntityManager em;

	@Resource
	protected SessionContext context;

}
