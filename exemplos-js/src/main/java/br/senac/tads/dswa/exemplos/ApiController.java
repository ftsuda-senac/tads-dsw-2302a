/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dswa.exemplos;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class ApiController {

	@GetMapping("/rest")
	public String acessarExemplo() {
		return "{ \"titulo\": \"Exemplo REST\" }";
	}

	@GetMapping("/dados")
	public DadosDto acessarDados() {
		DadosDto dados = new DadosDto(
			"João da Silva",
			"joao@teste.com.br",
			"(11) 99119-0001",
			LocalDate.parse("2000-10-20"),
			"https://linkein.com/joaosilva",
			"https://github.com/joaosilva");
		return dados;
	}



}
