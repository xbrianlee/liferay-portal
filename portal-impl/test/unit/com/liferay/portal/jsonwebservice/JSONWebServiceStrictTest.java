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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;
import com.liferay.portal.kernel.servlet.HttpMethods;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Igor Spasic
 */
@PrepareForTest(PropsUtil.class)
@RunWith(PowerMockRunner.class)
public class JSONWebServiceStrictTest extends BaseJSONWebServiceTestCase {

	@Before
	public void setUp() throws Exception {
		spy(PropsUtil.class);

		when(
			PropsUtil.get(PropsKeys.JSONWS_WEB_SERVICE_STRICT_HTTP_METHOD)
		).thenReturn(
			"true"
		);
	}

	@Test
	public void testStrictHttpMethod() throws Exception {
		initPortalServices();

		registerActionClass(CamelFooService.class);

		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/camelfoo/post/value/123");

		try {
			lookupJSONWebServiceAction(mockHttpServletRequest);

			Assert.fail();
		}
		catch (RuntimeException re) {
		}

		mockHttpServletRequest = createHttpRequest(
			"/camelfoo/post/value/123", HttpMethods.POST);

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			mockHttpServletRequest);

		Assert.assertNotNull(jsonWebServiceAction);

		Assert.assertEquals("post 123", jsonWebServiceAction.invoke());
	}

}