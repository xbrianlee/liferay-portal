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

package com.liferay.portal.deploy.sandbox;

import com.liferay.portal.kernel.deploy.sandbox.SandboxDeployException;
import com.liferay.portal.kernel.deploy.sandbox.SandboxDeployListener;
import com.liferay.portal.kernel.plugin.PluginPackage;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.tools.deploy.ThemeDeployer;
import com.liferay.portal.util.PortalUtil;

import java.io.File;

import java.util.ArrayList;

/**
 * @author Igor Spasic
 * @author Brian Wing Shun Chan
 */
public class ThemeSandboxDeployListener
	extends ThemeDeployer implements SandboxDeployListener {

	public ThemeSandboxDeployListener() {
		_sandboxHandler = new ThemeSandboxHandler(this);

		appServerType = ServerDetector.getServerId();

		String portalWebDir = PortalUtil.getPortalWebDir();

		themeTaglibDTD = portalWebDir + "/WEB-INF/tld/liferay-theme.tld";
		utilTaglibDTD = portalWebDir + "/WEB-INF/tld/liferay-util.tld";

		jars = new ArrayList<String>();

		String portalLibDir = PortalUtil.getPortalLibDir();

		jars.add(portalLibDir + "/commons-logging.jar");
		jars.add(portalLibDir + "/log4j.jar");
		jars.add(portalLibDir + "/util-java.jar");
		jars.add(portalLibDir + "/util-taglib.jar");
	}

	@Override
	public void copyXmls(
			File srcFile, String displayName, PluginPackage pluginPackage)
		throws Exception {

		super.copyXmls(srcFile, displayName, pluginPackage);

		copyTomcatContextXml(srcFile);
	}

	@Override
	public void deploy(File dir) throws SandboxDeployException {
		_sandboxHandler.deploy(dir);
	}

	@Override
	public String getDisplayName(File srcFile) {
		String displayName = super.getDisplayName(srcFile);

		return _sandboxHandler.getDisplayName(displayName);
	}

	@Override
	public void undeploy(File dir) throws SandboxDeployException {
		_sandboxHandler.undeploy(dir);
	}

	private SandboxHandler _sandboxHandler;

}