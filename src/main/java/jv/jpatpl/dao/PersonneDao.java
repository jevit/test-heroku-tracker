package jv.jpatpl.dao;

import java.util.Collection;
import java.util.List;

import jv.jpatpl.domain.Personne;

public interface PersonneDao {

	public Personne saveOrUpdate(Personne Personne);

	public void delete(Long id);

	public Personne get(Long id);

	public Collection<Personne> list(Integer firstResult, Integer maxResults, String orderBy, String order);

	public Collection<Personne> listAll();

	public Collection<Personne> search(String textSearched, Integer firstResult, Integer maxResults, String orderBy,
			String order);

	public Long count();

	public Long countSearch(String textSearched);

	public Personne findByLogin(String login);

	List<Personne> getAll();

}
