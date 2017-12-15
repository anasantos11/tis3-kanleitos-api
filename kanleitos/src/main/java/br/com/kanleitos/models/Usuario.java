package br.com.kanleitos.models;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.kanleitos.util.CriptografarSenha;

@Entity
public class Usuario {

	@Id
	@SequenceGenerator(name="USUARIO_ID",  initialValue=1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="USUARIO_ID")
	private long idUsario;

	@NotEmpty
	@Column(name = "login", nullable = false)
	private String login;
	
	@NotEmpty
	@Column(name = "senha", nullable = false)
	private String senha;

	@Column(name = "inativo", nullable = false)
	private boolean inativo;

	public Usuario() {
		setLogin(null);
		setSenha(null);
		setInativo(false);
	}
	
	private static class UsuarioKeys {

		private static final String LOGIN = "login";
		private static final String SENHA = "senha";
		private static final String INATIVO = "inativo";

	}

	public Usuario(JSONObject json) throws JSONException {

		if (json.has(UsuarioKeys.LOGIN))
			setLogin(json.getString(UsuarioKeys.LOGIN));

		if (json.has(UsuarioKeys.SENHA))
			setSenha(json.getString(UsuarioKeys.SENHA));
		
		if (json.has(UsuarioKeys.INATIVO))
			setInativo(json.getBoolean(UsuarioKeys.INATIVO));

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

	public boolean isInativo() {
		return inativo;
	}

	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}

	public long getIdUsario() {
		return idUsario;
	}
	
	public void encodeSenha() {
		try {
			setSenha(CriptografarSenha.encode(senha));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
