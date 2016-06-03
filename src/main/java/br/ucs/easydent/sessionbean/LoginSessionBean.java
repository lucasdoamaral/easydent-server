package br.ucs.easydent.sessionbean;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.ucs.easydent.FalhaLoginException;
import br.ucs.easydent.session.LoginSession;
import br.ucs.easydental.model.Usuario;

@Stateless
public class LoginSessionBean extends BaseSessionBean implements LoginSession {

	@Override
	public Usuario login(String usuario, String senha) throws FalhaLoginException {

		Query query = em.createQuery("SELECT e FROM Usuario AS e WHERE e.login= :usuario AND e.senha = :senha ");
		query.setParameter("usuario", usuario);
		query.setParameter("senha", senha);

		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			throw new FalhaLoginException("Usuário [" + usuario + "] não encontrado.");
		}
	}

}
