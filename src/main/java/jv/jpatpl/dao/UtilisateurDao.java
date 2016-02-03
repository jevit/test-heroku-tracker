package jv.jpatpl.dao;

import java.util.Collection;
import java.util.List;

import jv.jpatpl.domain.Utilisateur;

public interface UtilisateurDao {

	public Long saveOrUpdate(Utilisateur utilisateur);

	public void delete(Long id);

	public Utilisateur get(Long id);

	public Collection<Utilisateur> list(Integer firstResult,
			Integer maxResults, String orderBy, String order);

	public Collection<Utilisateur> listAll();

	public Collection<Utilisateur> search(String textSearched,
			Integer firstResult, Integer maxResults, String orderBy,
			String order);

	public Long count();

	public Long countSearch(String textSearched);

	public Utilisateur findByLogin(String login);

	List<Utilisateur> getAll();

}
