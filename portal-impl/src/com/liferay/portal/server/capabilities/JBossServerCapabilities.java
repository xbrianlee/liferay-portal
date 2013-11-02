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

import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.server.DeepNamedValueScanner;

import javax.servlet.ServletContext;

/**
 * @author Igor Spasic
 */
public class JBossServerCapabilities implements ServerCapabilities {

	@Override
	public void determine(ServletContext servletContext) throws Exception {
		determineSupportsHotDeploy(servletContext);
	}

	@Override
	public boolean isSupportsHotDeploy() {
		return _supportsHotDeploy;
	}

	protected void determineSupportsHotDeploy(ServletContext servletContext)
		throws Exception {

		if (ServerDetector.isJBoss5()) {
			_supportsHotDeploy = true;
		}
		else {
			DeepNamedValueScanner deepNamedValueScanner =
				new DeepNamedValueScanner("scanEnabled", true);

			deepNamedValueScanner.setExcludedClassNames(
				"ChainedInterceptorFactory", "TagAttributeInfo", ".jandex.",
				".vfs.");
			deepNamedValueScanner.setExcludedNames("serialversion");
			deepNamedValueScanner.setIncludedClassNames(
				"org.apache.", "org.jboss.");
			deepNamedValueScanner.setVisitArrays(true);
			deepNamedValueScanner.setVisitMaps(true);

			deepNamedValueScanner.scan(servletContext);

			Boolean scanEnabledValue =
				(Boolean)deepNamedValueScanner.getMatchedValue();

			if (scanEnabledValue == null) {
				_supportsHotDeploy = false;
			}
			else {
				_supportsHotDeploy = scanEnabledValue.booleanValue();
			}
		}
	}

	private boolean _supportsHotDeploy;

}