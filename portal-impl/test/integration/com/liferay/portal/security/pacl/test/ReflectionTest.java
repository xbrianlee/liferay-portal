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

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.ReleaseInfo;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class ReflectionTest {

	public static final String TEST_FIELD_1 = "TEST_FIELD_1";

	@Test
	public void testPlugin1() throws Exception {
		try {
			Class<?> clazz = getClass();

			clazz.getDeclaredField("_log");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPlugin2() throws Exception {
		try {
			Class<?> clazz = getClass();

			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
			}
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPlugin3() throws Exception {
		try {
			Class<?> clazz = getClass();

			Field[] fields = clazz.getFields();

			for (Field field : fields) {
				field.setAccessible(false);
			}
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPlugin4() throws Exception {
		try {
			Class<?> clazz = getClass();

			clazz.getField("TEST_FIELD_1");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPlugin5() throws Exception {
		try {
			Class<?> clazz = getClass();

			Field field = clazz.getField("TEST_FIELD_1");

			field.setAccessible(false);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPlugin6() throws Exception {
		try {
			Class<?> clazz = getClass();

			Field field = clazz.getField("TEST_FIELD_1");

			boolean accessible = field.isAccessible();

			field.setAccessible(true);

			field.setAccessible(accessible);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPortal1() throws Exception {
		try {
			Class<?> clazz = PropsKeys.class;

			clazz.getField("ADMIN_DEFAULT_GROUP_NAMES");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testPortal2() throws Exception {
		try {
			Class<?> clazz = PropsKeys.class;

			Field field = clazz.getField("ADMIN_DEFAULT_GROUP_NAMES");

			field.setAccessible(false);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testPortal3() throws Exception {
		try {
			Class<?> clazz = ReleaseInfo.class;

			Field[] fields = clazz.getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
			}

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testPortal4() throws Exception {
		try {
			Class<?> clazz = ReleaseInfo.class;

			Field[] fields = clazz.getFields();

			for (Field field : fields) {
				field.setAccessible(false);
			}

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testPortal5() throws Exception {
		try {
			Class<?> clazz = UserLocalServiceUtil.class;

			clazz.getDeclaredField("_service");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	private static final String TEST_FIELD_2 = "TEST_FIELD_2";

	private static Log _log = LogFactoryUtil.getLog(ReflectionTest.class);

	static {

		// Prevent compiler from removing the unused fields

		if (_log.isDebugEnabled()) {
			_log.debug(TEST_FIELD_2);
		}
	}

}