package br.ucs.easydent.rest.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import br.ucs.easydent.rest.services.entity.AgendaService;
import br.ucs.easydent.rest.services.entity.ConsultaService;
import br.ucs.easydent.rest.services.entity.DentistaService;
import br.ucs.easydent.rest.services.entity.EspecialidadeService;
import br.ucs.easydent.rest.services.entity.EstabelecimentoService;
import br.ucs.easydent.rest.services.entity.LaboratorioService;
import br.ucs.easydent.rest.services.entity.PacienteService;
import br.ucs.easydent.rest.services.entity.TipoProcedimentoService;
import br.ucs.easydent.rest.services.entity.TrabalhoExternoService;

//@ApplicationPath("/rest")
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
