package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Endereco;

@Remote
public interface EnderecoSession  extends EntityEJB<Endereco> {

}
