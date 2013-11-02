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

import com.liferay.portal.kernel.servlet.taglib.BaseBodyTagSupport;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.tagext.BodyTag;

/**
 * @author Eduardo Lundgren
 */
public class PositionTagSupport extends BaseBodyTagSupport implements BodyTag {

	public String getPosition() {
		return getPositionValue();
	}

	public boolean isPositionAuto() {
		String position = getPosition();

		if (position.equals(_POSITION_AUTO)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isPositionInLine() {
		String position = getPosition();

		if (position.equals(_POSITION_INLINE)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setPosition(String position) {
		_position = position;
	}

	protected void cleanUp() {
		_position = null;
	}

	protected String getPositionValue() {
		HttpServletRequest request =
			(HttpServletRequest)pageContext.getRequest();

		String position = _position;

		String fragmentId = ParamUtil.getString(request, "p_f_id");

		if (Validator.isNotNull(fragmentId)) {
			position = _POSITION_INLINE;
		}

		if (Validator.isNull(position)) {
			ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
				WebKeys.THEME_DISPLAY);

			if (themeDisplay.isStateExclusive() || themeDisplay.isIsolated()) {
				position = _POSITION_INLINE;
			}
			else {
				position = _POSITION_AUTO;
			}
		}

		return position;
	}

	private static final String _POSITION_AUTO = "auto";

	private static final String _POSITION_INLINE = "inline";

	private String _position;

}