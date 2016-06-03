package br.ucs.easydental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.ucs.easydental.enums.TipoUsuarioEnum;
import br.ucs.easydental.model.intf.Entidade;

@Entity
public class Usuario implements Entidade {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "usuario", sequenceName = "usuario_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
	private Long id;

	private String email;

	private String login;

	private String senha;

	private String nome;

	private Integer fgTipoUsuario;

	public Usuario() {
		// TODO Auto-gene rated constructor stub
	}

	public TipoUsuarioEnum getTipoUsuarioEnum() {
		return TipoUsuarioEnum.getById(fgTipoUsuario);
	}

	public void setTipoUsuarioEnum(TipoUsuarioEnum tipoUsuarioEnum) {
		fgTipoUsuario = tipoUsuarioEnum != null ? tipoUsuarioEnum.getId() : null;
	}

	@Override
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFgTipoUsuario() {
		return fgTipoUsuario;
	}

	public void setFgTipoUsuario(Integer fgTipoUsuario) {
		this.fgTipoUsuario = fgTipoUsuario;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}