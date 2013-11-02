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

package com.liferay.portal.test;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Shuyang Zhou
 */
@RunWith(AspectJMockingNewJVMJUnitTestRunner.class)
public class AspectJMockingNewJVMJUnitTestRunnerTest {

	@Test
	public void testStaticUtil() {
		Assert.assertEquals(1, StaticUtil.getValue1());
		Assert.assertEquals(2, StaticUtil.getValue2());
	}

	@AdviseWith(adviceClasses = {AdviceClass1.class})
	@Test
	public void testStaticUtilMocking1() {
		Assert.assertEquals(3, StaticUtil.getValue1());
		Assert.assertEquals(2, StaticUtil.getValue2());
	}

	@AdviseWith(adviceClasses = {AdviceClass2.class})
	@Test
	public void testStaticUtilMocking2() {
		Assert.assertEquals(1, StaticUtil.getValue1());
		Assert.assertEquals(4, StaticUtil.getValue2());
	}

	@AdviseWith(adviceClasses = {AdviceClass1.class, AdviceClass2.class})
	@Test
	public void testStaticUtilMocking3() {
		Assert.assertEquals(3, StaticUtil.getValue1());
		Assert.assertEquals(4, StaticUtil.getValue2());
	}

	@AdviseWith(adviceClasses = {AdviceClass3.class})
	@Test
	public void testStaticUtilMocking4() {
		Assert.assertEquals(5, StaticUtil.getValue1());

		try {
			StaticUtil.getValue2();

			Assert.fail();
		}
		catch (IllegalStateException ise) {
		}
	}

	@Aspect
	private static class AdviceClass1 {

		@Around("execution(* *.getValue1())")
		public Object mockGetValue() {
			return 3;
		}

	}

	@Aspect
	private static class AdviceClass2 {

		@Around("execution(* *.getValue2())")
		public Object mockGetValue() {
			return 4;
		}

	}

	@Aspect
	private static class AdviceClass3 {

		@Around("execution(* *.getValue1())")
		public Object mockGetValue1() {
			return 5;
		}

		@Around("execution(* *.getValue2())")
		public Object mockGetValue2() {
			throw new IllegalStateException();
		}

	}

	private static class StaticUtil {

		public static int getValue1() {
			return 1;
		}

		public static int getValue2() {
			return 2;
		}

	}

}