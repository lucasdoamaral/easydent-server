package br.ucs.easydent.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.ucs.easydent.rest.entity.AgendaService;
import br.ucs.easydent.rest.entity.ConsultaService;
import br.ucs.easydent.rest.entity.DentistaService;
import br.ucs.easydent.rest.entity.EspecialidadeService;
import br.ucs.easydent.rest.entity.EstabelecimentoService;
import br.ucs.easydent.rest.entity.LaboratorioService;
import br.ucs.easydent.rest.entity.PacienteService;
import br.ucs.easydent.rest.entity.TipoProcedimentoService;
import br.ucs.easydent.rest.entity.TrabalhoExternoService;

public class RestApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		
		classes.add(AgendaService.class);
		classes.add(ConsultaService.class);
		classes.add(DentistaService.class);
		classes.add(EspecialidadeService.class);
		classes.add(EstabelecimentoService.class);
		classes.add(LaboratorioService.class);
		classes.add(TipoProcedimentoService.class);
		classes.add(TrabalhoExternoService.class);
		classes.add(PacienteService.class);
		
		return classes;
		
	}
	
}
