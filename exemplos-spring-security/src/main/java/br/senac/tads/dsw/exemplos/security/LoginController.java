/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping
	public String executar(@RequestBody Credencial dadosLogin) {
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						dadosLogin.username, dadosLogin.senha));
        UsuarioSistema usuario = (UsuarioSistema) auth.getPrincipal();
        return usuario.getNomeCompleto() + " " + usuario.getEmail();
	}

	public static record Credencial(String username, String senha) {
	}
	// O record acima equivale a class abaixo:
	/*
	public static class Credencial {

		private final String username;

		private final String senha;

		public Credencial(String username, String senha) {
			this.username = username;
			this.senha = senha;
		}

		public String getUsername() {
			return username;
		}

		public String getSenha() {
			return senha;
		}
	}
	*/

}
