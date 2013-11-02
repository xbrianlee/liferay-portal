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

/**
 * @author Raymond Augé
 */
public interface HotDeploy {

	public void fireDeployEvent(HotDeployEvent hotDeployEvent);

	public void fireUndeployEvent(HotDeployEvent hotDeployEvent);

	public void registerListener(HotDeployListener hotDeployListener);

	public void reset();

	public void setCapturePrematureEvents(boolean capturePrematureEvents);

	public void unregisterListener(HotDeployListener hotDeployListener);

	public void unregisterListeners();

}