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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.nio.intraband.blocking.ExecutorIntraband;
import com.liferay.portal.kernel.nio.intraband.nonblocking.SelectorIntraband;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.test.NewClassLoaderJUnitTestRunner;
import com.liferay.portal.kernel.util.PropsKeys;

import java.io.IOException;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(NewClassLoaderJUnitTestRunner.class)
public class IntrabandFactoryUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testCreateIntrabandClassNotFound() throws IOException {
		System.setProperty(PropsKeys.INTRABAND_IMPL, "NoSuchClass");

		try {
			IntrabandFactoryUtil.createIntraband();

			Assert.fail();
		}
		catch (RuntimeException re) {
			Assert.assertEquals(
				re.getMessage(), "Unable to instantiate NoSuchClass");

			Throwable throwable = re.getCause();

			Assert.assertSame(
				ClassNotFoundException.class, throwable.getClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandCustomizedImpl() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_IMPL, SelectorIntraband.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(SelectorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandDefaultToFIFO() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, FIFOWelder.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(ExecutorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testCreateIntrabandDefaultToSocket() throws Exception {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, SocketWelder.class.getName());

		Intraband intraband = null;

		try {
			intraband = IntrabandFactoryUtil.createIntraband();

			Assert.assertSame(SelectorIntraband.class, intraband.getClass());
		}
		finally {
			if (intraband != null) {
				intraband.close();
			}

			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

}