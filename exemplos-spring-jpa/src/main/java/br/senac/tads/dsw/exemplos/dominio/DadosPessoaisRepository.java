/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.senac.tads.dsw.exemplos.dominio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DadosPessoaisRepository extends JpaRepository<DadosPessoais, Integer> {

	Page<DadosPessoais> findByNomeContainingIgnoreCase(String nome, Pageable page);

	Page<DadosPessoais> findByNomeContainingIgnoreCaseOrEmailContainingIgnoreCase(String nome, String email,Pageable page);

	@Query("""
        SELECT dp FROM DadosPessoais dp
        WHERE UPPER(dp.nome) LIKE UPPER(%?1%)
        OR UPPER(dp.email) LIKE UPPER(%?1%)
        """)
	Page<DadosPessoais> findComJpql(String termo, Pageable page);

	@Query(value = """
        SELECT * from dados_pessoais WHERE
			nome ILIKE %?1% OR email ILIKE %?1%
        """, nativeQuery = true)
	Page<DadosPessoais> findComSql(String termo, Pageable page);
}
