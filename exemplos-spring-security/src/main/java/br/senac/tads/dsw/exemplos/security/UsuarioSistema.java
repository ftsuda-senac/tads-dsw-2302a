/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos.security;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioSistema implements UserDetails {

	private String username;

	private String nomeCompleto;

	private String email;

	private String foto;

	private String hashSenha;

	private List<Papel> papeis;

	public UsuarioSistema() {

	}

	public UsuarioSistema(String username, String nomeCompleto, String email, String foto, String hashSenha, List<Papel> papeis) {
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.foto = foto;
		this.hashSenha = hashSenha;
		this.papeis = papeis;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getHashSenha() {
		return hashSenha;
	}

	public void setHashSenha(String hashSenha) {
		this.hashSenha = hashSenha;
	}

	public List<Papel> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<Papel> papeis) {
		this.papeis = papeis;
	}

	@Override
	// public Collection<? extends GrantedAuthority> getAuthorities() {
	public List<Papel> getAuthorities() {
		return papeis;
	}

	@Override
	public String getPassword() {
		return hashSenha;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


}
