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

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.Route;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Connor McKay
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class RouteImplTest {

	@Test
	public void testNonMatchingRoute() {
		Map<String, String> parameters = new HashMap<String, String>();

		parameters.put("action", "view");
		parameters.put("id", "bob");

		Map<String, String> originalParameters = new HashMap<String, String>(
			parameters);

		Route route = new RouteImpl("{action}/{id:\\d+}");

		String url = route.parametersToUrl(parameters);

		Assert.assertNull(url);
		Assert.assertEquals(originalParameters, parameters);
	}

}