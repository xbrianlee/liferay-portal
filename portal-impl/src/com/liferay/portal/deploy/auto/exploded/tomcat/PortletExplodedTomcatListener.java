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
import com.liferay.portal.util.Portal;

import java.io.File;

/**
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public class PortletExplodedTomcatListener extends BaseExplodedTomcatListener {

	public PortletExplodedTomcatListener() {
		_deployer = new PortletExplodedTomcatDeployer();
	}

	@Override
	protected int deploy(File file) throws AutoDeployException {
		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + file.getPath());
		}

		ExplodedTomcatDeployer deployer = null;

		File docBaseDir = getDocBaseDir(file, "index.php");

		if (docBaseDir != null) {
			deployer = getPhpDeployer();
		}
		else {
			docBaseDir = getDocBaseDir(
				file, "WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_STANDARD);

			if (docBaseDir != null) {
				deployer = _deployer;
			}
			else {
				return AutoDeployer.CODE_NOT_APPLICABLE;
			}
		}

		if (_log.isInfoEnabled()) {
			_log.info("Modifying portlets for " + file.getPath());
		}

		deployer.explodedTomcatDeploy(file, docBaseDir, null);

		if (_log.isInfoEnabled()) {
			_log.info(
				"Portlets for " + file.getPath() + " modified successfully");
		}

		copyContextFile(file);

		return AutoDeployer.CODE_DEFAULT;
	}

	protected ExplodedTomcatDeployer getPhpDeployer()
		throws AutoDeployException {

		if (_phpDeployer == null) {
			_phpDeployer = new PHPPortletExplodedTomcatDeployer();
		}

		return _phpDeployer;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletExplodedTomcatListener.class);

	private ExplodedTomcatDeployer _deployer;
	private PHPPortletExplodedTomcatDeployer _phpDeployer;

}