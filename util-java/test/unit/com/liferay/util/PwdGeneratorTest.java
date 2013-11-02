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

package com.liferay.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Brian Wing Shun Chan
 */
public class PwdGeneratorTest {

	@Test
	public void testGetPassword() {
		long start = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			PwdGenerator.getPassword();
		}

		long end = System.currentTimeMillis();

		long delta = end - start;

		if (_log.isInfoEnabled()) {
			_log.info(
				"Generated 100 thousand secure passwords in " + delta + " ms");
		}

		Assert.assertTrue(delta < 2000);
	}

	private static Log _log = LogFactoryUtil.getLog(PwdGeneratorTest.class);

}