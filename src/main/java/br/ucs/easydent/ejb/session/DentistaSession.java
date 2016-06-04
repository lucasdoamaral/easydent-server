package br.ucs.easydent.ejb.session;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Dentista;

@Remote
public interface DentistaSession  extends EntityEJB<Dentista> {

}
