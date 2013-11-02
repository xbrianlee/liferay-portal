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

package com.liferay.portal.template;

import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.URLTemplateResource;

import java.io.IOException;

import java.net.URL;

/**
 * @author Tina Tian
 */
public abstract class URLResourceParser implements TemplateResourceParser {

	@Override
	public TemplateResource getTemplateResource(String templateId)
		throws TemplateException {

		try {
			URL url = getURL(templateId);

			if (url == null) {
				return null;
			}

			return new URLTemplateResource(templateId, url);
		}
		catch (IOException ioe) {
			throw new TemplateException(ioe);
		}
	}

	public abstract URL getURL(String templateId) throws IOException;

}