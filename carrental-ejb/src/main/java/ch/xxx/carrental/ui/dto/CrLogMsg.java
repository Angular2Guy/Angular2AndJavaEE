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
package ch.xxx.carrental.ui.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CrLogMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class CrLogMsg {
	public enum LogLevel {INFO("Info"), WARN("Warn"), ERROR("Error");
		private String level;
		
		private LogLevel(String level) {
			this.level = level;
		}
		
		public String getLevel() {
			return this.level;
		}
	};
	
	@XmlElement(required=true)
	private String logLevel;
	
	@XmlElement(required=true)
	private String logMsg;
	
	public CrLogMsg() {		
	}

	public CrLogMsg(String logLevel, String logMsg) {
		super();
		this.logLevel = logLevel;
		this.logMsg = logMsg;
	}

	public CrLogMsg(LogLevel logLevel, String logMsg) {
		super();
		this.logLevel = logLevel.getLevel();
		this.logMsg = logMsg;
	}
	
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public String getLogMsg() {
		return logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}
}
