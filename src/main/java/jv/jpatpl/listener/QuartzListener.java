package jv.jpatpl.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jv.jpatpl.service.SchedulerService;

public class QuartzListener implements ServletContextListener {

	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	SchedulerService schedulerService;

	public void contextDestroyed(ServletContextEvent arg0) {
		//
	}

	public void contextInitialized(ServletContextEvent servletContext) {
		WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext.getServletContext()).getAutowireCapableBeanFactory().autowireBean(this);
		schedulerService.lanceScheduler();
	}
}
