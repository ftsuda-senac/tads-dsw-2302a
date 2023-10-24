/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DadosPessoaisServiceImpl implements DadosPessoaisService {

	private static int contador = 0;

	private Map<Integer, DadosPessoais> mapDados = new LinkedHashMap<>();

	@PostConstruct
    public void init() {
		int id = ++contador;
        mapDados.put(id, new DadosPessoais(id, "Fulano da Silva", "fulano", "fulano@teste.com.br", "(11) 99999-1122",
                "abcd$1234", "abcd$1234", LocalDate.parse("2000-10-20"), Arrays.asList("Java", "HTML", "CSS")));
		id = ++contador;
        mapDados.put(id, new DadosPessoais(id, "Ciclano de Souza", "ciclano", "ciclano@teste.com.br", "(11) 98765-1234",
                "abcd$1234", "abcd$1234", LocalDate.parse("2001-05-15"),  Arrays.asList("Java", "HTML", "Javascript")));
		id = ++contador;
        mapDados.put(id, new DadosPessoais(id, "Beltrana dos Santos", "beltrana", "beltrana@teste.com.br", "(11) 91234-8877",
                "abcd$1234", "abcd$1234", LocalDate.parse("1999-02-01"),  Arrays.asList("HTML", "CSS", "Javascript")));
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

	@Override
	public void save(DadosPessoais dados) {
		dados.setId(++contador);
		mapDados.put(dados.getId(), dados);
	}

}
