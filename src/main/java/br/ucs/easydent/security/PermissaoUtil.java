package br.ucs.easydent.security;

import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.enums.TipoUsuarioEnum;
import br.ucs.easydent.security.TipoPermissao.PesquisaPaciente;

public class PermissaoUtil {

	public static TipoPermissao.PesquisaPaciente getTipoPesquisaPaciente(Usuario usuario) {
		TipoUsuarioEnum tipoUsuarioEnum = usuario.getTipoUsuarioEnum();
		switch (tipoUsuarioEnum) {
		case ADMIN:
			return PesquisaPaciente.TODOS;
		case GERENTE:
		case SECRETARIA:
			return PesquisaPaciente.POR_ESTABELECIMENTO;
		case DENTISTA:
			return PesquisaPaciente.POR_DENTISTA;
		case PACIENTE:
			return PesquisaPaciente.PROPRIO;
		default:
			return PesquisaPaciente.NENHUM;
		}
	}

}
