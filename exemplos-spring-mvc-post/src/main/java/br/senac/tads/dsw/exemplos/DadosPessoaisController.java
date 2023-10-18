/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/dados-pessoais")
public class DadosPessoaisController {

	@Autowired
	private DadosPessoaisService service;
	
	@GetMapping
	public List<DadosPessoais> findAll() {
		return service.findAll();
	}

	@GetMapping("/busca")
	public List<DadosPessoais> buscar(
			@RequestParam("termo") String termoBusca,
			@RequestParam(name = "nascimento", required = false) LocalDate dataNascimento,
			@RequestParam(defaultValue = "1") int pagina,
			@RequestParam(defaultValue = "1") int quantidade) {
		System.out.println("Data nascimento: " + dataNascimento);
		return service.buscar(termoBusca, pagina, quantidade);
	}

	@GetMapping("/{id}")
	public DadosPessoais findById(@PathVariable Integer id) {
		DadosPessoais dados = service.findById(id);
		if (dados != null) {
			return dados;
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "ID " + id + " não encontrado");
		}
	}

	@GetMapping("/{id}/opt")
	public DadosPessoais findByIdComOptional(@PathVariable Integer id) {
//		Optional<DadosPessoais> optDadosPessoais = service.findByIdComOptional(id);
//		if (optDadosPessoais.isPresent()) {
//			return optDadosPessoais.get();
//		} else {
//			throw new ResponseStatusException(
//					HttpStatus.NOT_FOUND, "ID " + id + " não encontrado");
//		}
		return service.findByIdComOptional(id).orElseThrow(
				() -> new ResponseStatusException(
					HttpStatus.NOT_FOUND, "ID " + id + " não encontrado"));
	}

	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody @Valid DadosPessoais dados) {
		System.out.printf("**** " + dados.toString());
		service.save(dados);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dados.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

}
