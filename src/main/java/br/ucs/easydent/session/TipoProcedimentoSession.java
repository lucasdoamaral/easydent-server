package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.TipoProcedimento;

@Remote
public interface TipoProcedimentoSession  extends EntityEJB<TipoProcedimento> {

}
