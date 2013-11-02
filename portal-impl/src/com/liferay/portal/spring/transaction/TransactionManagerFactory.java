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

package com.liferay.portal.spring.transaction;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.SortedProperties;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.util.Enumeration;
import java.util.Properties;

import javax.sql.DataSource;

import jodd.bean.BeanUtil;

import org.hibernate.SessionFactory;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;

/**
 * @author Brian Wing Shun Chan
 */
public class TransactionManagerFactory {

	public static AbstractPlatformTransactionManager createTransactionManager(
			DataSource dataSource, SessionFactory sessionFactory)
		throws Exception {

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		Class<?> clazz = classLoader.loadClass(
			PropsValues.TRANSACTION_MANAGER_IMPL);

		AbstractPlatformTransactionManager abstractPlatformTransactionManager =
			(AbstractPlatformTransactionManager)clazz.newInstance();

		Properties properties = PropsUtil.getProperties(
			"transaction.manager.property.", true);

		Enumeration<String> enu =
			(Enumeration<String>)properties.propertyNames();

		while (enu.hasMoreElements()) {
			String key = enu.nextElement();

			String value = properties.getProperty(key);

			BeanUtil.setProperty(
				abstractPlatformTransactionManager, key, value);
		}

		if (abstractPlatformTransactionManager instanceof
				HibernateTransactionManager) {

			HibernateTransactionManager hibernateTransactionManager =
				(HibernateTransactionManager)abstractPlatformTransactionManager;

			hibernateTransactionManager.setDataSource(dataSource);
			hibernateTransactionManager.setSessionFactory(sessionFactory);
		}

		if (_log.isDebugEnabled()) {
			_log.debug(
				"Created transaction manager " +
					abstractPlatformTransactionManager.getClass().getName());

			SortedProperties sortedProperties = new SortedProperties(
				properties);

			sortedProperties.list(System.out);
		}

		return abstractPlatformTransactionManager;
	}

	private static Log _log = LogFactoryUtil.getLog(
		TransactionManagerFactory.class);

}