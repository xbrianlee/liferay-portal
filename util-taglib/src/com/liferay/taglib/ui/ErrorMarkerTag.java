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
public class ErrorMarkerTag extends IncludeTag {

	public void setKey(String key) {
		_key = key;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Override
	protected void cleanUp() {
		_key = null;
		_value = null;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		if (Validator.isNotNull(_key) && Validator.isNotNull(_value)) {
			request.setAttribute("liferay-ui:error-marker:key", _key);
			request.setAttribute("liferay-ui:error-marker:value", _value);
		}
		else {
			request.removeAttribute("liferay-ui:error-marker:key");
			request.removeAttribute("liferay-ui:error-marker:value");
		}
	}

	private String _key;
	private String _value;

}