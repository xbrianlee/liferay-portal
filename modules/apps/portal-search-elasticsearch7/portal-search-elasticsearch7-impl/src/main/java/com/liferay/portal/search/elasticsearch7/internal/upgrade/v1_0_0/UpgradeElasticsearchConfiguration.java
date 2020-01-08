/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.elasticsearch7.internal.upgrade.v1_0_0;

import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration;
import com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration;

import java.util.Dictionary;

import org.osgi.framework.Constants;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

/**
 * @author Bryan Engler
 */
public class UpgradeElasticsearchConfiguration extends UpgradeProcess {

	public UpgradeElasticsearchConfiguration(
		ConfigurationAdmin configurationAdmin) {

		_configurationAdmin = configurationAdmin;
	}

	@Override
	protected void doUpgrade() throws Exception {
		upgradeElasticsearchConfiguration();
	}

	protected void upgradeElasticsearchConfiguration() throws Exception {
		Configuration elasticsearchConfiguration = _getConfiguration(
			ElasticsearchConfiguration.class.getName());

		if (elasticsearchConfiguration == null) {
			return;
		}

		Configuration elasticsearchConnectionConfiguration =
			_getDefaultConfiguration(
				ElasticsearchConnectionConfiguration.class.getName());

		if (elasticsearchConnectionConfiguration == null) {
			return;
		}

		Dictionary<String, Object> elasticsearchConfigurationProperties =
			elasticsearchConfiguration.getProperties();

		String operationMode = GetterUtil.getString(
			elasticsearchConfigurationProperties.get("operationMode"));
		String remoteClusterConnectionId = GetterUtil.getString(
			elasticsearchConfigurationProperties.get(
				"remoteClusterConnectionId"));

		Dictionary<String, Object>
			elasticsearchConnectionConfigurationProperties =
				elasticsearchConnectionConfiguration.getProperties();

		String connectionId = GetterUtil.getString(
			elasticsearchConnectionConfigurationProperties.get("connectionId"));

		if (operationMode.equals("REMOTE") &&
			(remoteClusterConnectionId.equals(StringPool.BLANK) ||
			 remoteClusterConnectionId.equals(connectionId))) {

			elasticsearchConnectionConfigurationProperties.put("active", true);
		}

		if (elasticsearchConfigurationProperties.get("authenticationEnabled") !=
				null) {

			elasticsearchConnectionConfigurationProperties.put(
				"authenticationEnabled",
				GetterUtil.getBoolean(
					elasticsearchConfigurationProperties.get(
						"authenticationEnabled")));
		}

		if (elasticsearchConfigurationProperties.get("httpSSLEnabled") !=
				null) {

			elasticsearchConnectionConfigurationProperties.put(
				"httpSSLEnabled",
				GetterUtil.getBoolean(
					elasticsearchConfigurationProperties.get(
						"httpSSLEnabled")));
		}

		String[] networkHostAddresses = GetterUtil.getStringValues(
			elasticsearchConfigurationProperties.get("networkHostAddresses"));

		if (ArrayUtil.isNotEmpty(networkHostAddresses)) {
			elasticsearchConnectionConfigurationProperties.put(
				"networkHostAddresses", networkHostAddresses);
		}

		String password = GetterUtil.getString(
			elasticsearchConfigurationProperties.get("password"));

		if (!Validator.isBlank(password)) {
			elasticsearchConnectionConfigurationProperties.put(
				"password", password);
		}

		String truststorePassword = GetterUtil.getString(
			elasticsearchConfigurationProperties.get("truststorePassword"));

		if (!Validator.isBlank(truststorePassword)) {
			elasticsearchConnectionConfigurationProperties.put(
				"truststorePassword", truststorePassword);
		}

		String truststorePath = GetterUtil.getString(
			elasticsearchConfigurationProperties.get("truststorePath"));

		if (!Validator.isBlank(truststorePath)) {
			elasticsearchConnectionConfigurationProperties.put(
				"truststorePath", truststorePath);
		}

		String truststoreType = GetterUtil.getString(
			elasticsearchConfigurationProperties.get("truststoreType"));

		if (!Validator.isBlank(truststoreType)) {
			elasticsearchConnectionConfigurationProperties.put(
				"truststoreType", truststoreType);
		}

		String username = GetterUtil.getString(
			elasticsearchConfigurationProperties.get("username"));

		if (!Validator.isBlank(username)) {
			elasticsearchConnectionConfigurationProperties.put(
				"username", username);
		}

		elasticsearchConnectionConfiguration.update(
			elasticsearchConnectionConfigurationProperties);
	}

	private Configuration _getConfiguration(String className) throws Exception {
		String filterString = StringBundler.concat(
			"(", Constants.SERVICE_PID, "=", className, ")");

		Configuration[] configurations = _configurationAdmin.listConfigurations(
			filterString);

		if (configurations != null) {
			return configurations[0];
		}

		return null;
	}

	private Configuration _getDefaultConfiguration(String className)
		throws Exception {

		String filterString = StringBundler.concat(
			"(", Constants.SERVICE_PID, "=", className, ".*)");

		Configuration[] configurations = _configurationAdmin.listConfigurations(
			filterString);

		for (Configuration configuration : configurations) {
			Dictionary<String, Object> properties =
				configuration.getProperties();

			String fileName = GetterUtil.getString(
				properties.get("felix.fileinstall.filename"));

			if (fileName.endsWith("-default.config")) {
				return configuration;
			}
		}

		return null;
	}

	private final ConfigurationAdmin _configurationAdmin;

}