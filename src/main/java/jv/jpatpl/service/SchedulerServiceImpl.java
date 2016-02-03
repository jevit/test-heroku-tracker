package jv.jpatpl.service;

import jv.jpatpl.scheduler.JobMiseAJourDonneeListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("schedulerService")
@Transactional
public class SchedulerServiceImpl implements SchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerServiceImpl.class);
	@Autowired
	ApplicationContext applicationContext;

	@Override
	public void lanceScheduler() {
		SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
		SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
		quartzScheduler.setJobFactory(jobFactory);
		JobDetail job = JobBuilder.newJob(JobMiseAJourDonneeListener.class).withIdentity("anyJobName", "group1").build();
		try {
			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("anyTriggerName", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();
			Scheduler scheduler = ((StdScheduler) applicationContext.getBean("scheduler"));
			scheduler.start();
			scheduler.scheduleJob(job, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}

	}

}
