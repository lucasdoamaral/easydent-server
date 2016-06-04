package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.app.exceptions.FalhaLoginException;
import br.ucs.easydent.model.entity.Usuario;

@Remote
public interface LoginSession {

	Usuario login(String usuario, String senha) throws FalhaLoginException; 
	
}
