package com.estudp.resumo_mensagem.codigo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author Majú Testoni
 */
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ds_login", length = 50)
	private String login;
	@Column(name = "ds_senha", length = 200)
	private String senha;


	public UsuarioEntity() {
	}

	@Override
	public String toString() {
		return "UsuarioEntity{" + "id=" + id + ", login='" + login + '\'' + ", senha='" + senha + '\'' + '}';
	}

	// GET E SETTER
	public Long getId() {
		return id;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
