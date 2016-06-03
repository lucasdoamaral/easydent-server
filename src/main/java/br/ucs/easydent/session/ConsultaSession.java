package br.ucs.easydent.session;

import java.util.List;

import javax.ejb.Remote;

import br.ucs.easydental.model.Consulta;
import br.ucs.easydental.model.Dentista;

@Remote
public interface ConsultaSession  extends EntityEJB<Consulta> {
	
	List<Consulta> buscarProximasConsultas(Dentista dentista, Integer proximas);

	List<Consulta> buscarProximasConsultas(Integer proximas);
}
