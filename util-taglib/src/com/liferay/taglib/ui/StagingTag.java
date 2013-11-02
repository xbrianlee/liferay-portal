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

package com.liferay.taglib.ui;

import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class StagingTag extends IncludeTag {

	public void setCssClass(String cssClass) {
		_cssClass = cssClass;
	}

	public void setExtended(boolean extended) {
		_extended = extended;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public void setIcon(String icon) {
		_icon = icon;
	}

	public void setLayoutSetBranchId(long layoutSetBranchId) {
		_layoutSetBranchId = layoutSetBranchId;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setOnlyActions(boolean onlyActions) {
		_onlyActions = onlyActions;
	}

	public void setPrivateLayout(boolean privateLayout) {
		_privateLayout = privateLayout;
	}

	public void setSelPlid(long selPlid) {
		_selPlid = selPlid;
	}

	public void setShowManageBranches(boolean showManageBranches) {
		_showManageBranches = showManageBranches;
	}

	@Override
	protected void cleanUp() {
		_cssClass = null;
		_extended = true;
		_groupId = 0;
		_icon = "/dockbar/staging.png";
		_layoutSetBranchId = 0;
		_message = "staging";
		_onlyActions = false;
		_privateLayout = false;
		_selPlid = 0;
		_showManageBranches = false;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:staging:cssClass", _cssClass);
		request.setAttribute(
			"liferay-ui:staging:extended", String.valueOf(_extended));
		request.setAttribute(
			"liferay-ui:staging:groupId", String.valueOf(_groupId));
		request.setAttribute("liferay-ui:staging:icon", _icon);
		request.setAttribute(
			"liferay-ui:staging:layoutSetBranchId",
			String.valueOf(_layoutSetBranchId));
		request.setAttribute("liferay-ui:staging:message", _message);
		request.setAttribute(
			"liferay-ui:staging:onlyActions", String.valueOf(_onlyActions));
		request.setAttribute(
			"liferay-ui:staging:privateLayout", String.valueOf(_privateLayout));
		request.setAttribute(
			"liferay-ui:staging:selPlid", String.valueOf(_selPlid));
		request.setAttribute(
			"liferay-ui:staging:showManageBranches",
			String.valueOf(_showManageBranches));
	}

	private static final String _PAGE = "/html/taglib/ui/staging/page.jsp";

	private String _cssClass;
	private boolean _extended = true;
	private long _groupId;
	private String _icon = "/dockbar/staging.png";
	private long _layoutSetBranchId;
	private String _message = "staging";
	private boolean _onlyActions;
	private boolean _privateLayout;
	private long _selPlid;
	private boolean _showManageBranches;

}