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

import com.liferay.portal.kernel.portlet.PortletBagPool;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portlet.PortletBagImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class PortletBagPoolTest {

	@Test
	public void test1() throws Exception {
		try {
			PortletBagPool.get("1_WAR_flashportlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test2() throws Exception {
		try {
			PortletBagPool.get("fail");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test3() throws Exception {
		try {
			PortletBagPool.get("flash-portlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test4() throws Exception {
		try {
			PortletBagPool.put(
				"1_WAR_flashportlet",
				new PortletBagImpl(
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null));
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test5() throws Exception {
		try {
			PortletBagPool.put(
				"fail",
				new PortletBagImpl(
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null));

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test6() throws Exception {
		try {
			PortletBagPool.put(
				"flash-portlet",
				new PortletBagImpl(
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null, null, null,
					null, null, null, null, null, null, null, null));
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test7() throws Exception {
		try {
			PortletBagPool.remove("1_WAR_flashportlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test8() throws Exception {
		try {
			PortletBagPool.remove("fail");

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test9() throws Exception {
		try {
			PortletBagPool.remove("flash-portlet");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test10() throws Exception {
		try {
			PortletBagPool.reset();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

}