/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.senac.tads.dsw.exemplos;

import br.senac.tads.dsw.exemplos.dominio.DadosPessoais;
import br.senac.tads.dsw.exemplos.dominio.DadosPessoaisRepository;
import br.senac.tads.dsw.exemplos.dominio.Interesse;
import br.senac.tads.dsw.exemplos.dominio.InteresseRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DadosPessoaisServiceBdImpl implements DadosPessoaisService {

	@Autowired
	private DadosPessoaisRepository repo;

	@Autowired
	private InteresseRepository interesseRepo;

	@Override
	public List<DadosPessoaisDto> findAll() {
		List<DadosPessoais> resultadoBd = repo.findAll();
		List<DadosPessoaisDto> resultadoDto = new ArrayList<>();
		for (DadosPessoais bd : resultadoBd) {
			resultadoDto.add(new DadosPessoaisDto(bd));
		}
		return resultadoDto;
	}

	@Override
	public List<DadosPessoaisDto> buscar(String termoBusca, int pagina, int quantidade) {
		if (termoBusca != null && termoBusca.length() > 0 ) {
			Page<DadosPessoais> resultadosPage = repo.findComSql(termoBusca, PageRequest.of(pagina, quantidade));
			List<DadosPessoaisDto> resultadoDto = new ArrayList<>();
			for (DadosPessoais bd : resultadosPage.getContent()) {
				resultadoDto.add(new DadosPessoaisDto(bd));
			}
			return resultadoDto;
		} else {
			Page<DadosPessoais> resultadosPage = repo.findAll(PageRequest.of(pagina, quantidade));
			List<DadosPessoaisDto> resultadoDto = new ArrayList<>();
			for (DadosPessoais bd : resultadosPage.getContent()) {
				resultadoDto.add(new DadosPessoaisDto(bd));
			}
			return resultadoDto;
		}
	}

	@Override
	public DadosPessoaisDto findById(int id) {
		Optional<DadosPessoais> optDadosPessoais = repo.findById(id);
		if (optDadosPessoais.isEmpty()) {
			return null;
		}
		return new DadosPessoaisDto(optDadosPessoais.get());
	}

	@Override
	public Optional<DadosPessoaisDto> findByIdComOptional(int id) {
		return repo.findById(id).map(bd -> new DadosPessoaisDto(bd));
	}

	@Override
	@Transactional
	public void save(DadosPessoaisDto dados) {
        DadosPessoais bd  = new DadosPessoais();
        bd.setId(dados.getId());
        bd.setNome(dados.getNome());
        bd.setApelido(dados.getApelido());
        bd.setEmail(dados.getEmail());
        bd.setTelefone(dados.getTelefone());
        bd.setHashSenha(dados.getSenha());
        bd.setDataNascimento(dados.getDataNascimento());
        Set<Interesse> interesses = new HashSet<>();
        for (String conhecimento : dados.getConhecimentos() ) {
            Optional<Interesse> optInteresse = interesseRepo.findByNomeIgnoreCase(conhecimento);
            if (optInteresse.isPresent()) {
                interesses.add(optInteresse.get());
            }
        }
        bd.setInteresses(interesses);
        repo.save(bd);
	}

}
