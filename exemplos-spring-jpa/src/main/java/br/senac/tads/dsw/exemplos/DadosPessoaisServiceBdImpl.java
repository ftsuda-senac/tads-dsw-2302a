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
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public DadosPessoaisDto findById(int id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public Optional<DadosPessoaisDto> findByIdComOptional(int id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	@Transactional
	public void save(DadosPessoaisDto dados) {
        DadosPessoais bd  =new DadosPessoais();
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
