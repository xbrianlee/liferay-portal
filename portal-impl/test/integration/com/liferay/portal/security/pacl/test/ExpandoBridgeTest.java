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

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupWrapper;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserWrapper;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.TestPropsValues;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class ExpandoBridgeTest {

	@Test
	public void test1() throws Exception {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				TestPropsValues.getCompanyId());

			group.getExpandoBridge();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test2() throws Exception {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				TestPropsValues.getCompanyId());

			ServiceContext serviceContext = new ServiceContext();

			group.setExpandoBridgeAttributes(serviceContext);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test3() throws Exception {
		try {
			Group group = GroupLocalServiceUtil.getCompanyGroup(
				TestPropsValues.getCompanyId());

			group = new GroupWrapper(group);

			ServiceContext serviceContext = new ServiceContext();

			group.setExpandoBridgeAttributes(serviceContext);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void test4() throws Exception {
		try {
			User user = TestPropsValues.getUser();

			user.getExpandoBridge();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test5() throws Exception {
		try {
			User user = TestPropsValues.getUser();

			ServiceContext serviceContext = new ServiceContext();

			user.setExpandoBridgeAttributes(serviceContext);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test6() throws Exception {
		try {
			User user = TestPropsValues.getUser();

			user = new UserWrapper(user);

			user.getExpandoBridge();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void test7() throws Exception {
		try {
			User user = TestPropsValues.getUser();

			user = new UserWrapper(user);

			ServiceContext serviceContext = new ServiceContext();

			user.setExpandoBridgeAttributes(serviceContext);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

}