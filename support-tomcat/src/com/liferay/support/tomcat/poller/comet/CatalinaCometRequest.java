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

package com.liferay.support.tomcat.poller.comet;

import com.liferay.portal.kernel.poller.comet.BaseCometRequest;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public class CatalinaCometRequest extends BaseCometRequest {

	public CatalinaCometRequest(CometEvent cometEvent) {
		_request = cometEvent.getHttpServletRequest();

		setRequest(_request);
	}

	@Override
	public String getParameter(String name) {
		return _request.getParameter(name);
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		return _request.getParameterMap();
	}

	@Override
	public Enumeration<String> getParameterNames() {
		return _request.getParameterNames();
	}

	private HttpServletRequest _request;

}