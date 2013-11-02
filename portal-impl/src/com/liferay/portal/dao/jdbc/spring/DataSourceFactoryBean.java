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

package com.liferay.portal.dao.jdbc.spring;

import com.liferay.portal.kernel.dao.jdbc.DataSourceFactoryUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.util.PropsUtil;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author Brian Wing Shun Chan
 */
public class DataSourceFactoryBean extends AbstractFactoryBean<DataSource> {

	@Override
	public DataSource createInstance() throws Exception {
		Properties properties = _properties;

		if (properties == null) {
			properties = PropsUtil.getProperties(_propertyPrefix, true);
		}
		else {
			properties = PropertiesUtil.getProperties(
				properties, _propertyPrefix, true);
		}

		return DataSourceFactoryUtil.initDataSource(properties);
	}

	@Override
	public void destroyInstance(DataSource dataSource) throws Exception {
		DataSourceFactoryUtil.destroyDataSource(dataSource);
	}

	@Override
	public Class<DataSource> getObjectType() {
		return DataSource.class;
	}

	public void setProperties(Properties properties) {
		_properties = properties;
	}

	public void setPropertyPrefix(String propertyPrefix) {
		_propertyPrefix = propertyPrefix;
	}

	public void setPropertyPrefixLookup(String propertyPrefixLookup) {
		_propertyPrefix = PropsUtil.get(propertyPrefixLookup);
	}

	private Properties _properties;
	private String _propertyPrefix;

}