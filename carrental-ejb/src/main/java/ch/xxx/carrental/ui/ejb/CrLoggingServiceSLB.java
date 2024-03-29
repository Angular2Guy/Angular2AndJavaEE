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



import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import java.util.logging.Level;
import java.util.logging.Logger;
import ch.xxx.carrental.ui.dto.CrLogMsg;
import ch.xxx.carrental.ui.service.CrLoggingService;

@Local(CrLoggingService.class)
@Stateless
public class CrLoggingServiceSLB implements CrLoggingService {	
    @Inject
	private Logger LOG;
	
	@Override
	public boolean logMsg(CrLogMsg crLogMsg) {
		if(CrLogMsg.LogLevel.ERROR.getLevel().equals(crLogMsg.getLogLevel())) {
			LOG.log(Level.SEVERE, crLogMsg.getLogMsg());
		} else if(CrLogMsg.LogLevel.WARN.getLevel().equals(crLogMsg.getLogLevel())) {
			LOG.log(Level.WARNING, crLogMsg.getLogMsg());
		} else if(CrLogMsg.LogLevel.INFO.getLevel().equals(crLogMsg.getLogLevel())) {
			LOG.info(crLogMsg.getLogMsg());
		} else {
			LOG.log(Level.SEVERE, "Log Level not found.");
			return false;
		}
		return true;
	}

}
