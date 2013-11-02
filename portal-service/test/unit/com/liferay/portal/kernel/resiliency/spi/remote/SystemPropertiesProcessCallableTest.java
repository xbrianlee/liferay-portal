/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.kernel.resiliency.spi.remote;

import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class SystemPropertiesProcessCallableTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testSystemPropertiesProcessCallable() {
		Properties oldProperties = System.getProperties();

		Properties newProperties = new Properties();

		System.setProperties(newProperties);

		Map<String, String> propertiesMap = new HashMap<String, String>();

		propertiesMap.put("key1", "value1");
		propertiesMap.put("key2", "value2");
		propertiesMap.put("key3", "value3");

		SystemPropertiesProcessCallable systemPropertiesProcessCallable =
			new SystemPropertiesProcessCallable(propertiesMap);

		systemPropertiesProcessCallable.call();

		Assert.assertEquals(3, newProperties.size());

		Assert.assertEquals("value1", newProperties.getProperty("key1"));
		Assert.assertEquals("value2", newProperties.getProperty("key2"));
		Assert.assertEquals("value3", newProperties.getProperty("key3"));

		System.setProperties(oldProperties);
	}

}