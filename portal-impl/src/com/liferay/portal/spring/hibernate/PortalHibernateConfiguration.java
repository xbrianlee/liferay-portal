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

package com.liferay.portal.spring.hibernate;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.resiliency.spi.SPIUtil;
import com.liferay.portal.kernel.util.Converter;
import com.liferay.portal.kernel.util.PreloadClassLoader;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.io.InputStream;

import java.net.URL;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.WeakHashMap;

import javassist.util.proxy.ProxyFactory;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;

import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

/**
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Shuyang Zhou
 * @author Tomas Polesovsky
 */
public class PortalHibernateConfiguration extends LocalSessionFactoryBean {

	@Override
	public SessionFactory buildSessionFactory() throws Exception {
		setBeanClassLoader(getConfigurationClassLoader());

		return super.buildSessionFactory();
	}

	@Override
	public void destroy() throws HibernateException {
		setBeanClassLoader(null);

		super.destroy();
	}

	public void setHibernateConfigurationConverter(
		Converter<String> hibernateConfigurationConverter) {

		_hibernateConfigurationConverter = hibernateConfigurationConverter;
	}

	protected static Map<String, Class<?>> getPreloadClassLoaderClasses() {
		try {
			Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

			for (String className : _PRELOAD_CLASS_NAMES) {
				ClassLoader portalClassLoader =
					ClassLoaderUtil.getPortalClassLoader();

				Class<?> clazz = portalClassLoader.loadClass(className);

				classes.put(className, clazz);
			}

			return classes;
		}
		catch (ClassNotFoundException cnfe) {
			throw new RuntimeException(cnfe);
		}
	}

	protected Dialect determineDialect() {
		return DialectDetector.getDialect(getDataSource());
	}

	protected ClassLoader getConfigurationClassLoader() {
		Class<?> clazz = getClass();

		return clazz.getClassLoader();
	}

	protected String[] getConfigurationResources() {
		return PropsUtil.getArray(PropsKeys.HIBERNATE_CONFIGS);
	}

	@Override
	protected Configuration newConfiguration() {
		Configuration configuration = new Configuration();

		try {
			String[] resources = getConfigurationResources();

			for (String resource : resources) {
				try {
					readResource(configuration, resource);
				}
				catch (Exception e2) {
					if (_log.isWarnEnabled()) {
						_log.warn(e2, e2);
					}
				}
			}

			Properties properties = PropsUtil.getProperties();

			if (SPIUtil.isSPI()) {
				properties.put(
					"hibernate.cache.use_query_cache",
					Boolean.FALSE.toString());
				properties.put(
					"hibernate.cache.use_second_level_cache",
					Boolean.FALSE.toString());
			}

			configuration.setProperties(properties);

			if (Validator.isNull(PropsValues.HIBERNATE_DIALECT)) {
				Dialect dialect = determineDialect();

				setDB(dialect);

				Class<?> clazz = dialect.getClass();

				configuration.setProperty("hibernate.dialect", clazz.getName());
			}

			DB db = DBFactoryUtil.getDB();

			String dbType = db.getType();

			if (dbType.equals(DB.TYPE_HYPERSONIC)) {
				//configuration.setProperty("hibernate.jdbc.batch_size", "0");
			}
		}
		catch (Exception e1) {
			_log.error(e1, e1);
		}

		Properties hibernateProperties = getHibernateProperties();

		if (hibernateProperties != null) {
			for (Map.Entry<Object, Object> entry :
					hibernateProperties.entrySet()) {

				String key = (String)entry.getKey();
				String value = (String)entry.getValue();

				configuration.setProperty(key, value);
			}
		}

		return configuration;
	}

	@Override
	protected void postProcessConfiguration(Configuration configuration) {

		// Make sure that the Hibernate settings from PropsUtil are set. See the
		// buildSessionFactory implementation in the LocalSessionFactoryBean
		// class to understand how Spring automates a lot of configuration for
		// Hibernate.

		String connectionReleaseMode = PropsUtil.get(
			Environment.RELEASE_CONNECTIONS);

		if (Validator.isNotNull(connectionReleaseMode)) {
			configuration.setProperty(
				Environment.RELEASE_CONNECTIONS, connectionReleaseMode);
		}
	}

	protected void readResource(
			Configuration configuration, InputStream inputStream)
		throws Exception {

		if (inputStream == null) {
			return;
		}

		if (_hibernateConfigurationConverter != null) {
			String configurationString = StringUtil.read(inputStream);

			configurationString = _hibernateConfigurationConverter.convert(
				configurationString);

			inputStream = new UnsyncByteArrayInputStream(
				configurationString.getBytes());
		}

		configuration = configuration.addInputStream(inputStream);

		inputStream.close();
	}

	protected void readResource(Configuration configuration, String resource)
		throws Exception {

		ClassLoader classLoader = getConfigurationClassLoader();

		if (resource.startsWith("classpath*:")) {
			String name = resource.substring("classpath*:".length());

			Enumeration<URL> enu = classLoader.getResources(name);

			if (_log.isDebugEnabled() && !enu.hasMoreElements()) {
				_log.debug("No resources found for " + name);
			}

			while (enu.hasMoreElements()) {
				URL url = enu.nextElement();

				InputStream inputStream = url.openStream();

				readResource(configuration, inputStream);
			}
		}
		else {
			InputStream inputStream = classLoader.getResourceAsStream(resource);

			readResource(configuration, inputStream);
		}
	}

	protected void setDB(Dialect dialect) {
		DBFactoryUtil.setDB(dialect);
	}

	private static final String[] _PRELOAD_CLASS_NAMES =
		PropsValues.SPRING_HIBERNATE_CONFIGURATION_PROXY_FACTORY_PRELOAD_CLASSLOADER_CLASSES;

	private static Log _log = LogFactoryUtil.getLog(
		PortalHibernateConfiguration.class);

	private static Map<ProxyFactory, ClassLoader> _proxyFactoryClassLoaders =
		new WeakHashMap<ProxyFactory, ClassLoader>();

	static {
		ProxyFactory.classLoaderProvider =
			new ProxyFactory.ClassLoaderProvider() {

				@Override
				public ClassLoader get(ProxyFactory proxyFactory) {
					synchronized (_proxyFactoryClassLoaders) {
						ClassLoader classLoader = _proxyFactoryClassLoaders.get(
							proxyFactory);

						if (classLoader != null) {
							return classLoader;
						}

						classLoader = ClassLoaderUtil.getPortalClassLoader();

						ClassLoader contextClassLoader =
							ClassLoaderUtil.getContextClassLoader();

						if (classLoader != contextClassLoader) {
							classLoader = new PreloadClassLoader(
								contextClassLoader,
								getPreloadClassLoaderClasses());
						}

						_proxyFactoryClassLoaders.put(
							proxyFactory, classLoader);

						return classLoader;
					}
				}

			};
	}

	private Converter<String> _hibernateConfigurationConverter;

}