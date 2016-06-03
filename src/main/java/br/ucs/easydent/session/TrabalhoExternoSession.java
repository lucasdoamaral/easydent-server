package br.ucs.easydent.session;

import javax.ejb.Remote;

import br.ucs.easydental.model.TrabalhoExterno;

@Remote
public interface TrabalhoExternoSession extends EntityEJB<TrabalhoExterno> {

}
