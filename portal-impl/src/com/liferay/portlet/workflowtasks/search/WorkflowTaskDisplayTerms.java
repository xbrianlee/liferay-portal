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

package com.liferay.portlet.workflowtasks.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Marcellus Tavares
 */
public class WorkflowTaskDisplayTerms extends DisplayTerms {

	public static final String NAME = "name";

	public static final String TYPE = "type";

	public WorkflowTaskDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		name = ParamUtil.getString(portletRequest, NAME);
		type = ParamUtil.getString(portletRequest, TYPE);
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	protected String name;
	protected String type;

}