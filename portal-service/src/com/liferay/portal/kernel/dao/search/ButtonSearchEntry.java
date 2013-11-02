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

package com.liferay.portal.kernel.dao.search;

import com.liferay.portal.kernel.bean.BeanPropertiesUtil;

import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class ButtonSearchEntry extends TextSearchEntry {

	@Override
	public Object clone() {
		ButtonSearchEntry buttonSearchEntry = new ButtonSearchEntry();

		BeanPropertiesUtil.copyProperties(this, buttonSearchEntry);

		return buttonSearchEntry;
	}

	@Override
	public void print(PageContext pageContext) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append("<input type=\"button\" ");
		sb.append("value=\"");
		sb.append(getName());
		sb.append("\" onClick=\"");
		sb.append(getHref());
		sb.append("\">");

		pageContext.getOut().print(sb.toString());
	}

}