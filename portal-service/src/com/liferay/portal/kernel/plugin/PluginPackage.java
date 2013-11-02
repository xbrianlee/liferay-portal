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

package com.liferay.portal.kernel.plugin;

import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author Jorge Ferrer
 * @author Brian Wing Shun Chan
 */
public interface PluginPackage {

	public static final String REPOSITORY_XML_FILENAME_EXTENSION = "xml";

	public static final String REPOSITORY_XML_FILENAME_PREFIX =
		"liferay-plugin-repository";

	public String getArtifactId();

	public String getArtifactURL();

	public String getAuthor();

	public String getChangeLog();

	public String getContext();

	public Properties getDeploymentSettings();

	public String getDownloadURL();

	public String getGroupId();

	public List<License> getLicenses();

	public List<String> getLiferayVersions();

	public String getLongDescription();

	public Date getModifiedDate();

	public String getModuleId();

	public String getName();

	public String getPackageId();

	public String getPageURL();

	public String getRecommendedDeploymentContext();

	public RemotePluginPackageRepository getRepository();

	public String getRepositoryURL();

	List<String> getRequiredDeploymentContexts();

	public List<Screenshot> getScreenshots();

	public String getShortDescription();

	public List<String> getTags();

	public List<String> getTypes();

	public String getVersion();

	public boolean isLaterVersionThan(PluginPackage pluginPackage);

	public boolean isPreviousVersionThan(PluginPackage pluginPackage);

	public boolean isSameVersionAs(PluginPackage pluginPackage);

	public void setAuthor(String author);

	public void setChangeLog(String changeLog);

	public void setContext(String context);

	public void setDeploymentSettings(Properties properties);

	public void setDownloadURL(String downloadURL);

	public void setLicenses(List<License> licenses);

	public void setLiferayVersions(List<String> liferayVersions);

	public void setLongDescription(String longDescription);

	public void setModifiedDate(Date modifiedDate);

	public void setName(String name);

	public void setPageURL(String pageURL);

	public void setRecommendedDeploymentContext(String deploymentContext);

	public void setRepository(RemotePluginPackageRepository repository);

	public void setRequiredDeploymentContexts(
		List<String> requiredDeploymentContexts);

	public void setScreenshots(List<Screenshot> screenshots);

	public void setShortDescription(String shortDescription);

	public void setTags(List<String> tags);

	public void setTypes(List<String> types);

}