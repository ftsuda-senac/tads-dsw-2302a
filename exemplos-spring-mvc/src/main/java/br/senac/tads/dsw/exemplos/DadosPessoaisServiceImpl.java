/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DadosPessoaisServiceImpl implements DadosPessoaisService {

	private Map<Integer, DadosPessoais> mapDados = new LinkedHashMap<>();

	@PostConstruct
	public void init() {
		mapDados.put(1, new DadosPessoais(1, "Fulano da Silva", "fulano@teste.com.br",
		"(11) 99991-0022", LocalDate.parse("2000-10-20")));
		mapDados.put(2, new DadosPessoais(2, "Ciclano de Souza", "ciclano@teste.com.br",
		"(11) 99992-9876", LocalDate.parse("1999-05-20")));
		mapDados.put(3, new DadosPessoais(3, "Beltrana dos Santos", "beltrana@teste.com.br",
		"(11) 99993-1122", LocalDate.parse("2001-02-15")));
	}

	@Override
	public List<DadosPessoais> findAll() {
//		List<DadosPessoais> resultado = new ArrayList<>();
//		for (DadosPessoais dp : mapDados.values()) {
//			resultado.add(dp);
//		}
//		return resultado;
		return new ArrayList<>(mapDados.values());
	}

	@Override
	public List<DadosPessoais> buscar(String termoBusca, int pagina, int quantidade) {
		List<DadosPessoais> resultadoTotal = new ArrayList<>();
		for (DadosPessoais dp : mapDados.values()) {
			if (dp.getNome().toLowerCase().contains(termoBusca.toLowerCase())) {
				resultadoTotal.add(dp);
			}
		}
		int paginaIndex = pagina - 1;
		int offset = paginaIndex * quantidade;

		List<DadosPessoais> resultadoFinal = new ArrayList<>();
		for (int i = offset; i < offset + quantidade && i < resultadoTotal.size(); i++) {
			resultadoFinal.add(resultadoTotal.get(i));
		}
		return resultadoFinal;
	}

	@Override
	public DadosPessoais findById(int id) {
		return mapDados.get(id);
	}

	@Override
	public Optional<DadosPessoais> findByIdComOptional(int id) {
		return Optional.ofNullable(mapDados.get(id));
	}

}
