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

package com.liferay.portal.servlet.filters.jsoncontenttype;

import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringUtil;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author Hugo Huijser
 * @author Igor Spasic
 */
public class JSONContentTypeResponse extends HttpServletResponseWrapper {

	public JSONContentTypeResponse(HttpServletResponse response) {
		super(response);
	}

	@Override
	public void setContentType(String contentType) {
		if (StringUtil.equalsIgnoreCase(
				contentType, ContentTypes.APPLICATION_JSON)) {

			contentType = ContentTypes.TEXT_JAVASCRIPT;
		}

		super.setContentType(contentType);
	}

}