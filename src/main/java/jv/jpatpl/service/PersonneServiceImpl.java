package jv.jpatpl.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jv.jpatpl.dao.PersonneDao;
import jv.jpatpl.domain.Personne;

@Service("personneService")
@Transactional
public class PersonneServiceImpl implements PersonneService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonneServiceImpl.class);
	@Autowired
	private PersonneDao dao;

	public Personne saveOrUpdate(Personne personne) {
		return dao.saveOrUpdate(personne);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public Personne get(Long id) {
		Personne userEntity = dao.get(id);

		return userEntity;
	}

	@Transactional(readOnly = true)
	public List<Personne> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy,
			String order) {

		Collection<Personne> list = null;
		Long count = null;

		if (StringUtils.isEmpty(textSearched)) {
			list = dao.list(firstResult, maxResults, orderBy, order);
			count = dao.count();
		} else {
			list = dao.search(textSearched, firstResult, maxResults, orderBy, order);
			count = dao.countSearch(textSearched);
		}

		return new ArrayList<Personne>(list);
	}

	@Transactional(readOnly = true)
	public List<Personne> listAll() {
		List<Personne> listPersonne = (List<Personne>) dao.listAll();
		return listPersonne;
	}

	public Personne findUserByName(String login) {
		return dao.findByLogin(login);
	}

}
