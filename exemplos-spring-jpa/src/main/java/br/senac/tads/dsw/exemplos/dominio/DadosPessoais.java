
package br.senac.tads.dsw.exemplos.dominio;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DadosPessoais {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100, nullable = false)
	private String nome;

	@Column(length = 64, nullable = false, unique = true)
	private String apelido;

	@Column(length = 200, nullable = false)
	private String email;

	@Column(length = 16)
	private String telefone;

	@Column(nullable = false, length = 100)
	private String hashSenha;

	private LocalDate dataNascimento;

	@ManyToMany
	@JoinTable(name = "pessoa_interesse",
			joinColumns = @JoinColumn(name = "pessoa_id"),
			inverseJoinColumns = @JoinColumn(name = "interesse_id"))
	private Set<Interesse> interesses;

    // "pessoa" é o nome do atributo na classe FotoPessoa
    // onde o @ManyToOne foi configurado - associação bidirecional
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa",
            cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Set<FotoPessoa> fotos;

    @Transient
    private String arquivoFoto;

    @Transient
    private List<Integer> interessesIds;

    public DadosPessoais(String nome, String apelido, String descricao,
            String dataNascimentoStr, String email, String telefone, String senha,
            int numero, String alturaStr, String pesoStr, int genero,
            List<Integer> interessesIds, String arquivoFoto, String urlFotoGerada) {
        this.nome = nome;
        this.apelido = apelido;
        this.dataNascimento = LocalDate.parse(dataNascimentoStr);
        this.email = email;
        this.telefone = telefone;
        this.hashSenha = senha;
        this.interessesIds = interessesIds;
        this.arquivoFoto = arquivoFoto;
    }

}
