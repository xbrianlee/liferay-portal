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

package com.liferay.portlet.blogsadmin.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Juan Fernández
 */
public class EntryDisplayTerms extends DisplayTerms {

	public static final String AUTHOR = "author";

	public static final String STATUS = "status";

	public static final String TITLE = "title";

	public EntryDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		author = ParamUtil.getString(portletRequest, AUTHOR);
		status = ParamUtil.getString(portletRequest, STATUS);
		title = ParamUtil.getString(portletRequest, TITLE);
	}

	public String getAuthor() {
		return author;
	}

	public String getStatus() {
		return status;
	}

	public String getTitle() {
		return title;
	}

	protected String author;
	protected String status;
	protected String title;

}