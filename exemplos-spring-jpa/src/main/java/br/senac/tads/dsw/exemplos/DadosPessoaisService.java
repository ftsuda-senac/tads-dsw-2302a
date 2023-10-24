
package br.senac.tads.dsw.exemplos;

import java.util.List;
import java.util.Optional;

public interface DadosPessoaisService {

	List<DadosPessoais> findAll();

	List<DadosPessoais> buscar(String termoBusca, int pagina, int quantidade);

	DadosPessoais findById(int id);

	Optional<DadosPessoais> findByIdComOptional(int id);

	void save(DadosPessoais dados);

}
