package jv.jpatpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.support.FileSystemXmlApplicationContext;

import jv.jpatpl.dao.UtilisateurDaoImpl;
import jv.jpatpl.domain.Utilisateur;
import jv.jpatpl.service.UtilisateurService;

/**
 * Hello world!
 *
 */
public class App {
	public static void main2(String[] args) {
		runSpring();
	}

	public static void runHibernate() {
		// just for fun
		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("punit");
		EntityManager entityManager = sessionFactory.createEntityManager();
		UtilisateurDaoImpl utilDao = new UtilisateurDaoImpl();
		utilDao.setEntityManager(entityManager);

		Utilisateur util = utilDao.get(1L);
		System.out.println(" Firstname " + util.getFirstname());

		entityManager.close();
		sessionFactory.close();
	}

	public static void runSpring() {
		FileSystemXmlApplicationContext appContext = new FileSystemXmlApplicationContext(
				"src/main/webapp/WEB-INF/applicationContext.xml");

		UtilisateurService utilisateurService = (UtilisateurService) appContext.getBean("utilisateurService");
		Utilisateur utilGet = utilisateurService.get(1L);
		System.out.println(" Firstname " + utilGet.getFirstname());
		// Quartz listener
		// QuartzListenner listenner = new QuartzListenner();
		// listenner.contextInitialized(null);

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Press any key...");
		try {
			input.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		appContext.close();
	}
}
