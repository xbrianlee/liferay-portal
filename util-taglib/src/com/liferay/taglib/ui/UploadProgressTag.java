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
 * @author Keith R. Davis
 * @author Iliyan Peychev
 */
public class UploadProgressTag extends IncludeTag {

	public void setHeight(int height) {
		_height = height;
	}

	public void setId(String id) {
		_id = id;
	}

	public void setMessage(String message) {
		_message = message;
	}

	public void setRedirect(String redirect) {
		_redirect = redirect;
	}

	public void setUpdatePeriod(Integer updatePeriod) {
		_updatePeriod = updatePeriod;
	}

	@Override
	protected void cleanUp() {
		_height = 25;
		_id = null;
		_message = null;
		_redirect = null;
		_updatePeriod = 1000;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:progress:id", _id);
		request.setAttribute("liferay-ui:progress:height", _height);
		request.setAttribute("liferay-ui:progress:message", _message);
		request.setAttribute("liferay-ui:progress:redirect", _redirect);
		request.setAttribute("liferay-ui:progress:updatePeriod", _updatePeriod);
	}

	private static final String _PAGE = "/html/taglib/ui/progress/page.jsp";

	private Integer _height;
	private String _id;
	private String _message;
	private String _redirect;
	private Integer _updatePeriod;

}