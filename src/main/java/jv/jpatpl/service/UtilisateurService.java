package jv.jpatpl.service;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import jv.jpatpl.domain.Utilisateur;

public interface UtilisateurService {

	public Long saveOrUpdate(Utilisateur userDto) throws ParseException;

	public void delete(Long id);

	public Utilisateur get(Long id);

	public Collection<Utilisateur> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);

	public Utilisateur findUserByName(String login);

	public List<Utilisateur> listAll();
}
