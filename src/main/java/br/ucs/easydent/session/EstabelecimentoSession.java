package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.Estabelecimento;

@Remote
public interface EstabelecimentoSession  extends EntityEJB<Estabelecimento> {

}
