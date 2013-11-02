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

package com.liferay.portal.upgrade.v6_0_12;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.RoleConstants;

/**
 * @author Hugo Huijser
 */
public class UpgradeResourcePermission extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler(11);

		sb.append("update ResourcePermission set scope = ");
		sb.append(ResourceConstants.SCOPE_GROUP_TEMPLATE);
		sb.append(", primKey = '");
		sb.append(String.valueOf(GroupConstants.DEFAULT_PARENT_GROUP_ID));
		sb.append("' where scope = ");
		sb.append(ResourceConstants.SCOPE_COMPANY);
		sb.append(" and primKey = CAST_TEXT(companyId) and exists (select ");
		sb.append("roleId from Role_ where Role_.roleId = ");
		sb.append("ResourcePermission.roleId and Role_.type_ = ");
		sb.append(RoleConstants.TYPE_PROVIDER);
		sb.append(")");

		runSQL(sb.toString());
	}

}