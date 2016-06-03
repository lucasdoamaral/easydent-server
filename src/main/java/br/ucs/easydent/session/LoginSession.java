package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydent.FalhaLoginException;
import br.ucs.easydental.model.Usuario;

@Remote
public interface LoginSession {

	Usuario login(String usuario, String senha) throws FalhaLoginException; 
	
}
