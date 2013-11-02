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
import javax.servlet.jsp.JspWriter;

/**
 * @author Brian Wing Shun Chan
 */
public class JournalContentSearchTag extends IncludeTag {

	public void setShowListed(boolean showListed) {
		_showListed = showListed;
	}

	public void setTargetPortletId(String targetPortletId) {
		_targetPortletId = targetPortletId;
	}

	public void setType(String type) {
		_type = type;
	}

	@Override
	protected void cleanUp() {
		_showListed = true;
		_targetPortletId = null;
		_type = null;
	}

	@Override
	protected String getEndPage() {
		return _END_PAGE;
	}

	@Override
	protected String getStartPage() {
		return _START_PAGE;
	}

	@Override
	protected int processEndTag() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("</form>");

		return EVAL_PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:journal-content-search:showListed",
			String.valueOf(_showListed));
		request.setAttribute(
			"liferay-ui:journal-content-search:targetPortletId",
			_targetPortletId);
		request.setAttribute("liferay-ui:journal-content-search:type", _type);
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/journal_content_search/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/journal_content_search/start.jsp";

	private boolean _showListed;
	private String _targetPortletId;
	private String _type;

}