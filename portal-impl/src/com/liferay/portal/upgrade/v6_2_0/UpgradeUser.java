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

package com.liferay.portal.upgrade.v6_2_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class UpgradeUser extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		long classNameId = PortalUtil.getClassNameId(User.class);

		runSQL("update Contact_ set classNameId = " + classNameId);

		StringBundler sb = new StringBundler(4);

		sb.append("update Contact_ set classPK = (select User_.userId from ");
		sb.append("User_ where User_.contactId = Contact_.contactId), ");
		sb.append("emailAddress = (select User_.emailAddress from User_ ");
		sb.append("where User_.contactId = Contact_.contactId)");

		runSQL(sb.toString());
	}

}