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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.util.IncludeTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class WriteTag extends IncludeTag {

	public void setBean(Object bean) {
		_bean = bean;
	}

	public void setProperty(String property) {
		_property = property;
	}

	@Override
	protected void cleanUp() {
		_bean = null;
		_property = null;
	}

	@Override
	protected String getPage() {
		if ((_bean == null) || Validator.isNull(_property)) {
			return null;
		}
		else {
			return _PAGE;
		}
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:write:bean", _bean);
		request.setAttribute("liferay-ui:write:property", _property);
	}

	private static final String _PAGE = "/html/taglib/ui/write/page.jsp";

	private Object _bean;
	private String _property;

}