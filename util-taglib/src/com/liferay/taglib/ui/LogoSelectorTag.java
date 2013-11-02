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
 * @author Julio Camarero
 */
public class LogoSelectorTag extends IncludeTag {

	public void setDefaultLogoURL(String defaultLogoURL) {
		_defaultLogoURL = defaultLogoURL;
	}

	public void setEditLogoURL(String editLogoURL) {
		_editLogoURL = editLogoURL;
	}

	public void setImageId(long imageId) {
		_imageId = imageId;
	}

	public void setLogoDisplaySelector(String logoDisplaySelector) {
		_logoDisplaySelector = logoDisplaySelector;
	}

	public void setShowBackground(boolean showBackground) {
		_showBackground = showBackground;
	}

	@Override
	protected void cleanUp() {
		_defaultLogoURL = null;
		_editLogoURL = null;
		_imageId = 0;
		_logoDisplaySelector = null;
		_showBackground = true;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:logo-selector:defaultLogoURL", _defaultLogoURL);
		request.setAttribute(
			"liferay-ui:logo-selector:editLogoURL", _editLogoURL);
		request.setAttribute(
			"liferay-ui:logo-selector:imageId", String.valueOf(_imageId));
		request.setAttribute(
			"liferay-ui:logo-selector:logoDisplaySelector",
			_logoDisplaySelector);
		request.setAttribute(
			"liferay-ui:logo-selector:showBackground",
			String.valueOf(_showBackground));
	}

	private static final String _PAGE =
		"/html/taglib/ui/logo_selector/page.jsp";

	private String _defaultLogoURL;
	private String _editLogoURL;
	private long _imageId;
	private String _logoDisplaySelector;
	private boolean _showBackground = true;

}