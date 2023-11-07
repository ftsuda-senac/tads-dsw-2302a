package br.senac.tads.dsw.exemplos.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FotoPessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotBlank
    @Size(max = 500)
    // NAO PODE REPETIR
    @Column(unique = true)
	private String nomeArquivo;

	@Size(max = 1000)
	private String legenda;

	@ManyToOne
	@JoinColumn(name = "pessoa_id")
    // @JsonIgnore // Adicionar para evitar loop infinito ao gerar JSON
    private DadosPessoais pessoa;

    public FotoPessoa() {

    }

    public FotoPessoa(String nomeArquivo, String legenda) {
        this.nomeArquivo = nomeArquivo;
        this.legenda = legenda;
    }

}
