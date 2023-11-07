/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos.security;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSistemaService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, UsuarioSistema> mapUsuarios;

    @PostConstruct
    public void init() {
        mapUsuarios = new HashMap<>();
        mapUsuarios.put("luke", new UsuarioSistema("luke",
                "Luke Skywalker",
                "luke@starwars.com.br", "luke.jpg", passwordEncoder.encode("Abcd1234"),
                Arrays.asList(new Papel("PADAWAN"), new Papel("JEDI"))));
        mapUsuarios.put("vader", new UsuarioSistema("vader",
                "Darth Vader",
                "vader@starwars.com.br", "vader.jpg", passwordEncoder.encode("Abcd1234"),
                Arrays.asList(new Papel("LORD_SITH"))));
        mapUsuarios.put("kenobi", new UsuarioSistema("kenobi",
                "Obi Wan Kenobi",
                "kenobi@starwars.com.br", "kenobi.jpg", passwordEncoder.encode("Abcd1234"),
                Arrays.asList(new Papel("MESTRE"), new Papel("JEDI"))));
    }

	@Override
	public UsuarioSistema loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioSistema usuario = mapUsuarios.get(username);
		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}
		return usuario;
	}

}
