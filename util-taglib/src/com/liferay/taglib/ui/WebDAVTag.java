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
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class WebDAVTag extends IncludeTag {

	public void setPath(String path) {
		_path = path;
	}

	@Override
	protected void cleanUp() {
		_path = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:webdav:path", _path);
	}

	private static final String _PAGE = "/html/taglib/ui/webdav/page.jsp";

	private String _path;

}