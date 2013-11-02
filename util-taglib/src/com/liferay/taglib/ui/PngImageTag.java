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
 * @author Brian Wing Shun Chan
 */
public class PngImageTag extends IncludeTag {

	public void setHeight(String height) {
		_height = height;
	}

	public void setImage(String image) {
		_image = image;
	}

	public void setWidth(String width) {
		_width = width;
	}

	@Override
	protected void cleanUp() {
		_height = null;
		_image = null;
		_width = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute("liferay-ui:png_image:height", _height);
		request.setAttribute("liferay-ui:png_image:image", _image);
		request.setAttribute("liferay-ui:png_image:width", _width);
	}

	private static final String _PAGE = "/html/taglib/ui/png_image/page.jsp";

	private String _height;
	private String _image;
	private String _width;

}