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

package com.liferay.portal.kernel.deploy;

import com.liferay.portal.kernel.deploy.auto.context.AutoDeploymentContext;
import com.liferay.portal.kernel.plugin.PluginPackage;

import java.util.List;
import java.util.Properties;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public interface DeployManager {

	public void deploy(AutoDeploymentContext autoDeploymentContext)
		throws Exception;

	public String getDeployDir() throws Exception;

	public String getInstalledDir() throws Exception;

	public PluginPackage getInstalledPluginPackage(String context);

	public List<PluginPackage> getInstalledPluginPackages();

	public List<String[]> getLevelsRequiredDeploymentContexts();

	public List<String[]> getLevelsRequiredDeploymentWARFileNames();

	public boolean isDeployed(String context);

	public boolean isRequiredDeploymentContext(String context);

	public PluginPackage readPluginPackageProperties(
		String displayName, Properties properties);

	public PluginPackage readPluginPackageXml(String xml) throws Exception;

	public void redeploy(String context) throws Exception;

	public void undeploy(String context) throws Exception;

}