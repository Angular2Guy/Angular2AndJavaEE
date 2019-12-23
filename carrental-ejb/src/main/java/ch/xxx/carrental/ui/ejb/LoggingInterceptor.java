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

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.log4j.Logger;

import ch.xxx.carrental.ui.dto.BusinessException;
import ch.xxx.carrental.ui.exception.LocalEntityNotFoundException;
import ch.xxx.carrental.ui.exception.LocalValidationException;

@AutoLogging
@Interceptor
public class LoggingInterceptor {
	private static final Logger LOG = Logger.getLogger(LoggingInterceptor.class);

	@AroundInvoke
	public Object interceptLogging(InvocationContext ctx) throws Exception {
		StringBuilder params = new StringBuilder();
		for (Object param : ctx.getParameters()) {
			params.append(param.toString()).append(",");
		}
		String signature = ctx.getClass() + "." + ctx.getMethod() + "(" + params.substring(0, params.length() - 1)
				+ ")";
		Date now1 = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("hh.mm.ss dd.MM.yyyy");
		LOG.info(sdf.format(now1) + " - " + signature);
		Object o = null;
		try {
			o = ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		} catch (Exception e) {
			if (e instanceof LocalValidationException || e instanceof LocalEntityNotFoundException) {
				LOG.warn(signature, e);
				throw e;
			}
			LOG.error(signature, e);
			throw new BusinessException(signature, e);
		} finally {
			Date now2 = new Date(System.currentTimeMillis());
			LOG.info(sdf.format(now2) + " - " + signature);
		}
		return o;
	}
}
