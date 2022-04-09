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
package ch.xxx.carrrental.ui.rest.model;

import org.junit.Assert;
import org.junit.Test;

public class UrlTest {

    private String regexStr = "^\\/de\\/[a-zA-Z_0-9\\/]+$";
	
	@Test
	public void url1() {
		String testStr = "/de/favicon.ico";
		Assert.assertFalse(testStr.matches(regexStr));
	}
	
	@Test
	public void url2() {
		String testStr = "/de/crlist/mietenr/1";
		Assert.assertTrue(testStr.matches(regexStr));
	}
	
	@Test
	public void url3() {
		String testStr = "/de/crdetail/mietenr/1/jahr/2017";
		Assert.assertTrue(testStr.matches(regexStr));
	}
	
	@Test
	public void url4() {
		String testStr = "/de/";
		Assert.assertFalse(testStr.matches(regexStr));
	}

}
