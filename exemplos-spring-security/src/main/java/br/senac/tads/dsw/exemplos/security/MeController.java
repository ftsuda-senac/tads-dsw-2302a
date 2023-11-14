/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeController {

	@Autowired
	private UsuarioSistemaService usuarioSistemaService;

	@GetMapping("/me")
	public UserInfo findUserInfo(Authentication auth) {
		Jwt jwt = (Jwt) auth.getPrincipal();
		String username = jwt.getSubject();
		UsuarioSistema us = usuarioSistemaService.loadUserByUsername(username);
		return new UserInfo(us.getNomeCompleto(), us.getEmail(), us.getHashSenha(),
				"http://localhost:8080/img/" + us.getFoto());

	}

	public static record UserInfo(
			String nome,
			String email,
			String hashSenha,
			String urlFoto) {
	}

}
