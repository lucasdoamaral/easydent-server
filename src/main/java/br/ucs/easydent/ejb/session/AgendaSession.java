package br.ucs.easydent.ejb.session;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

import br.ucs.easydent.ejb.sessionbean.QueryParams;
import br.ucs.easydent.model.entity.Agenda;
import br.ucs.easydent.model.entity.Consulta;

@Remote
public interface AgendaSession extends EntityEJB<Agenda> {

	List<Consulta> buscarConsultasPorData(Agenda agenda, Calendar inicio, Calendar fim,  QueryParams params);
	
}
