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

import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sergio González
 */
public class TrashEmptyTag extends IncludeTag {

	@Override
	public int doStartTag() {
		return EVAL_BODY_INCLUDE;
	}

	public void setConfirmMessage(String confirmMessage) {
		_confirmMessage = confirmMessage;
	}

	public void setEmptyMessage(String emptyMessage) {
		_emptyMessage = emptyMessage;
	}

	public void setInfoMessage(String infoMessage) {
		_infoMessage = infoMessage;
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL.toString();
	}

	public void setPortletURL(String portletURL) {
		_portletURL = portletURL;
	}

	public void setTotalEntries(int totalEntries) {
		_totalEntries = totalEntries;
	}

	@Override
	protected void cleanUp() {
		_confirmMessage = _CONFIRM_MESSAGE;
		_emptyMessage = _EMPTY_MESSAGE;
		_infoMessage = _INFO_MESSAGE;
		_portletURL = null;
		_totalEntries = 0;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:trash-empty:confirmMessage", _confirmMessage);
		request.setAttribute(
			"liferay-ui:trash-empty:emptyMessage", _emptyMessage);
		request.setAttribute(
			"liferay-ui:trash-empty:infoMessage", _infoMessage);
		request.setAttribute("liferay-ui:trash-empty:portletURL", _portletURL);
		request.setAttribute(
			"liferay-ui:trash-empty:totalEntries", _totalEntries);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

	private static final String _CONFIRM_MESSAGE =
		"are-you-sure-you-want-to-empty-the-recycle-bin";

	private static final String _EMPTY_MESSAGE = "empty-the-recycle-bin";

	private static final String _INFO_MESSAGE =
		"entries-that-have-been-in-the-recycle-bin-for-more-than-x-will-be-" +
			"automatically-deleted";

	private static final String _PAGE = "/html/taglib/ui/trash_empty/page.jsp";

	private String _confirmMessage = _CONFIRM_MESSAGE;
	private String _emptyMessage = _EMPTY_MESSAGE;
	private String _infoMessage = _INFO_MESSAGE;
	private String _portletURL;
	private int _totalEntries;

}