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

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Vilmos Papp
 */
public class FileEntryDisplayTerms extends DisplayTerms {

	public static final String DOCUMENT = "document";

	public static final String DOWNLOADS = "downloads";

	public static final String LOCKED = "locked";

	public static final String SELECTED_GROUP_ID = "selectedGroupId";

	public static final String SIZE = "size";

	public FileEntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		document = ParamUtil.getString(portletRequest, DOCUMENT);
		downloads = ParamUtil.getInteger(portletRequest, DOWNLOADS);
		keywords = ParamUtil.getString(portletRequest, DisplayTerms.KEYWORDS);
		locked = ParamUtil.getBoolean(portletRequest, LOCKED);
		selectedGroupId = ParamUtil.getLong(portletRequest, SELECTED_GROUP_ID);
		size = ParamUtil.getString(portletRequest, SIZE);
	}

	public String getDocument() {
		return document;
	}

	public int getDownloads() {
		return downloads;
	}

	public long getSelectedGroupId() {
		return selectedGroupId;
	}

	public String getSize() {
		return size;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setSelectedGroupId(long selectedGroupId) {
		this.selectedGroupId = selectedGroupId;
	}

	protected String document;
	protected int downloads;
	protected boolean locked;
	protected long selectedGroupId;
	protected String size;

}