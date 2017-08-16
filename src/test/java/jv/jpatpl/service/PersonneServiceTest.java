package jv.jpatpl.service;

import org.junit.Before;
import org.mockito.Mockito;

import jv.jpatpl.dao.PersonneDao;

public class PersonneServiceTest {

	private PersonneDao personneDao;

	@Before
	public void setUp() {
		personneDao = Mockito.mock(PersonneDao.class);
	}
}
