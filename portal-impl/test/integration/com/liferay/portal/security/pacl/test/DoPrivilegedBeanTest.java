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
import com.liferay.portal.security.lang.DoPrivilegedUtil;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class DoPrivilegedBeanTest {

	@Test
	public void testEquals() throws Exception {
		String className = "TEST_CLASS_NAME";

		ExpandoBridge expandoBridge1 = DoPrivilegedUtil.wrap(
			new ExpandoBridgeImpl(TestPropsValues.getCompanyId(), className));

		ExpandoBridge expandoBridge2 = DoPrivilegedUtil.wrap(
			new ExpandoBridgeImpl(TestPropsValues.getCompanyId(), className));

		Assert.assertTrue(expandoBridge1.equals(expandoBridge2));
	}

}