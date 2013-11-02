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

package com.liferay.portlet.trash.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Sergio González
 */
public class EntryDisplayTerms extends DisplayTerms {

	public static final String NAME = "name";

	public static final String REMOVED_BY = "removedBy";

	public static final String REMOVED_DATE = "removedDate";

	public static final String TYPE = "type";

	public EntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		removedDate = ParamUtil.getString(portletRequest, REMOVED_DATE);
		removedBy = ParamUtil.getString(portletRequest, REMOVED_BY);
		type = ParamUtil.getString(portletRequest, TYPE);
	}

	public String getName() {
		return name;
	}

	public String getRemovedBy() {
		return removedBy;
	}

	public String getRemovedDate() {
		return removedDate;
	}

	public String getType() {
		return type;
	}

	protected String name;
	protected String removedBy;
	protected String removedDate;
	protected String type;

}