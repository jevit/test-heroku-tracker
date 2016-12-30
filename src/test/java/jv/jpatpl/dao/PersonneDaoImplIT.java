package jv.jpatpl.dao;

import static org.junit.Assert.assertNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import jv.jpatpl.domain.Personne;

@DatabaseSetup(value = "/datasets/personnes.xml")
@Transactional
public class PersonneDaoImplIT extends AbstractRepositoryIT {

	@Autowired
	private PersonneDao personneDao;

	@Test
	@Rollback(true)
	public void personneAdd() {
		Personne personne = new Personne(null, "1", "1");
		Personne personneRet = personneDao.saveOrUpdate(personne);
		Assert.assertNotNull("Person is null", personneRet.getId());
	}

	@Test
	@Rollback(true)
	public void personneDelete() {
		personneDao.delete(1L);
		Personne personneRet = personneDao.get(1L);
		assertNull("Person is not null.", personneRet);
	}

	@Test
	@Rollback(true)
	public void personneGetOne() {
		Personne personneRet = personneDao.get(1L);
		Assert.assertNotNull("Person is null.", personneRet);
	}

	@Test
	@Rollback(true)
	public void personneGetAll() {
		List<Personne> personneRets = personneDao.getAll();
		Assert.assertNotNull("Person is null.", personneRets);
	}
}