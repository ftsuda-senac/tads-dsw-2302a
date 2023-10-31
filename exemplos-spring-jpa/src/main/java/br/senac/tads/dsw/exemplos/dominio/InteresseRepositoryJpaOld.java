package br.senac.tads.dsw.exemplos.dominio;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class InteresseRepositoryJpaOld {

	@PersistenceContext
	private EntityManager em;

	public List<Interesse> findAll() {
		TypedQuery<Interesse> jpqlQuery =
				em.createQuery("SELECT i FROM Interesse i",
						Interesse.class);
		return jpqlQuery.getResultList();
	}

	public Optional<Interesse> findById(Integer id) {
		TypedQuery<Interesse> jpqlQuery =
				em.createQuery("SELECT i FROM Interesse i WHERE i.id = :idInteresse", Interesse.class)
				.setParameter("idInteresse", id);
		try {
			return Optional.of(jpqlQuery.getSingleResult());
		} catch(NoResultException ex) {
			return Optional.empty();
		}
	}

	@Transactional
	public Interesse save(Interesse interesse) {
		if (interesse.getId() == null) {
			// Inclui um novo interesse
			em.persist(interesse);
		} else {
			// Atualizar interesse existente
			em.merge(interesse);
		}
		return interesse;
	}

	@Transactional
	public void deleteById(Integer id) {
		// Carregar obj no entityManager
		Interesse interesse = em.find(Interesse.class, id);
		em.remove(interesse);
	}

}
