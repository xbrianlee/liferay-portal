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

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 * @author Igor Spasic
 */
public class DefaultServerCapabilities implements ServerCapabilities {

	@Override
	public void determine(ServletContext servletContext) {
	}

	@Override
	public boolean isSupportsHotDeploy() {
		return _SUPPORTS_HOT_DEPLOY;
	}

	private static final boolean _SUPPORTS_HOT_DEPLOY = false;

}