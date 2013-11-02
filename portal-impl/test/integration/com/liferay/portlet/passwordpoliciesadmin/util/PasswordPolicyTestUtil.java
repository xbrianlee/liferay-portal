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

package com.liferay.portlet.passwordpoliciesadmin.util;

import com.liferay.portal.model.PasswordPolicy;
import com.liferay.portal.service.PasswordPolicyLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;

/**
 * @author Daniela Zapata Riesco
 */
public class PasswordPolicyTestUtil {

	public static PasswordPolicy addPasswordPolicy(
			ServiceContext serviceContext)
		throws Exception {

		return PasswordPolicyLocalServiceUtil.addPasswordPolicy(
			serviceContext.getUserId(), ServiceTestUtil.randomBoolean(),
			ServiceTestUtil.randomString(), ServiceTestUtil.randomString(),
			ServiceTestUtil.randomBoolean(), ServiceTestUtil.randomBoolean(),
			ServiceTestUtil.randomLong(), ServiceTestUtil.randomBoolean(),
			ServiceTestUtil.randomBoolean(), ServiceTestUtil.nextInt(),
			ServiceTestUtil.nextInt(), ServiceTestUtil.nextInt(),
			ServiceTestUtil.nextInt(), ServiceTestUtil.nextInt(),
			ServiceTestUtil.nextInt(), "(?=.{4})(?:[a-zA-Z0-9]*)",
			ServiceTestUtil.randomBoolean(), ServiceTestUtil.nextInt(),
			ServiceTestUtil.randomBoolean(), ServiceTestUtil.randomLong(),
			ServiceTestUtil.randomLong(), ServiceTestUtil.nextInt(),
			ServiceTestUtil.randomBoolean(), ServiceTestUtil.nextInt(),
			ServiceTestUtil.randomLong(), ServiceTestUtil.randomLong(),
			ServiceTestUtil.randomLong(), serviceContext);
	}

}