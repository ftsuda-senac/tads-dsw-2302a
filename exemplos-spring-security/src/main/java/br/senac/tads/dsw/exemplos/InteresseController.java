/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import br.senac.tads.dsw.exemplos.dominio.Interesse;
import br.senac.tads.dsw.exemplos.dominio.InteresseRepository;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/interesses")
public class InteresseController {

	@Autowired
	private InteresseRepository repo;

	@GetMapping
	public List<Interesse> findAll() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Interesse findById(@PathVariable("id") Integer id) {
		return repo.findById(id).orElseThrow(() ->
				new ResponseStatusException(
					HttpStatus.NOT_FOUND, "ID " + id + " não encontrado"));
	}

	@PostMapping
	public ResponseEntity<?> addNew(@RequestBody Interesse interesse) {
		interesse.setId(null);
		repo.save(interesse);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(interesse.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Interesse interesse) {
		repo.findById(id).orElseThrow(() ->
			new ResponseStatusException(
				HttpStatus.NOT_FOUND, "ID " + id + " não encontrado"));
		interesse.setId(id);
		repo.save(interesse);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
		repo.findById(id).orElseThrow(() ->
			new ResponseStatusException(
				HttpStatus.NOT_FOUND, "ID " + id + " não encontrado"));
		repo.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
