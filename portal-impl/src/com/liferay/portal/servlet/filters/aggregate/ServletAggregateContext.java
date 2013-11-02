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

package com.liferay.portal.servlet.filters.aggregate;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletContext;

/**
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public class ServletAggregateContext extends BaseAggregateContext {

	public ServletAggregateContext(
			ServletContext servletContext, String resourcePath)
		throws IOException {

		_servletContext = servletContext;

		String rootPath = ServletContextUtil.getRootPath(_servletContext);

		int pos = resourcePath.lastIndexOf(StringPool.SLASH);

		if (pos > 0) {
			resourcePath = resourcePath.substring(0, resourcePath.length() - 1);
		}

		pos = resourcePath.lastIndexOf(StringPool.SLASH);

		resourcePath = resourcePath.substring(0, pos);

		pos = resourcePath.lastIndexOf(rootPath);

		if (pos == 0) {
			resourcePath = resourcePath.substring(rootPath.length());
		}

		pushPath(resourcePath);
	}

	@Override
	public String getContent(String path) {
		try {
			String fullPath = getFullPath(StringPool.BLANK);

			URL resourceURL = null;

			if (Validator.isUrl(path)) {
				resourceURL = new URL(path);
			}
			else {
				resourceURL = _servletContext.getResource(
					fullPath.concat(path));
			}

			if (resourceURL == null) {
				return null;
			}

			URLConnection urlConnection = resourceURL.openConnection();

			return StringUtil.read(urlConnection.getInputStream());
		}
		catch (IOException ioe) {
			_log.error(ioe, ioe);
		}

		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ServletAggregateContext.class);

	private ServletContext _servletContext;

}