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

package com.liferay.portal.deploy.messaging;

import com.liferay.portal.kernel.deploy.DeployManagerUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployDir;
import com.liferay.portal.kernel.deploy.auto.AutoDeployUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.StreamUtil;

import java.io.FileOutputStream;
import java.io.InputStream;

import java.util.List;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-39277.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class RequiredPluginsMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		if (_firstMessage) {

			// Ignore the first message to give the portal time to fully load

			_firstMessage = false;

			return;
		}

		List<String[]> levelsRequiredDeploymentContexts =
			DeployManagerUtil.getLevelsRequiredDeploymentContexts();
		List<String[]> levelsRequiredDeploymentWARFileNames =
			DeployManagerUtil.getLevelsRequiredDeploymentWARFileNames();

		for (int i = 0; i < levelsRequiredDeploymentContexts.size(); i++) {
			String[] levelRequiredDeploymentContexts =
				levelsRequiredDeploymentContexts.get(i);
			String[] levelRequiredDeploymentWARFileNames =
				levelsRequiredDeploymentWARFileNames.get(i);

			for (int j = 0; j < levelRequiredDeploymentContexts.length; j++) {
				String levelRequiredDeploymentContext =
					levelRequiredDeploymentContexts[j];

				if (DeployManagerUtil.isDeployed(
						levelRequiredDeploymentContext)) {

					continue;
				}

				String levelRequiredDeploymentWARFileName =
					levelRequiredDeploymentWARFileNames[j];

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Automatically deploying the required plugin " +
							levelRequiredDeploymentWARFileName);
				}

				Class<?> clazz = getClass();

				ClassLoader classLoader = clazz.getClassLoader();

				InputStream inputStream = classLoader.getResourceAsStream(
					"com/liferay/portal/deploy/dependencies/plugins" +
						(i + 1) + "/" + levelRequiredDeploymentWARFileNames[j]);

				AutoDeployDir autoDeployDir = AutoDeployUtil.getDir(
					AutoDeployDir.DEFAULT_NAME);

				if (autoDeployDir == null) {

					// This should never happen except during shutdown

					if (_log.isDebugEnabled()) {
						_log.debug(
							"The autodeploy directory " +
								AutoDeployDir.DEFAULT_NAME + " is null");
					}

					continue;
				}

				StreamUtil.transfer(
					inputStream,
					new FileOutputStream(
						autoDeployDir.getDeployDir() + "/" +
							levelRequiredDeploymentWARFileNames[j]));
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		RequiredPluginsMessageListener.class);

	private boolean _firstMessage = true;

}