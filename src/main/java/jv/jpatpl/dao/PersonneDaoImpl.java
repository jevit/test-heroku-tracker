package jv.jpatpl.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import jv.jpatpl.domain.Personne;

@Repository
public class PersonneDaoImpl extends GenericDaoImpl<Personne> implements PersonneDao {

	public Personne saveOrUpdate(Personne personne) {
		Personne personneRet = getEntityManager().merge(personne);
		return personneRet;
	}

	public void delete(Long id) {
		Personne personne = getEntityManager().find(Personne.class, id);
		getEntityManager().remove(personne);
	}

	public Personne get(Long id) {
		return (Personne) getEntityManager().find(Personne.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Personne> list(Integer firstResult, Integer maxResults, String orderBy, String order) {
		Query query = getEntityManager().createQuery("FROM Personne ORDER BY " + orderBy + " " + order);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Personne> listAll() {
		Query query = getEntityManager().createQuery(" FROM Personne ");
		return query.getResultList();
	}

	public Long count() {
		Query query = getEntityManager().createQuery("SELECT COUNT(id) FROM Personne");
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Personne> search(String textSearched, Integer firstResult, Integer maxResults, String orderBy,
			String order) {
		Query query = getEntityManager().createQuery("FROM Personne " + "WHERE UPPER(lastname) LIKE :textSearched "
				+ "OR UPPER(firstname) LIKE :textSearched " + "ORDER BY " + orderBy + " " + order);
		query.setParameter("textSearched", textSearched);
		// query.setParameter("textSearched",
		// DaoUtils.jokerifyUpperCase(textSearched));
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public Long countSearch(String textSearched) {
		Query query = getEntityManager().createQuery("SELECT COUNT(id) " + "FROM Personne "
				+ "WHERE UPPER(lastname) LIKE :textSearched " + "OR UPPER(firstname) LIKE :textSearched");
		query.setParameter("textSearched", textSearched);

		return (Long) query.getSingleResult();
	}

	public Personne findByLogin(String login) {
		Query query = getEntityManager().createQuery("FROM Personne " + "WHERE login = :login");
		query.setParameter("login", login);
		return (Personne) query.getSingleResult();
	}

	public List<Personne> getAll() {
		Query query = getEntityManager().createQuery("FROM Personne u "
		/* + " JOIN FETCH u.listeParcours" */);
		return (List<Personne>) query.getResultList();
		// Criteria crit = getEntityManager().createCriteria(Personne.class);
		// crit.createCriteria("listeParcours");
		// return crit.list();
	}
}
