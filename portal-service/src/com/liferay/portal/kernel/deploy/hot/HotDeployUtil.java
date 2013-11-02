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

package com.liferay.portal.kernel.deploy.hot;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class HotDeployUtil {

	public static void fireDeployEvent(HotDeployEvent hotDeployEvent) {
		getHotDeploy().fireDeployEvent(hotDeployEvent);
	}

	public static void fireUndeployEvent(HotDeployEvent hotDeployEvent) {
		getHotDeploy().fireUndeployEvent(hotDeployEvent);
	}

	public static HotDeploy getHotDeploy() {
		PortalRuntimePermission.checkGetBeanProperty(HotDeployUtil.class);

		return _hotDeploy;
	}

	public static void registerListener(HotDeployListener hotDeployListener) {
		getHotDeploy().registerListener(hotDeployListener);
	}

	public static void reset() {
		getHotDeploy().reset();
	}

	public static void setCapturePrematureEvents(
		boolean capturePrematureEvents) {

		getHotDeploy().setCapturePrematureEvents(capturePrematureEvents);
	}

	public static void unregisterListener(HotDeployListener hotDeployListener) {
		getHotDeploy().unregisterListener(hotDeployListener);
	}

	public static void unregisterListeners() {
		getHotDeploy().unregisterListeners();
	}

	public void setHotDeploy(HotDeploy hotDeploy) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_hotDeploy = hotDeploy;
	}

	private static HotDeploy _hotDeploy;

}