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

package com.liferay.portlet;

import com.liferay.portal.kernel.portlet.LiferayPortletURL;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class ResourceResponseImpl
	extends MimeResponseImpl implements ResourceResponse {

	@Override
	public void addDateHeader(String name, long date) {
		_response.addDateHeader(name, date);
	}

	@Override
	public void addHeader(String name, String value) {
		_response.addHeader(name, value);
	}

	@Override
	public void addIntHeader(String name, int value) {
		_response.addIntHeader(name, value);
	}

	@Override
	public void addProperty(Cookie cookie) {
		_response.addCookie(cookie);
	}

	@Override
	public PortletURL createActionURL() {
		return super.createActionURL();
	}

	@Override
	public LiferayPortletURL createLiferayPortletURL(
		String portletName, String lifecycle) {

		ResourceRequest resourceRequest = (ResourceRequest)getPortletRequest();

		String cacheability = resourceRequest.getCacheability();

		if (cacheability.equals(ResourceURL.PAGE)) {
		}
		else if (lifecycle.equals(PortletRequest.ACTION_PHASE)) {
			throw new IllegalStateException(
				"Unable to create an action URL from a resource response " +
					"when the cacheability is not set to PAGE");
		}
		else if (lifecycle.equals(PortletRequest.RENDER_PHASE)) {
			throw new IllegalStateException(
				"Unable to create a render URL from a resource response when " +
					"the cacheability is not set to PAGE");
		}

		return super.createLiferayPortletURL(portletName, lifecycle);
	}

	@Override
	public PortletURL createRenderURL() {
		return super.createRenderURL();
	}

	@Override
	public ResourceURL createResourceURL() {
		return super.createResourceURL();
	}

	@Override
	public String getLifecycle() {
		return PortletRequest.RESOURCE_PHASE;
	}

	@Override
	public void setCharacterEncoding(String charset) {
		_response.setCharacterEncoding(charset);
	}

	@Override
	public void setContentLength(int length) {
		_response.setContentLength(length);
	}

	@Override
	public void setDateHeader(String name, long date) {
		_response.setDateHeader(name, date);
	}

	@Override
	public void setHeader(String name, String value) {
		_response.setHeader(name, value);

		if (name.equals(ResourceResponse.HTTP_STATUS_CODE)) {
			int status = GetterUtil.getInteger(
				value, HttpServletResponse.SC_OK);

			_response.setStatus(status);
		}
	}

	@Override
	public void setIntHeader(String name, int value) {
		_response.setIntHeader(name, value);
	}

	@Override
	public void setLocale(Locale locale) {
		_response.setLocale(locale);
	}

	@Override
	protected void init(
		PortletRequestImpl portletRequestImpl, HttpServletResponse response,
		String portletName, long companyId, long plid) {

		super.init(portletRequestImpl, response, portletName, companyId, plid);

		_response = response;
	}

	private HttpServletResponse _response;

}