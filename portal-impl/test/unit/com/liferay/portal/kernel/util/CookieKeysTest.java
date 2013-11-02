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

package com.liferay.portal.kernel.util;

import com.liferay.portal.util.PropsImpl;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Raymond Augé
 */
public class CookieKeysTest {

	@Before
	public void setUp() {
		PropsUtil.setProps(new PropsImpl());
	}

	@Test
	public void testDomain1() throws Exception {
		String domain = CookieKeys.getDomain("www.liferay.com");

		Assert.assertEquals(".liferay.com", domain);
	}

	@Test
	public void testDomain2() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		String domain = CookieKeys.getDomain(mockHttpServletRequest);

		Assert.assertEquals(".liferay.com", domain);
	}

	@Test
	public void testDomain3() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_DOMAIN");

		Object value = field.get(null);

		try {
			field.set(null, "www.example.com");

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals("www.example.com", domain);
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testDomain4() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_USE_FULL_HOSTNAME");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.FALSE);

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals(".liferay.com", domain);
		}
		finally {
			field.set(null, value);
		}
	}

	@Test
	public void testDomain5() throws Exception {
		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setServerName("www.liferay.com");

		Field field = ReflectionUtil.getDeclaredField(
			CookieKeys.class, "_SESSION_COOKIE_USE_FULL_HOSTNAME");

		Object value = field.get(null);

		try {
			field.set(null, Boolean.TRUE);

			String domain = CookieKeys.getDomain(mockHttpServletRequest);

			Assert.assertEquals(StringPool.BLANK, domain);
		}
		finally {
			field.set(null, value);
		}
	}

}