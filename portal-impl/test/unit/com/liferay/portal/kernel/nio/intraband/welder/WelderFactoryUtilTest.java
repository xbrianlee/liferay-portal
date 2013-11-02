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

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.Intraband;
import com.liferay.portal.kernel.nio.intraband.RegistrationReference;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.test.CodeCoverageAssertor;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.test.AdviseWith;
import com.liferay.portal.test.AspectJMockingNewClassLoaderJUnitTestRunner;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(AspectJMockingNewClassLoaderJUnitTestRunner.class)
public class WelderFactoryUtilTest {

	@ClassRule
	public static CodeCoverageAssertor codeCoverageAssertor =
		new CodeCoverageAssertor();

	@Test
	public void testCreateWelder() {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, MockWelder.class.getName());

		try {
			Welder welder = WelderFactoryUtil.createWelder();

			Assert.assertNotNull(welder);
			Assert.assertSame(MockWelder.class, welder.getClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testCreateWelderFailed() {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, PrivateMockWelder.class.getName());

		try {
			WelderFactoryUtil.createWelder();

			Assert.fail();
		}
		catch (RuntimeException re) {
			Assert.assertTrue(re.getCause() instanceof IllegalAccessException);
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testGetWelderClassClassNotFound() {
		System.setProperty(PropsKeys.INTRABAND_WELDER_IMPL, "NoSuchClass");

		try {
			WelderFactoryUtil.getWelderClass();

			Assert.fail();
		}
		catch (RuntimeException re) {
			Assert.assertTrue(re.getCause() instanceof ClassNotFoundException);
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@Test
	public void testGetWelderClassCustomizedImpl() {
		System.setProperty(
			PropsKeys.INTRABAND_WELDER_IMPL, MockWelder.class.getName());

		try {
			Assert.assertSame(
				MockWelder.class, WelderFactoryUtil.getWelderClass());
		}
		finally {
			System.clearProperty(PropsKeys.INTRABAND_WELDER_IMPL);
		}
	}

	@AdviseWith(adviceClasses = {FIFOUtilAdvice.class, OSDetectorAdvice.class})
	@Test
	public void testGetWelderClassOnNonWindowsWithFIFO() {
		FIFOUtilAdvice._fifoSupported = true;
		OSDetectorAdvice._windows = false;

		Assert.assertSame(FIFOWelder.class, WelderFactoryUtil.getWelderClass());
	}

	@AdviseWith(adviceClasses = {FIFOUtilAdvice.class, OSDetectorAdvice.class})
	@Test
	public void testGetWelderClassOnNonWindowsWithoutFIFO() {
		FIFOUtilAdvice._fifoSupported = false;
		OSDetectorAdvice._windows = false;

		Assert.assertSame(
			SocketWelder.class, WelderFactoryUtil.getWelderClass());
	}

	@AdviseWith(adviceClasses = {OSDetectorAdvice.class})
	@Test
	public void testGetWelderClassOnWindows() {
		OSDetectorAdvice._windows = true;

		Assert.assertSame(
			SocketWelder.class, WelderFactoryUtil.getWelderClass());
	}

	@Aspect
	public static class FIFOUtilAdvice {

		@Around(
			"execution(public static boolean com.liferay.portal.kernel." +
				"nio.intraband.welder.fifo.FIFOUtil.isFIFOSupported())")
		public boolean isFIFOSupported() {
			return _fifoSupported;
		}

		private static boolean _fifoSupported;

	}

	@Aspect
	public static class OSDetectorAdvice {

		@Around(
			"execution(public static boolean com.liferay.portal.kernel.util." +
				"OSDetector.isWindows())")
		public boolean isWindows() {
			return _windows;
		}

		private static boolean _windows;

	}

	protected static class MockWelder implements Welder {

		@Override
		public void destroy() {
		}

		@Override
		public RegistrationReference weld(Intraband intraband) {
			return null;
		}

	}

	private static class PrivateMockWelder implements Welder {

		@Override
		public void destroy() {
		}

		@Override
		public RegistrationReference weld(Intraband intraband) {
			return null;
		}

	}

}