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
import com.liferay.portal.kernel.util.ArrayUtil;

import javax.enterprise.deploy.shared.ModuleType;
import javax.enterprise.deploy.spi.DeploymentManager;
import javax.enterprise.deploy.spi.TargetModuleID;
import javax.enterprise.deploy.spi.status.DeploymentStatus;
import javax.enterprise.deploy.spi.status.ProgressEvent;
import javax.enterprise.deploy.spi.status.ProgressListener;
import javax.enterprise.deploy.spi.status.ProgressObject;

/**
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 */
public class DeploymentProgressListener implements ProgressListener {

	public DeploymentProgressListener(
		DeploymentHandler deploymentHandler, String warContext) {

		_deploymentHandler = deploymentHandler;
		_warContext = warContext;
		_deploymentManager = _deploymentHandler.getDeploymentManager();
	}

	@Override
	public void handleProgressEvent(ProgressEvent progressEvent) {
		DeploymentStatus deploymentStatus = progressEvent.getDeploymentStatus();

		if (_log.isInfoEnabled()) {
			_log.info(deploymentStatus.getMessage());
		}

		if (deploymentStatus.isCompleted()) {
			try {
				TargetModuleID[] targetModuleIDs =
					_deploymentManager.getNonRunningModules(
						ModuleType.WAR, _deploymentManager.getTargets());

				if (ArrayUtil.isNotEmpty(targetModuleIDs)) {
					for (TargetModuleID targetModuleID : targetModuleIDs) {
						if (!_warContext.equals(targetModuleID.getModuleID())) {
							continue;
						}

						ProgressObject startProgress = _deploymentManager.start(
							new TargetModuleID[] {targetModuleID});

						startProgress.addProgressListener(
							new StartProgressListener(_deploymentHandler));

						_deploymentHandler.setError(false);
						_deploymentHandler.setStarted(true);

						break;
					}
				}
				else {
					targetModuleIDs = _deploymentManager.getAvailableModules(
						ModuleType.WAR, _deploymentManager.getTargets());

					for (TargetModuleID targetModuleID : targetModuleIDs) {
						if (!_warContext.equals(targetModuleID.getModuleID())) {
							continue;
						}

						ProgressObject startProgress = _deploymentManager.start(
							new TargetModuleID[] {targetModuleID});

						startProgress.addProgressListener(
							new StartProgressListener(_deploymentHandler));

						_deploymentHandler.setError(false);
						_deploymentHandler.setStarted(true);

						break;
					}
				}
			}
			catch (Exception e) {
				_log.error(e, e);

				_deploymentHandler.setError(true);
				_deploymentHandler.setStarted(false);
			}
		}
		else if (deploymentStatus.isFailed()) {
			_deploymentHandler.setError(true);
			_deploymentHandler.setStarted(false);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DeploymentProgressListener.class);

	private DeploymentHandler _deploymentHandler;
	private DeploymentManager _deploymentManager;
	private String _warContext;

}