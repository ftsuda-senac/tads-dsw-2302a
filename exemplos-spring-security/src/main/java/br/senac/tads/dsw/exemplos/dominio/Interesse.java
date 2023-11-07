package br.senac.tads.dsw.exemplos.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Interesse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, unique = true, nullable = false)
	private String nome;

	@ManyToMany(mappedBy = "interesses")
	@JsonIgnore
	private Set<DadosPessoais> pessoas;

	public Interesse() {
	}

	public Interesse(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Interesse(String nome) {
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<DadosPessoais> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<DadosPessoais> pessoas) {
		this.pessoas = pessoas;
	}

}
