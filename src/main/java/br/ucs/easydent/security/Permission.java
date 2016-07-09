package br.ucs.easydent.security;

import java.util.Arrays;

import br.ucs.easydent.app.exceptions.ProblemaPermissaoException;
import br.ucs.easydent.model.entity.Usuario;
import br.ucs.easydent.model.enums.TipoUsuarioEnum;

public enum Permission {

	LABORATORIO_BUSCAR ("Buscar laboratório", new TipoUsuarioEnum[] {TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.GERENTE, TipoUsuarioEnum.DENTISTA, TipoUsuarioEnum.SECRETARIA}),
	LABORATORIO_LISTAR ("Listar laboratórios", new TipoUsuarioEnum[] {TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.GERENTE, TipoUsuarioEnum.DENTISTA, TipoUsuarioEnum.SECRETARIA}),
	LABORATORIO_CRIAR ("Criar laboratório", new TipoUsuarioEnum[] {TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.GERENTE, TipoUsuarioEnum.DENTISTA, TipoUsuarioEnum.SECRETARIA}),
	LABORATORIO_ALTERAR ("Alterar laboratório", new TipoUsuarioEnum[] {TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.GERENTE}),
	LABORATORIO_EXCLUIR ("Excluir laboratório", new TipoUsuarioEnum[] {TipoUsuarioEnum.ADMIN, TipoUsuarioEnum.GERENTE })

	;

	private TipoUsuarioEnum[] permitidos;
	private String nome;

	private Permission(String nome, TipoUsuarioEnum[] permitidos) {
		this.nome = nome;
		this.permitidos = permitidos;
	}

	public void check(Usuario usuario) throws ProblemaPermissaoException {
		check(usuario.getTipoUsuarioEnum());
	}

	public void check(TipoUsuarioEnum tipoUsuario) throws ProblemaPermissaoException {
		if (!Arrays.asList(permitidos).contains(tipoUsuario)) {
			throw new ProblemaPermissaoException("Você não tem permissão para a ação [" + getNome() + "]");
		}
	}

	public String getNome() {
		return nome;
	}
}
