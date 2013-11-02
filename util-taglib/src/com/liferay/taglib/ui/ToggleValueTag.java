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

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.util.SessionClicks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @author Brian Wing Shun Chan
 */
public class ToggleValueTag extends TagSupport {

	/**
	 * @deprecated As of 6.1.0
	 */
	public static void doTag(
			String id, PageContext pageContext, HttpServletRequest request)
		throws Exception {

		doTag(id, "block", pageContext);
	}

	public static void doTag(
			String id, String defaultValue, PageContext pageContext)
		throws Exception {

		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		String value = SessionClicks.get(request, id, StringPool.BLANK);

		if (value.equals(StringPool.BLANK)) {
			value = defaultValue;
		}

		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write(value);
	}

	@Override
	public int doEndTag() throws JspException {
		try {
			doTag(_id, _defaultValue, pageContext);

			return EVAL_PAGE;
		}
		catch (Exception e) {
			throw new JspException(e);
		}
	}

	public void setDefaultValue(String defaultValue) {
		_defaultValue = defaultValue;
	}

	@Override
	public void setId(String id) {
		_id = id;
	}

	private String _defaultValue = "block";
	private String _id;

}