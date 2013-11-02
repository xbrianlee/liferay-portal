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

import com.liferay.portal.kernel.servlet.PortalIncludeUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class UserDisplayTag extends TagSupport {

	@Override
	public int doEndTag() throws JspException {
		try {
			PortalIncludeUtil.include(pageContext, getEndPage());

			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			request.removeAttribute("liferay-ui:user-display:url");

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	@Override
	public int doStartTag() throws JspException {
		try {
			HttpServletRequest request =
				(HttpServletRequest)pageContext.getRequest();

			request.setAttribute(
				"liferay-ui:user-display:user-id", String.valueOf(_userId));
			request.setAttribute(
				"liferay-ui:user-display:user-name", _userName);

			User user = UserLocalServiceUtil.fetchUserById(_userId);

			if (user != null) {
				if (user.isDefaultUser()) {
					user = null;
				}

				request.setAttribute("liferay-ui:user-display:user", user);

				pageContext.setAttribute("userDisplay", user);
			}
			else {
				request.removeAttribute("liferay-ui:user-display:user");

				pageContext.removeAttribute("userDisplay");
			}

			request.setAttribute("liferay-ui:user-display:url", _url);
			request.setAttribute(
				"liferay-ui:user-display:displayStyle",
				String.valueOf(_displayStyle));

			PortalIncludeUtil.include(pageContext, getStartPage());

			if (user != null) {
				return EVAL_BODY_INCLUDE;
			}
			else {
				return SKIP_BODY;
			}
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setDisplayStyle(int displayStyle) {
		_displayStyle = displayStyle;
	}

	public void setEndPage(String endPage) {
		_endPage = endPage;
	}

	public void setStartPage(String startPage) {
		_startPage = startPage;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	protected String getEndPage() {
		if (Validator.isNull(_endPage)) {
			return _END_PAGE;
		}
		else {
			return _endPage;
		}
	}

	protected String getStartPage() {
		if (Validator.isNull(_startPage)) {
			return _START_PAGE;
		}
		else {
			return _startPage;
		}
	}

	private static final String _END_PAGE =
		"/html/taglib/ui/user_display/end.jsp";

	private static final String _START_PAGE =
		"/html/taglib/ui/user_display/start.jsp";

	private int _displayStyle = 1;
	private String _endPage;
	private String _startPage;
	private String _url;
	private long _userId;
	private String _userName;

}