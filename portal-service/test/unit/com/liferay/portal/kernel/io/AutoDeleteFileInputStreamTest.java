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

package com.liferay.portal.kernel.io;

import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.io.File;

import java.lang.reflect.Field;

import java.security.Permission;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public class AutoDeleteFileInputStreamTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testAutoRemoveFileInputStream() throws Exception {
		File tempFile = new File("tempFile");

		Assert.assertTrue(tempFile.createNewFile());

		AutoDeleteFileInputStream autoRemoveFileInputStream =
			new AutoDeleteFileInputStream(tempFile);

		final AtomicInteger checkDeleteCount = new AtomicInteger();

		SecurityManager securityManager = new SecurityManager() {

			@Override
			public void checkDelete(String file) {
				if (file.contains("tempFile")) {
					checkDeleteCount.getAndIncrement();
				}
			}

			@Override
			public void checkPermission(Permission permission) {
			}

		};

		System.setSecurityManager(securityManager);

		try {
			autoRemoveFileInputStream.close();
		}
		finally {
			System.setSecurityManager(null);
		}

		Assert.assertFalse(tempFile.exists());
		Assert.assertEquals(1, checkDeleteCount.get());

		checkDeleteCount.set(0);

		Assert.assertTrue(tempFile.createNewFile());

		autoRemoveFileInputStream = new AutoDeleteFileInputStream(tempFile);

		Assert.assertTrue(tempFile.delete());

		System.setSecurityManager(securityManager);

		try {
			autoRemoveFileInputStream.close();
		}
		finally {
			System.setSecurityManager(null);
		}

		Assert.assertFalse(tempFile.exists());
		Assert.assertEquals(2, checkDeleteCount.get());

		Class<?> clazz = Class.forName("java.io.DeleteOnExitHook");

		Field filesField = ReflectionUtil.getDeclaredField(clazz, "files");

		Set<String> files = (Set<String>)filesField.get(null);

		Assert.assertTrue(files.contains(tempFile.getPath()));
	}

}