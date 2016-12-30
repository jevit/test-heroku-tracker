package jv.jpatpl.service;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import jv.jpatpl.domain.Personne;

public interface PersonneService {

	public Personne saveOrUpdate(Personne userDto) throws ParseException;

	public void delete(Long id);

	public Personne get(Long id);

	public Collection<Personne> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy,
			String order);

	public Personne findUserByName(String login);

	public List<Personne> listAll();
}
