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

package com.liferay.portlet.assetpublisher.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Julio Camarero
 */
public class AssetSearchTerms extends AssetDisplayTerms {

	public AssetSearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		description = DAOParamUtil.getString(portletRequest, DESCRIPTION);
		groupId = ParamUtil.getLong(portletRequest, GROUP_ID);
		title = DAOParamUtil.getString(portletRequest, TITLE);
		userName = DAOParamUtil.getString(portletRequest, USER_NAME);
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

}