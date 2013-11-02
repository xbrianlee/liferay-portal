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

import com.liferay.portal.kernel.test.CodeCoverageAssertor;

import javax.servlet.http.Cookie;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class CookieUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testConstructor() {
		new CookieUtil();
	}

	@Test
	public void testEquals() {

		// Comment

		Cookie cookie1 = new Cookie("name", null);

		cookie1.setComment("comment");

		Cookie cookie2 = new Cookie("name2", null);

		cookie2.setComment("comment2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setComment("comment");

		// Domain

		cookie1.setDomain("domain");
		cookie2.setDomain("domain2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setDomain("domain");

		// Max age

		cookie1.setMaxAge(1);
		cookie2.setMaxAge(2);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setMaxAge(1);

		// Name

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2 = new Cookie("name", null);

		cookie2.setComment("comment");
		cookie2.setDomain("domain");
		cookie2.setMaxAge(1);

		// Path

		cookie1.setPath("path");
		cookie2.setPath("path2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setPath("path");

		// Secure

		cookie1.setSecure(true);
		cookie2.setSecure(false);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setSecure(true);

		// Value

		cookie1.setValue("value");
		cookie2.setValue("value2");

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setValue("value");

		// Version

		cookie1.setVersion(1);
		cookie2.setVersion(2);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setVersion(1);

		// HTTP only

		cookie1.setHttpOnly(true);
		cookie2.setHttpOnly(false);

		Assert.assertFalse(CookieUtil.equals(cookie1, cookie2));

		cookie2.setHttpOnly(true);

		// Equals

		Assert.assertTrue(CookieUtil.equals(cookie1, cookie2));
	}

	@Test
	public void testSerializationAndDeserialization() {
		Cookie cookie1 = new Cookie("name1", null);

		byte[] bytes = CookieUtil.serialize(cookie1);

		Assert.assertTrue(
			CookieUtil.equals(cookie1, CookieUtil.deserialize(bytes)));

		Cookie cookie2 = new Cookie("name2", "value");

		cookie2.setComment("comment");
		cookie2.setDomain("domain");
		cookie2.setHttpOnly(true);
		cookie2.setMaxAge(1);
		cookie2.setPath("path");
		cookie2.setSecure(true);
		cookie2.setVersion(1);

		bytes = CookieUtil.serialize(cookie2);

		Assert.assertTrue(
			CookieUtil.equals(cookie2, CookieUtil.deserialize(bytes)));
	}

	@Test
	public void testToString() {
		Cookie cookie = new Cookie("name", "value");

		cookie.setComment("comment");
		cookie.setDomain("domain");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(1);
		cookie.setPath("path");
		cookie.setSecure(true);
		cookie.setVersion(1);

		Assert.assertEquals(
			"{comment=comment, domain=domain, httpOnly=true, maxAge=1, " +
				"name=name, path=path, secure=true, value=value, version=1}",
			CookieUtil.toString(cookie));
	}

}