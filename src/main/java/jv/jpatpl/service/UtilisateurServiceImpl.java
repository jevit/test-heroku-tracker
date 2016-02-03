package jv.jpatpl.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import jv.jpatpl.dao.UtilisateurDao;
import jv.jpatpl.domain.Utilisateur;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service("utilisateurService")
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilisateurServiceImpl.class);
	@Autowired
	private UtilisateurDao dao;

	public Long saveOrUpdate(Utilisateur userDto) {

		return dao.saveOrUpdate(userDto);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public Utilisateur get(Long id) {
		Utilisateur userEntity = dao.get(id);

		return userEntity;
	}

	@Transactional(readOnly = true)
	public List<Utilisateur> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order) {

		Collection<Utilisateur> list = null;
		Long count = null;

		if (StringUtils.isEmpty(textSearched)) {
			list = dao.list(firstResult, maxResults, orderBy, order);
			count = dao.count();
		} else {
			list = dao.search(textSearched, firstResult, maxResults, orderBy, order);
			count = dao.countSearch(textSearched);
		}

		return new ArrayList<Utilisateur>(list);
	}

	@Transactional(readOnly = true)
	public List<Utilisateur> listAll() {
		List<Utilisateur> listUtilisateur = (List<Utilisateur>) dao.listAll();
		return listUtilisateur;
	}

	public Utilisateur findUserByName(String login) {
		return dao.findByLogin(login);
	}

}
