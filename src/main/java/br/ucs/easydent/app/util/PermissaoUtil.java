package br.ucs.easydent.app.util;

import br.ucs.easydent.app.util.Permissao.TipoPesquisaPaciente;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.enums.TipoUsuarioEnum;

public class PermissaoUtil {

	public static Permissao.TipoPesquisaPaciente getTipoPesquisaPaciente(Usuario usuario) {
		TipoUsuarioEnum tipoUsuarioEnum = usuario.getTipoUsuarioEnum();
		switch (tipoUsuarioEnum) {
		case ADMIN:
			return TipoPesquisaPaciente.TODOS;
		case GERENTE:
		case SECRETARIA:
			return TipoPesquisaPaciente.POR_ESTABELECIMENTO;
		case DENTISTA:
			return TipoPesquisaPaciente.POR_DENTISTA;
		case PACIENTE:
			return TipoPesquisaPaciente.PROPRIO;
		default:
			return TipoPesquisaPaciente.NENHUM;
		}
	}

}
