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
package ch.xxx.carrental.ui.config;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.stream.StreamSupport;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter("/*")
public class ForwardingFilter implements Filter {
	private static final Logger LOGGER = LoggerFactory.getLogger(ForwardingFilter.class);
	private static final String REST_PATH = "/rest";
	private static final List<Locale> SUPPORTED_LOCALES = List.of(Locale.ENGLISH, Locale.GERMAN);
	public static final List<String> LANGUAGE_PATHS = SUPPORTED_LOCALES.stream()
			.map(myLocale -> String.format("/%s/", myLocale.getLanguage())).toList();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest myRequest = (HttpServletRequest) request;		
		if (myRequest.getServletPath().contains(REST_PATH)
				|| LANGUAGE_PATHS.stream().anyMatch(langPath -> myRequest.getServletPath().contains(langPath))
						&& (myRequest.getServletPath().contains(".") && !myRequest.getServletPath().contains("?"))) {
			//LOGGER.info("Nothing to do: {}",myRequest.getServletPath());
			chain.doFilter(myRequest, response);
		} else {
			Iterable<Locale> iterable = () -> myRequest.getLocales().asIterator();
			Locale userLocale = StreamSupport.stream(iterable.spliterator(), false)
					.filter(myLocale -> SUPPORTED_LOCALES.contains(myLocale)).findFirst().orElse(Locale.ENGLISH);
			String forwardPath = String.format("/%s/index.html", userLocale.getLanguage());
			RequestDispatcher dispatcher = myRequest.getServletContext().getRequestDispatcher(forwardPath);
			//LOGGER.info("Forward path: {}", forwardPath);
			dispatcher.forward(myRequest, response);
			return;
		}
	}
}
