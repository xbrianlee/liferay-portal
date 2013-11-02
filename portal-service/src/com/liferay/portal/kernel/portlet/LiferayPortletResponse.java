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

package com.liferay.portal.kernel.portlet;

import java.util.Map;

import javax.portlet.PortletResponse;
import javax.portlet.PortletURL;
import javax.portlet.ResourceURL;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Raymond Augé
 */
public interface LiferayPortletResponse extends PortletResponse {

	public void addDateHeader(String name, long date);

	public void addHeader(String name, String value);

	public void addIntHeader(String name, int value);

	public PortletURL createActionURL();

	public LiferayPortletURL createActionURL(String portletName);

	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle);

	public LiferayPortletURL createLiferayPortletURL(
		long plid, String portletName, String lifecycle,
		boolean includeLinkToLayoutUuid);

	public LiferayPortletURL createLiferayPortletURL(String lifecycle);

	public LiferayPortletURL createLiferayPortletURL(
		String portletName, String lifecycle);

	public PortletURL createRenderURL();

	public LiferayPortletURL createRenderURL(String portletName);

	public ResourceURL createResourceURL();

	public LiferayPortletURL createResourceURL(String portletName);

	public HttpServletResponse getHttpServletResponse();

	public Map<String, String[]> getProperties();

	public void setDateHeader(String name, long date);

	public void setHeader(String name, String value);

	public void setIntHeader(String name, int value);

	public void transferMarkupHeadElements();

}