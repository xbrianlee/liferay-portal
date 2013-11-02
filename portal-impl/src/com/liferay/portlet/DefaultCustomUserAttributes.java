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

package com.liferay.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.RandomUtil;

import java.util.Map;

/**
 * <p>
 * A separate instance of this class is created every time
 * <code>renderRequest.getAttribute(PortletRequest.USER_INFO)</code> is called.
 * It is safe to cache attributes in this instance because you can assume that
 * all calls to this instance belong to the same user.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class DefaultCustomUserAttributes implements CustomUserAttributes {

	@Override
	public Object clone() {
		return new DefaultCustomUserAttributes();
	}

	@Override
	public String getValue(String name, Map<String, String> userInfo) {
		if (name == null) {
			return null;
		}

		if (_log.isDebugEnabled()) {
			String companyId = userInfo.get(UserAttributes.LIFERAY_COMPANY_ID);
			String userId = userInfo.get(UserAttributes.LIFERAY_USER_ID);

			_log.debug("Company id " + companyId);
			_log.debug("User id " + userId);
		}

		if (name.equals("user.name.random")) {
			String[] names = new String[] {"Aaa", "Bbb", "Ccc"};

			return names[RandomUtil.nextInt(3)];
		}
		else {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultCustomUserAttributes.class);

}