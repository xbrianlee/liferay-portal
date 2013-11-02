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

import com.liferay.portal.server.DeepNamedValueScanner;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Igor Spasic
 */
public class JettyServerCapabilities implements ServerCapabilities {

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

		DeepNamedValueScanner deepNamedValueScanner = new DeepNamedValueScanner(
			"_scanInterval");

		deepNamedValueScanner.setExcludedClassNames("WebAppProvider");
		deepNamedValueScanner.setIncludedClassNames("org.eclipse.jetty");
		deepNamedValueScanner.setVisitLists(true);

		deepNamedValueScanner.scan(servletContext);

		Integer scanInterval = (Integer)deepNamedValueScanner.getMatchedValue();

		if ((scanInterval != null) && (scanInterval.intValue() > 0)) {
			_supportsHotDeploy = true;
		}
		else {
			_supportsHotDeploy = false;
		}
	}

	private boolean _supportsHotDeploy;

}