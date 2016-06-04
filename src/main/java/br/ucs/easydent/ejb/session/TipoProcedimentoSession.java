package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.TipoProcedimento;

@Remote
public interface TipoProcedimentoSession  extends EntityEJB<TipoProcedimento> {

}
