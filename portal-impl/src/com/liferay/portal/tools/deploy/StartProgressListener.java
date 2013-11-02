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

package com.liferay.portal.tools.deploy;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.enterprise.deploy.spi.status.DeploymentStatus;
import javax.enterprise.deploy.spi.status.ProgressEvent;
import javax.enterprise.deploy.spi.status.ProgressListener;

/**
 * @author Sandeep Soni
 * @author Brian Wing Shun Chan
 * @author Deepak Gothe
 */
public class StartProgressListener implements ProgressListener {

	public StartProgressListener(DeploymentHandler deploymentHandler) {
		_deploymentHandler = deploymentHandler;
	}

	@Override
	public void handleProgressEvent(ProgressEvent progressEvent) {
		DeploymentStatus deploymentStatus = progressEvent.getDeploymentStatus();

		if (_log.isInfoEnabled()) {
			_log.info(deploymentStatus.getMessage());
		}

		if (deploymentStatus.isCompleted()) {
			_deploymentHandler.setError(false);
			_deploymentHandler.setStarted(true);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		StartProgressListener.class);

	private DeploymentHandler _deploymentHandler;

}