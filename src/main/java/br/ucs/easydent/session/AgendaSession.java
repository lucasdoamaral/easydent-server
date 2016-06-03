package br.ucs.easydent.session;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import br.ucs.easydent.sessionbean.QueryParams;
import br.ucs.easydental.model.Agenda;
import br.ucs.easydental.model.Consulta;

@Remote
public interface AgendaSession extends EntityEJB<Agenda> {

	List<Consulta> buscarConsultasPorData(Agenda agenda, Calendar inicio, Calendar fim,  QueryParams params);
	
}
