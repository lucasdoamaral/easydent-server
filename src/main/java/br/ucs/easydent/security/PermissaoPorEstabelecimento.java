package br.ucs.easydent.security;

import java.util.List;

import br.ucs.easydent.model.entity.Estabelecimento;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.enums.TipoUsuarioEnum;
import br.ucs.easydent.model.intf.EntidadeComEstabelecimento;

public class PermissaoPorEstabelecimento {

	public static void checkIfCanGetsEntity(Usuario usuario, EntidadeComEstabelecimento entidade) {

		if (TipoUsuarioEnum.ADMIN.equals(usuario.getTipoUsuarioEnum())) {
			return;
		}

		if (!usuario.getEstabelecimento().equals(entidade.getEstabelecimento())) {
			throw new RuntimeException("!!! SECURITY PROBLEM !!!");
		}
	}

	public static void checkIfCanGetsEntity(Usuario usuario, List<? extends EntidadeComEstabelecimento> entidades) {

		if (TipoUsuarioEnum.ADMIN.equals(usuario.getTipoUsuarioEnum())) {
			return;
		}

		Estabelecimento estabelecimentoUsuario = usuario.getEstabelecimento();
		for (EntidadeComEstabelecimento entidade : entidades) {
			if (!estabelecimentoUsuario.equals(entidade.getEstabelecimento())) {
				throw new RuntimeException("!!! SECURITY PROBLEM !!!");
			}
		}
	}

}
