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

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Ivica Cardic
 */
public class GhostscriptUtil {

	public static Future<?> execute(List<String> arguments) throws Exception {
		return getGhostscript().execute(arguments);
	}

	public static Ghostscript getGhostscript() {
		PortalRuntimePermission.checkGetBeanProperty(GhostscriptUtil.class);

		return _ghostscript;
	}

	public static boolean isEnabled() {
		return getGhostscript().isEnabled();
	}

	public static void reset() {
		getGhostscript().reset();
	}

	public void setGhostscript(Ghostscript ghostscript) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_ghostscript = ghostscript;
	}

	private static Ghostscript _ghostscript;

}