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
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

/**
 * @author Brian Wing Shun Chan
 */
public class TextSearchEntry extends SearchEntry {

	@Override
	public Object clone() {
		TextSearchEntry textSearchEntry = new TextSearchEntry();

		BeanPropertiesUtil.copyProperties(this, textSearchEntry);

		return textSearchEntry;
	}

	public Map<String, Object> getData() {
		return _data;
	}

	public String getHref() {
		return _href;
	}

	public String getName() {
		return _name;
	}

	public String getName(PageContext pageContext) {
		return getName();
	}

	public String getTarget() {
		return _target;
	}

	public String getTitle() {
		return _title;
	}

	@Override
	public void print(PageContext pageContext) throws Exception {
		if (_href == null) {
			pageContext.getOut().print(getName(pageContext));
		}
		else {
			StringBundler sb = new StringBundler();

			sb.append("<a");

			if (_data != null) {
				for (Map.Entry<String, Object> entry : _data.entrySet()) {
					String key = entry.getKey();
					String value = String.valueOf(entry.getValue());

					sb.append(" data-");
					sb.append(key);
					sb.append("=\"");
					sb.append(value);
					sb.append("\"");
				}
			}

			sb.append(" href=\"");

			if (_href.startsWith("javascript:")) {
				sb.append(_href);
			}
			else {
				sb.append(HtmlUtil.escape(_href));
			}

			sb.append("\"");

			if (Validator.isNotNull(_target)) {
				sb.append(" target=\"");
				sb.append(_target);
				sb.append("\"");
			}

			if (Validator.isNotNull(_title)) {
				sb.append(" title=\"");
				sb.append(_title);
				sb.append("\"");
			}

			sb.append(">");
			sb.append(getName(pageContext));
			sb.append("</a>");

			JspWriter jspWriter = pageContext.getOut();

			jspWriter.print(sb.toString());
		}
	}

	public void setData(Map<String, Object> data) {
		_data = data;
	}

	public void setHref(String href) {
		_href = href;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setTarget(String target) {
		_target = target;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private Map<String, Object> _data;
	private String _href;
	private String _name;
	private String _target;
	private String _title;

}