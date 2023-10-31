
package br.senac.tads.dsw.exemplos;

import java.util.List;
import java.util.Optional;

public interface DadosPessoaisService {

	List<DadosPessoaisDto> findAll();

	List<DadosPessoaisDto> buscar(String termoBusca, int pagina, int quantidade);

	DadosPessoaisDto findById(int id);

	Optional<DadosPessoaisDto> findByIdComOptional(int id);

	void save(DadosPessoaisDto dados);

}
