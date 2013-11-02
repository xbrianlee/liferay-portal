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
import com.liferay.portal.util.ClassLoaderUtil;

import java.io.File;

import javax.enterprise.deploy.shared.ModuleType;
import javax.enterprise.deploy.shared.factories.DeploymentFactoryManager;
import javax.enterprise.deploy.spi.DeploymentManager;
import javax.enterprise.deploy.spi.TargetModuleID;
import javax.enterprise.deploy.spi.factories.DeploymentFactory;
import javax.enterprise.deploy.spi.status.ProgressObject;

/**
 * @author Sandeep Soni
 * @author Brian Wing Shun Chan
 */
public class DeploymentHandler {

	public DeploymentHandler(
		String dmId, String dmUser, String dmPassword, String dfClassName) {

		try {
			ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

			DeploymentFactoryManager deploymentFactoryManager =
				DeploymentFactoryManager.getInstance();

			Class<?> clazz = classLoader.loadClass(dfClassName);

			DeploymentFactory deploymentFactory =
				(DeploymentFactory)clazz.newInstance();

			deploymentFactoryManager.registerDeploymentFactory(
				deploymentFactory);

			_deploymentManager = deploymentFactoryManager.getDeploymentManager(
				dmId, dmUser, dmPassword);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	public void deploy(File warDir, String warContext) throws Exception {
		setStarted(false);

		ProgressObject deployProgress = null;

		TargetModuleID[] targetModuleIDs =
			_deploymentManager.getAvailableModules(
				ModuleType.WAR, _deploymentManager.getTargets());

		for (TargetModuleID targetModuleID : targetModuleIDs) {
			if (!targetModuleID.getModuleID().equals(warContext)) {
				continue;
			}

			deployProgress = _deploymentManager.redeploy(
				new TargetModuleID[] {targetModuleID}, warDir, null);

			break;
		}

		if (deployProgress == null) {
			deployProgress = _deploymentManager.distribute(
				_deploymentManager.getTargets(), warDir, null);
		}

		deployProgress.addProgressListener(
			new DeploymentProgressListener(this, warContext));

		waitForStart(warContext);

		if (_error) {
			throw new Exception("Failed to deploy " + warDir);
		}
	}

	public DeploymentManager getDeploymentManager() {
		return _deploymentManager;
	}

	public void releaseDeploymentManager() {
		_deploymentManager.release();
	}

	public synchronized void setError(boolean error) {
		_error = error;
	}

	public synchronized void setStarted(boolean started) {
		_started = started;

		notifyAll();
	}

	protected synchronized void waitForStart(String warContext)
		throws Exception {

		while (!_error && !_started) {
			wait();
		}

		if (_error) {
			return;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(DeploymentHandler.class);

	private DeploymentManager _deploymentManager;
	private boolean _error;
	private boolean _started;

}