package jv.jpatpl.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import jv.jpatpl.domain.Utilisateur;

import org.springframework.stereotype.Repository;

@Repository
public class UtilisateurDaoImpl extends GenericDaoImpl<Utilisateur> implements
		UtilisateurDao {
	public Long saveOrUpdate(Utilisateur utilisateur) {
		getEntityManager().merge(utilisateur);
		return utilisateur.getId();
	}

	public void delete(Long id) {
		Utilisateur userEntity = new Utilisateur();
		userEntity.setId(id);
		((UtilisateurDaoImpl) getEntityManager()).delete(id);
	}

	public Utilisateur get(Long id) {
		return (Utilisateur) getEntityManager().find(Utilisateur.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Utilisateur> list(Integer firstResult,
			Integer maxResults, String orderBy, String order) {
		Query query = getEntityManager().createQuery(
				"FROM Utilisateur ORDER BY " + orderBy + " " + order);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Collection<Utilisateur> listAll() {
		Query query = getEntityManager().createQuery(" FROM Utilisateur ");
		return query.getResultList();
	}

	public Long count() {
		Query query = getEntityManager().createQuery(
				"SELECT COUNT(id) FROM Utilisateur");
		return (Long) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Collection<Utilisateur> search(String textSearched,
			Integer firstResult, Integer maxResults, String orderBy,
			String order) {
		Query query = getEntityManager().createQuery(
				"FROM Utilisateur "
						+ "WHERE UPPER(lastname) LIKE :textSearched "
						+ "OR UPPER(firstname) LIKE :textSearched "
						+ "ORDER BY " + orderBy + " " + order);
		query.setParameter("textSearched", textSearched);
		// query.setParameter("textSearched",
		// DaoUtils.jokerifyUpperCase(textSearched));
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);
		return query.getResultList();
	}

	public Long countSearch(String textSearched) {
		Query query = getEntityManager().createQuery(
				"SELECT COUNT(id) " + "FROM Utilisateur "
						+ "WHERE UPPER(lastname) LIKE :textSearched "
						+ "OR UPPER(firstname) LIKE :textSearched");
		query.setParameter("textSearched", textSearched);

		return (Long) query.getSingleResult();
	}

	public Utilisateur findByLogin(String login) {
		Query query = getEntityManager().createQuery(
				"FROM Utilisateur " + "WHERE login = :login");
		query.setParameter("login", login);
		return (Utilisateur) query.getSingleResult();
	}

	public List<Utilisateur> getAll() {
		Query query = getEntityManager().createQuery("FROM Utilisateur u "
		/* + " JOIN FETCH u.listeParcours" */);
		return (List<Utilisateur>) query.getResultList();
		// Criteria crit = getEntityManager().createCriteria(Utilisateur.class);
		// crit.createCriteria("listeParcours");
		// return crit.list();
	}
}
