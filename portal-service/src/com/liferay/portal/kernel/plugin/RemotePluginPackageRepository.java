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

import com.liferay.portal.kernel.util.StringPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Jorge Ferrer
 */
public class RemotePluginPackageRepository {

	public static final String LOCAL_URL = "LOCAL_URL";

	public static final String SETTING_USE_DOWNLOAD_URL = "use-download-url";

	public RemotePluginPackageRepository(String repositoryURL) {
		_repositoryURL = repositoryURL;
	}

	public void addPluginPackage(PluginPackage pluginPackage) {

		// Avoid duplicates

		PluginPackage existingPackage = _moduleIdIndex.get(
			pluginPackage.getModuleId());

		if (existingPackage != null) {
			return;
		}

		_artifactURLIndex.put(pluginPackage.getArtifactURL(), pluginPackage);
		_moduleIdIndex.put(pluginPackage.getModuleId(), pluginPackage);
		_addToGroupAndArtifactIndex(
			pluginPackage.getGroupId(), pluginPackage.getArtifactId(),
			pluginPackage);
		_pluginPackages.add(pluginPackage);
		_tags.addAll(pluginPackage.getTags());
	}

	public PluginPackage findPluginByArtifactURL(String artifactURL) {
		return _artifactURLIndex.get(artifactURL);
	}

	public PluginPackage findPluginPackageByModuleId(String moduleId) {
		return _moduleIdIndex.get(moduleId);
	}

	public List<PluginPackage> findPluginsByGroupIdAndArtifactId(
		String groupId, String artifactId) {

		return _groupAndArtifactIndex.get(
			groupId + StringPool.SLASH + artifactId);
	}

	public List<PluginPackage> getPluginPackages() {
		return _pluginPackages;
	}

	public String getRepositoryURL() {
		return _repositoryURL;
	}

	public Properties getSettings() {
		return _settings;
	}

	public Set<String> getTags() {
		return _tags;
	}

	public void removePlugin(PluginPackage pluginPackage) {
		_artifactURLIndex.remove(pluginPackage.getArtifactURL());
		_moduleIdIndex.remove(pluginPackage.getModuleId());
		_removeFromGroupAndArtifactIndex(
			pluginPackage.getGroupId(), pluginPackage.getArtifactId(),
			pluginPackage.getModuleId());
		_pluginPackages.remove(pluginPackage);
	}

	public void setSettings(Properties settings) {
		_settings = settings;
	}

	private void _addToGroupAndArtifactIndex(
		String groupId, String artifactId, PluginPackage pluginPackage) {

		List<PluginPackage> pluginPackages = findPluginsByGroupIdAndArtifactId(
			groupId, artifactId);

		if (pluginPackages == null) {
			pluginPackages = new ArrayList<PluginPackage>();

			_groupAndArtifactIndex.put(
				groupId+ StringPool.SLASH + artifactId, pluginPackages);
		}

		pluginPackages.add(pluginPackage);
	}

	private void _removeFromGroupAndArtifactIndex(
		String groupId, String artifactId, String moduleId) {

		List<PluginPackage> pluginPackages = findPluginsByGroupIdAndArtifactId(
			groupId, artifactId);

		if (pluginPackages != null) {
			Iterator<PluginPackage> itr = pluginPackages.iterator();

			while (itr.hasNext()) {
				PluginPackage pluginPackage = itr.next();

				if (pluginPackage.getModuleId().equals(moduleId)) {
					itr.remove();

					break;
				}
			}
		}
	}

	private Map<String, PluginPackage> _artifactURLIndex =
		new HashMap<String, PluginPackage>();
	private Map<String, List<PluginPackage>> _groupAndArtifactIndex =
		new HashMap<String, List<PluginPackage>>();
	private Map<String, PluginPackage> _moduleIdIndex =
		new HashMap<String, PluginPackage>();
	private List<PluginPackage> _pluginPackages =
		new ArrayList<PluginPackage>();
	private String _repositoryURL;
	private Properties _settings = null;
	private Set<String> _tags = new TreeSet<String>();

}