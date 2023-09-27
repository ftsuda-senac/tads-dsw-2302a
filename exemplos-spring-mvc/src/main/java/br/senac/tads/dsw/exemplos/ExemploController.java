/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExemploController {
	
	@GetMapping("/exemplo01")
	public String exemplo01() {
		return "Exemplo de GET no Controller";
	}

	@GetMapping("/exemplo02")
	public String exemplo02() {
		return "{ \"nome\": \"João da Silva\", \"email\": \"joao@teste.com.br\" }";
	}

	@GetMapping("/exemplo03")
	public DadosPessoais exemplo03() {
		DadosPessoais dados = new DadosPessoais(1, "José Severino",
				"jose@teste.com.br", "(11) 99999-1234",
				LocalDate.parse("2002-06-27"));
		return dados;
	}

	@GetMapping("/exemplo04")
	public DadosPessoais[] exemplo04() {
		DadosPessoais d1 = new DadosPessoais(2, "Fulano da Silva", "fulano@teste.com.br",
		"(11) 99991-0022", LocalDate.parse("2000-10-20"));
		DadosPessoais d2 = new DadosPessoais(3, "Ciclano de Souza", "ciclano@teste.com.br",
		"(11) 99992-9876", LocalDate.parse("1999-05-20"));
		DadosPessoais d3 = new DadosPessoais(4, "Beltrana dos Santos", "beltrana@teste.com.br",
		"(11) 99993-1122", LocalDate.parse("2001-02-15"));

		DadosPessoais[] arr = new DadosPessoais[3];
		arr[0] = d1;
		arr[1] = d2;
		arr[2] = d3;
		return arr;
	}

	@GetMapping("/exemplo04b")
	public List<DadosPessoais> exemplo04b() {
		DadosPessoais d1 = new DadosPessoais(2, "Fulano da Silva", "fulano@teste.com.br",
		"(11) 99991-0022", LocalDate.parse("2000-10-20"));
		DadosPessoais d2 = new DadosPessoais(3, "Ciclano de Souza", "ciclano@teste.com.br",
		"(11) 99992-9876", LocalDate.parse("1999-05-20"));
		DadosPessoais d3 = new DadosPessoais(4, "Beltrana dos Santos", "beltrana@teste.com.br",
		"(11) 99993-1122", LocalDate.parse("2001-02-15"));

		List<DadosPessoais> resultado = new ArrayList<>();
		resultado.add(d1);
		resultado.add(d2);
		resultado.add(d3);
		return resultado;
	}

	@GetMapping("/exemplo05")
	public String exemplo05(@RequestHeader("user-agent") String userAgent) {
        String mensagem = "Acesso via dispositivo movel";
        if (!userAgent.toLowerCase().contains("mobile")) {
            mensagem = "Acesso via desktop";
        }
		return mensagem;
	}

}
