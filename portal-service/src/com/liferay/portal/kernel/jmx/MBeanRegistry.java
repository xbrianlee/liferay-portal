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

package com.liferay.portal.kernel.jmx;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

/**
 * @author Michael C. Han
 */
public class MBeanRegistry {

	public void destroy() throws Exception {
		synchronized (_objectNameCache) {
			for (ObjectName objectName : _objectNameCache.values()) {
				try {
					_mBeanServer.unregisterMBean(objectName);
				}
				catch (Exception e) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to unregister MBean" +
								objectName.getCanonicalName(),
							e);
					}
				}
			}

			_objectNameCache.clear();
		}
	}

	public ObjectName getObjectName(String objectNameCacheKey) {
		return _objectNameCache.get(objectNameCacheKey);
	}

	public ObjectInstance register(
			String objectNameCacheKey, Object object, ObjectName objectName)
		throws InstanceAlreadyExistsException, MBeanRegistrationException,
			   NotCompliantMBeanException {

		ObjectInstance objectInstance = _mBeanServer.registerMBean(
			object, objectName);

		synchronized (_objectNameCache) {
			_objectNameCache.put(
				objectNameCacheKey, objectInstance.getObjectName());
		}

		return objectInstance;
	}

	public void replace(
			String objectCacheKey, Object object, ObjectName objectName)
		throws Exception {

		try {
			register(objectCacheKey, object, objectName);
		}
		catch (InstanceAlreadyExistsException iaee) {
			unregister(objectCacheKey, objectName);

			register(objectCacheKey, object, objectName);
		}
	}

	public void setMBeanServer(MBeanServer mBeanServer) {
		_mBeanServer = mBeanServer;
	}

	public void unregister(
			String objectNameCacheKey, ObjectName defaultObjectName)
		throws InstanceNotFoundException, MBeanRegistrationException {

		synchronized (_objectNameCache) {
			ObjectName objectName = _objectNameCache.get(objectNameCacheKey);

			if (objectName == null) {
				try {
					_mBeanServer.unregisterMBean(defaultObjectName);
				}
				catch (InstanceNotFoundException infe) {
					if (_log.isDebugEnabled()) {
						_log.debug(
							"Unable to unregister " + defaultObjectName, infe);
					}
				}
			}
			else {
				_objectNameCache.remove(objectNameCacheKey);

				_mBeanServer.unregisterMBean(objectName);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(MBeanRegistry.class);

	private MBeanServer _mBeanServer;
	private Map<String, ObjectName> _objectNameCache =
		new ConcurrentHashMap<String, ObjectName>();

}