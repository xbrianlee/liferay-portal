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

package com.liferay.taglib.util;

import com.liferay.portal.kernel.servlet.taglib.TagSupport;

import javax.servlet.jsp.JspException;

/**
 * @author Brian Wing Shun Chan
 */
public class PropertyTag extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		PropertyAncestorTag propertyAncestorTag =
			(PropertyAncestorTag)findAncestorWithClass(
				this, PropertyAncestorTag.class);

		if (propertyAncestorTag == null) {
			throw new JspException();
		}

		propertyAncestorTag.addProperty(_name, _value);

		return SKIP_BODY;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValue(String value) {
		_value = value;
	}

	private String _name;
	private String _value;

}