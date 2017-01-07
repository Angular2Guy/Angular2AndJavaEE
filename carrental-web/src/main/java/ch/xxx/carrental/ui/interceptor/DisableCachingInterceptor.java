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
package ch.xxx.carrental.ui.interceptor;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import ch.xxx.carrental.ui.dto.BusinessException;

@DisableCaching 
@Interceptor
public class DisableCachingInterceptor {
	private static final Logger LOG = Logger.getLogger(DisableCachingInterceptor.class);
	
	@AroundInvoke
	public Object interceptNoCache(InvocationContext ctx) throws Exception {		
		Object o = null;	
		try{
			o = ctx.getMethod().invoke(ctx.getTarget(), ctx.getParameters());
		}catch(Exception e) {
			if(!(e instanceof BusinessException)) {
				LOG.error("Exception: ", e);				
			}
			return Response.serverError().cacheControl(createCacheControl()).build();				
		}
		if(o instanceof Response) {						
			return Response.fromResponse((Response) o).cacheControl(createCacheControl()).build();
		}
		return o; 
	}

	private CacheControl createCacheControl() {
		CacheControl cacheControl = new CacheControl();
		cacheControl.setNoCache(true);
		return cacheControl;
	}
}
