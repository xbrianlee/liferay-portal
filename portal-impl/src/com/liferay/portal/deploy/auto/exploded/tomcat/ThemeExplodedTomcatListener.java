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

package com.liferay.portal.deploy.auto.exploded.tomcat;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.File;

/**
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class ThemeExplodedTomcatListener extends BaseExplodedTomcatListener {

	public ThemeExplodedTomcatListener() {
		_deployer = new ThemeExplodedTomcatDeployer();
	}

	@Override
	protected int deploy(File file) throws AutoDeployException {
		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + file.getPath());
		}

		File docBaseDir = getDocBaseDir(
			file, "WEB-INF/liferay-look-and-feel.xml");

		if (docBaseDir == null) {
			return AutoDeployer.CODE_NOT_APPLICABLE;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Modifying themes for " + file.getPath());
		}

		_deployer.explodedTomcatDeploy(file, docBaseDir, null);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Themes for " + file.getPath() + " modified successfully");
		}

		copyContextFile(file);

		return AutoDeployer.CODE_DEFAULT;
	}

	private static Log _log = LogFactoryUtil.getLog(
		ThemeExplodedTomcatListener.class);

	private ExplodedTomcatDeployer _deployer;

}