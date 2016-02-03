package jv.jpatpl.scheduler;

import java.util.Date;

import jv.jpatpl.service.UtilisateurService;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Configuration
public class JobMiseAJourDonnee extends QuartzJobBean {

	/**
	 * Instance de Logger pour la journalisation de la classe
	 */
	private static final Logger log = Logger.getLogger(JobMiseAJourDonnee.class);

	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	UtilisateurService utilisateurService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		JobKey jobKey = context.getJobDetail().getKey();
		log.info("SimpleJob says: " + jobKey + " executing at " + new Date() + " data map " + context.getJobDetail().getJobDataMap().getKeys());
		UtilisateurService utilisateurService = applicationContext.getBean(UtilisateurService.class);
		log.info(utilisateurService.get(1L).getFirstname());

	}
}
