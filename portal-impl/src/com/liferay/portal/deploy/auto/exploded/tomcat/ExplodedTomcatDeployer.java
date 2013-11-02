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
import com.liferay.portal.kernel.plugin.PluginPackage;

import java.io.File;

/**
 * @author Olaf Fricke
 * @author Brian Wing Shun Chan
 */
public interface ExplodedTomcatDeployer {

	public void explodedTomcatDeploy(
			File contextFile, File webAppDir, PluginPackage pluginPackage)
		throws AutoDeployException;

}