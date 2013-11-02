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

package com.liferay.portal.deploy.auto;

import com.liferay.portal.kernel.deploy.auto.AutoDeployException;
import com.liferay.portal.kernel.deploy.auto.AutoDeployer;
import com.liferay.portal.kernel.deploy.auto.BaseAutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.Portal;

import java.io.File;

/**
 * @author Ivica Cardic
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 * @author Miguel Pastor
 */
public class PortletAutoDeployListener extends BaseAutoDeployListener {

	public PortletAutoDeployListener() {
		_autoDeployer = new PortletAutoDeployer();
	}

	@Override
	public int deploy(AutoDeploymentContext autoDeploymentContext)
		throws AutoDeployException {

		File file = autoDeploymentContext.getFile();

		if (_log.isDebugEnabled()) {
			_log.debug("Invoking deploy for " + file.getPath());
		}

		AutoDeployer autoDeployer = null;

		if (isMatchingFile(
				file, "WEB-INF/" + Portal.PORTLET_XML_FILE_NAME_STANDARD)) {

			autoDeployer = _autoDeployer;
		}
		else if (isMatchingFile(file, "index_mvc.jsp")) {
			autoDeployer = getMvcDeployer();
		}
		else if (isMatchingFile(file, "index.php")) {
			autoDeployer = getPhpDeployer();
		}
		else if (!isExtPlugin(file) && !isHookPlugin(file) &&
				 !isMatchingFile(
					file, "WEB-INF/liferay-layout-templates.xml") &&
				 !isThemePlugin(file) && !isWebPlugin(file) &&
				 file.getName().endsWith(".war")) {

			if (_log.isInfoEnabled()) {
				_log.info("Deploying package as a web application");
			}

			autoDeployer = getWaiDeployer();
		}
		else {
			return AutoDeployer.CODE_NOT_APPLICABLE;
		}

		if (_log.isInfoEnabled()) {
			_log.info("Copying portlets for " + file.getPath());
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Using deployer " + autoDeployer.getClass().getName());
		}

		autoDeployer = new ThreadSafeAutoDeployer(autoDeployer);

		int code = autoDeployer.autoDeploy(autoDeploymentContext);

		if ((code == AutoDeployer.CODE_DEFAULT) && _log.isInfoEnabled()) {
			_log.info(
				"Portlets for " + file.getPath() + " copied successfully. " +
					"Deployment will start in a few seconds.");
		}

		return code;
	}

	protected AutoDeployer getMvcDeployer() {
		if (_mvcPortletAutoDeployer == null) {
			_mvcPortletAutoDeployer = new MVCPortletAutoDeployer();
		}

		return _mvcPortletAutoDeployer;
	}

	protected AutoDeployer getPhpDeployer() throws AutoDeployException {
		if (_phpPortletAutoDeployer == null) {
			_phpPortletAutoDeployer = new PHPPortletAutoDeployer();
		}

		return _phpPortletAutoDeployer;
	}

	protected AutoDeployer getWaiDeployer() throws AutoDeployException {
		if (_waiAutoDeployer == null) {
			_waiAutoDeployer = new WAIAutoDeployer();
		}

		return _waiAutoDeployer;
	}

	private static Log _log = LogFactoryUtil.getLog(
		PortletAutoDeployListener.class);

	private AutoDeployer _autoDeployer;
	private MVCPortletAutoDeployer _mvcPortletAutoDeployer;
	private PHPPortletAutoDeployer _phpPortletAutoDeployer;
	private WAIAutoDeployer _waiAutoDeployer;

}