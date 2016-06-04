package br.ucs.easydent.ejb.session;

import java.util.List;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Consulta;
import br.ucs.easydent.model.entity.Dentista;

@Remote
public interface ConsultaSession  extends EntityEJB<Consulta> {
	
	List<Consulta> buscarProximasConsultas(Dentista dentista, Integer proximas);

	List<Consulta> buscarProximasConsultas(Integer proximas);
}
