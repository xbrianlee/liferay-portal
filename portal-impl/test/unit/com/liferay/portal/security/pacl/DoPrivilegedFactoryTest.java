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

package com.liferay.portal.security.pacl;

import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.UserImpl;
import com.liferay.portal.security.lang.DoPrivilegedBean;
import com.liferay.portal.security.lang.DoPrivilegedFactory;

import org.junit.Before;
import org.junit.Test;

import org.testng.Assert;

/**
 * @author Raymond Augé
 */
public class DoPrivilegedFactoryTest {

	@Before
	public void setUp() {
		PortalClassLoaderUtil.setClassLoader(UserImpl.class.getClassLoader());
	}

	@Test
	public void testBoolean() {
		Boolean wrappedBoolean = DoPrivilegedFactory.wrap(Boolean.TRUE);

		Assert.assertTrue(wrappedBoolean);
		Assert.assertEquals(wrappedBoolean.getClass(), Boolean.class);
	}

	@Test
	public void testClassWithNoInterfaces() {
		ClassWithNoInterfaces classWithNoInterfaces =
			new ClassWithNoInterfaces();

		ClassWithNoInterfaces wrappedClassWithNoInterfaces =
			DoPrivilegedFactory.wrap(classWithNoInterfaces);

		Assert.assertEquals(
			wrappedClassWithNoInterfaces, classWithNoInterfaces);
		Assert.assertFalse(
			wrappedClassWithNoInterfaces instanceof DoPrivilegedBean);
	}

	@Test
	public void testString() {
		String string = DoPrivilegedFactory.wrap("Test");

		Assert.assertEquals(string, "Test");
		Assert.assertEquals(string.getClass(), String.class);
	}

	@Test
	public void testUser() {
		User user = DoPrivilegedFactory.wrap(new UserImpl());

		Assert.assertTrue(user instanceof DoPrivilegedBean);
	}

	private class ClassWithNoInterfaces {
	}

}