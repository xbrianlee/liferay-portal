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

package com.liferay.portlet.journal.search;

import com.liferay.portal.kernel.dao.search.DAOParamUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Vilmos Papp
 */
public class FileEntrySearchTerms extends FileEntryDisplayTerms {

	public FileEntrySearchTerms(PortletRequest portletRequest) {
		super(portletRequest);

		document = DAOParamUtil.getString(portletRequest, DOCUMENT);
		downloads = ParamUtil.getInteger(portletRequest, DOWNLOADS);
		locked = DAOParamUtil.getBoolean(portletRequest, LOCKED);
		selectedGroupId = DAOParamUtil.getLong(
			portletRequest, SELECTED_GROUP_ID);
		size = DAOParamUtil.getString(portletRequest, SIZE);
	}

	@Override
	public void setSelectedGroupId(long selectedGroupId) {
		this.selectedGroupId = selectedGroupId;
	}

}