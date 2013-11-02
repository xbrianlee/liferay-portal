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

package com.liferay.taglib.aui;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.taglib.aui.base.BaseButtonTag;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Julio Camarero
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public class ButtonTag extends BaseButtonTag {

	@Override
	public void setIconAlign(String iconAlign) {
		if (iconAlign != null) {
			super.setIconAlign(StringUtil.toLowerCase(iconAlign));
		}
	}

	@Override
	protected boolean isCleanUpSetAttributes() {
		return _CLEAN_UP_SET_ATTRIBUTES;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		super.setAttributes(request);

		String value = getValue();

		if (Validator.isNull(value)) {
			String type = getType();

			if (type.equals("submit")) {
				value = "save";
			}
			else if (type.equals("cancel")) {
				value = "cancel";
			}
			else if (type.equals("reset")) {
				value = "reset";
			}
		}

		setNamespacedAttribute(request, "value", value);
	}

	private static final boolean _CLEAN_UP_SET_ATTRIBUTES = true;

}