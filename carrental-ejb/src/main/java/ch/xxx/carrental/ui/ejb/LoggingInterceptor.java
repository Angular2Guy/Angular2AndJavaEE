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

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

import ch.xxx.carrental.ui.dto.BusinessException;
import ch.xxx.carrental.ui.exception.LocalEntityNotFoundException;
import ch.xxx.carrental.ui.exception.LocalValidationException;
import jakarta.annotation.Priority;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@AutoLogging
@Interceptor
@Priority(Interceptor.Priority.APPLICATION)
public class LoggingInterceptor {
	@Inject
	private Logger LOG;

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
			if (e instanceof InvocationTargetException && (
					((InvocationTargetException) e).getTargetException() instanceof LocalValidationException 
					|| ((InvocationTargetException) e).getTargetException() instanceof LocalEntityNotFoundException)) {
				RuntimeException re = (RuntimeException) ((InvocationTargetException) e).getTargetException();
				LOG.log(Level.WARNING, signature, re);
				throw re;
			}
			LOG.log(Level.SEVERE, signature, e);
			throw new BusinessException(signature, e);
		} finally {
			Date now2 = new Date(System.currentTimeMillis());
			LOG.info(sdf.format(now2) + " - " + signature);
		}
		return o;
	}
}
