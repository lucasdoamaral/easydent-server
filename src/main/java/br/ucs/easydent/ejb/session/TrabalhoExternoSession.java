package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.TrabalhoExterno;

@Remote
public interface TrabalhoExternoSession extends EntityEJB<TrabalhoExterno> {

}
