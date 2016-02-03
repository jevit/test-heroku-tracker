package jv.jpatpl.aop;

import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

public class AopLogging {

	private static final Logger LOG = Logger.getLogger(AopLogging.class);

	/**
	 * Lanc� avant l'appel d'une m�thode pr�sente dans geo.poi.dao Affiche le
	 * nom de la m�thode , ses parametres, et le type qu'il retourne
	 * 
	 * @param joinPoint
	 */
	public void logBefore(final JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

		// Nom de la m�thode intercept�e
		String name = joinPoint.getSignature().toLongString();
		StringBuffer sb = new StringBuffer(name + " called with: [");

		// Liste des valeurs des arguments re�us par la m�thode
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			sb.append("'" + o + "'");
			sb.append(i == args.length - 1 ? "" : ", ");
		}
		sb.append(']');
		LOG.info(sb);
	}

	/**
	 * Lanc� apr�s le lancement d'une m�thode pr�sente dans geo.poi.dao affiche
	 * le nom de la m�thode et l'objet qu'il retourne
	 * 
	 * @param staticPart
	 * @param result
	 */
	public void logAfter(final StaticPart staticPart, final Object result) {
		// Nom de la m�thode intercept�e
		String name = staticPart.getSignature().toLongString();
		LOG.info(name + " returning: [" + result + "]");
	}

	public void afterThrowing(final Method m, final Object[] args, final Object target, final Throwable ex) {
		LOG.info("Exception in method: " + m.getName() + " Exception is: " + ex.getMessage());
	}

	/**
	 * Dur�e d'�x�cution de la m�thode
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	public Object logTimeMethod(final ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object retVal = joinPoint.proceed();
		stopWatch.stop();
		StringBuffer logMessage = new StringBuffer();
		logMessage.append(" execution time: ");
		logMessage.append(stopWatch.getTotalTimeMillis());
		logMessage.append(" ms");
		LOG.warn(logMessage.toString());
		return retVal;
	}

}
