package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Estabelecimento;

@Remote
public interface EstabelecimentoSession extends EntityEJB<Estabelecimento> {

}
