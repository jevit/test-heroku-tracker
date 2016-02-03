package jv.jpatpl.scheduler;

import java.util.Date;

import jv.jpatpl.service.UtilisateurService;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

public class JobMiseAJourDonneeListener implements Job {

	/**
	 * Instance de Logger pour la journalisation de la classe
	 */
	private static final Logger log = Logger.getLogger(JobMiseAJourDonneeListener.class);

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			UtilisateurService utilisateurService = applicationContext.getBean(UtilisateurService.class);
			JobKey jobKey = context.getJobDetail().getKey();
			utilisateurService.get(1L);
			log.info("SimpleJob says: " + jobKey + " executing at " + new Date() + " name " + utilisateurService.get(1L).getLastname() + " data map "
					+ context.getJobDetail().getJobDataMap().getKeys().toString());
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
