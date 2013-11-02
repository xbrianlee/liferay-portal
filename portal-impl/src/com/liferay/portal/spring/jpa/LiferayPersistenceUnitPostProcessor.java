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

package com.liferay.portal.spring.jpa;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropertiesUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Properties;

import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitPostProcessor;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class LiferayPersistenceUnitPostProcessor
	implements PersistenceUnitPostProcessor {

	@Override
	public void postProcessPersistenceUnitInfo(
		MutablePersistenceUnitInfo mutablePersistenceUnitInfo) {

		for (String mappingFileName : PropsValues.JPA_CONFIGS) {
			mutablePersistenceUnitInfo.addMappingFileName(mappingFileName);
		}

		Properties properties = PropsUtil.getProperties(
			PropsKeys.JPA_PROVIDER_PROPERTY_PREFIX, true);

		if (_log.isInfoEnabled()) {
			_log.info(PropertiesUtil.list(properties));
		}

		mutablePersistenceUnitInfo.setProperties(properties);
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayPersistenceUnitPostProcessor.class);

}