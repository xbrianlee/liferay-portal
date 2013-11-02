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

package com.liferay.portal.util.comparator;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.Comparator;
import java.util.Locale;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class PortletTitleComparator
	implements Comparator<Portlet>, Serializable {

	public PortletTitleComparator(Locale locale) {
		_locale = locale;
	}

	public PortletTitleComparator(
		ServletContext servletContext, Locale locale) {

		_servletContext = servletContext;
		_locale = locale;
	}

	@Override
	public int compare(Portlet portlet1, Portlet portlet2) {
		String portletTitle1 = StringPool.BLANK;
		String portletTitle2 = StringPool.BLANK;

		if (_servletContext != null) {
			portletTitle1 = PortalUtil.getPortletTitle(
				portlet1, _servletContext, _locale);
			portletTitle2 = PortalUtil.getPortletTitle(
				portlet2, _servletContext, _locale);
		}
		else {
			portletTitle1 = PortalUtil.getPortletTitle(portlet1, _locale);
			portletTitle2 = PortalUtil.getPortletTitle(portlet2, _locale);
		}

		return portletTitle1.compareTo(portletTitle2);
	}

	private Locale _locale;
	private ServletContext _servletContext;

}