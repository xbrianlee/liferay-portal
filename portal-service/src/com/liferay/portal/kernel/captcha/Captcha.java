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

package com.liferay.portal.kernel.captcha;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public interface Captcha {

	public void check(HttpServletRequest request) throws CaptchaException;

	public void check(PortletRequest portletRequest) throws CaptchaException;

	public String getTaglibPath();

	public boolean isEnabled(HttpServletRequest request)
		throws CaptchaException;

	public boolean isEnabled(PortletRequest portletRequest)
		throws CaptchaException;

	public void serveImage(
			HttpServletRequest request, HttpServletResponse response)
		throws IOException;

	public void serveImage(
			ResourceRequest resourceRequest, ResourceResponse resourceResponse)
		throws IOException;

}