package ch.xxx.carrental.ui.ejb;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

public class Utils {
	private static final Logger LOG = Logger.getLogger(Utils.class);
	
	public static boolean checkForWildfly() {
		String namingFactoryKey = null;
		try {
			InitialContext ic = new InitialContext();
			namingFactoryKey = (String) ic.getEnvironment().get("java.naming.factory.url.pkgs");			
		} catch (NamingException e) {
			LOG.debug("No Wildfly found -> using singleton bean data service.");
		}
		return EjbNaming.JBOSS.getKey().equals(namingFactoryKey);
	}
}
