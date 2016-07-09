package br.ucs.easydent.ejb.session;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.Remote;

import br.ucs.easydent.model.entity.Dentista;
import br.ucs.easydent.model.entity.Usuario;

@Remote
public interface DentistaSession  extends EntityEJB<Dentista> {

	List<String> buscarHorariosDisponiveis(Usuario usuario, Dentista dentista, LocalDate data);

}
