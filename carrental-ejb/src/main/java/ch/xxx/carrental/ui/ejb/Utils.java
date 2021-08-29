/**
 *    Copyright 2016 Sven Loesekann

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package ch.xxx.carrental.ui.ejb;

import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

@Named
public class Utils {
	@Inject
	private Logger LOG;
	
	public boolean checkForWildflyorWS() {
		String namingFactoryKey = null;
		try {
			InitialContext ic = new InitialContext();	
			namingFactoryKey = (String) ic.getEnvironment().get(EjbNaming.JBOSS.getKey());
			if(namingFactoryKey == null || !EjbNaming.JBOSS.getValue().contains(namingFactoryKey)) {
				Object o = ic.lookup(EjbNaming.WEBSPHERE.getKey()); 
				namingFactoryKey = o != null ? o.getClass().getName() : "Not JBoss or Websphere";
			}
		} catch (NamingException e) {			
			LOG.warn("No NamingFactory found -> using singleton bean data service.");
			return false;
		}
		boolean result = EjbNaming.JBOSS.getValue().contains(namingFactoryKey) || EjbNaming.WEBSPHERE.getValue().equals(namingFactoryKey);
		LOG.info("Value: '"+namingFactoryKey+"'"+" Return: "+result);
		return result;
	}
}
