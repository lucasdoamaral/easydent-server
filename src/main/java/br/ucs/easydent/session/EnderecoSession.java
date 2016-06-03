package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Endereco;

@Remote
public interface EnderecoSession  extends EntityEJB<Endereco> {

}
