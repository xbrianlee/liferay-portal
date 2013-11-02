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

package com.liferay.portal.kernel.poller.comet;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public interface CometRequest {

	public long getCompanyId();

	public String getParameter(String name);

	public Map<String, String[]> getParameterMap();

	public Enumeration<String> getParameterNames();

	public String getPathInfo();

	public HttpServletRequest getRequest();

	public long getTimestamp();

	public long getUserId();

	public void setCompanyId(long companyId);

	public void setPathInfo(String pathInfo);

	public void setTimestamp(long timestamp);

	public void setUserId(long userId);

}