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

import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.comet.CometEvent;

/**
 * @author Edward Han
 */
public class CatalinaCometSessionUtil {

	public static String getSessionId(CometEvent cometEvent) {
		HttpServletRequest request = cometEvent.getHttpServletRequest();

		Object sessionId = request.getAttribute(_CATALINA_COMET_CONNECTION_ID);

		if ((sessionId == null) || !(sessionId instanceof String)) {
			sessionId = PortalUUIDUtil.generate();

			request.setAttribute(_CATALINA_COMET_CONNECTION_ID, sessionId);
		}

		return (String)sessionId;
	}

	private static final String _CATALINA_COMET_CONNECTION_ID =
		"CATALINA_COMET_CONNECTION_ID";

}