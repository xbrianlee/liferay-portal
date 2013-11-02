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

package com.liferay.portlet.usersadmin.action;

import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class FindUserAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		return 0;
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "p_u_i_d";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		return "/directory/view_user";
	}

	@Override
	protected String[] initPortletIds() {
		return new String[] {PortletKeys.DIRECTORY};
	}

}