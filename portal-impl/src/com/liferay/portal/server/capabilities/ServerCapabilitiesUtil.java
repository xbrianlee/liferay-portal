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

package com.liferay.portal.server.capabilities;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServerDetector;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Igor Spasic
 */
public class ServerCapabilitiesUtil {

	public static void determineServerCapabilities(
		ServletContext servletContext) {

		ServerCapabilities serverCapabilities = null;

		if (ServerDetector.isGlassfish()) {
			serverCapabilities = new GlassfishServerCapabilities();
		}
		else if (ServerDetector.isJBoss()) {
			serverCapabilities = new JBossServerCapabilities();
		}
		else if (ServerDetector.isJetty()) {
			serverCapabilities = new JettyServerCapabilities();
		}
		else if (ServerDetector.isTomcat()) {
			serverCapabilities = new TomcatServerCapabilities();
		}

		if (serverCapabilities == null) {
			serverCapabilities = new DefaultServerCapabilities();
		}

		if (_log.isInfoEnabled()) {
			Class<?> clazz = serverCapabilities.getClass();

			_log.info("Using " + clazz.getName());
		}

		try {
			serverCapabilities.determine(servletContext);
		}
		catch (Exception e) {
			_log.error("Unable to determine server capabilities", e);
		}

		ServerDetector.setSupportsHotDeploy(
			serverCapabilities.isSupportsHotDeploy());
	}

	private static Log _log = LogFactoryUtil.getLog(
		ServerCapabilitiesUtil.class);

}