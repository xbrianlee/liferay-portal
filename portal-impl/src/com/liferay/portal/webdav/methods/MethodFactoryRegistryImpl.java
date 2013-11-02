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

package com.liferay.portal.webdav.methods;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.webdav.methods.MethodFactory;
import com.liferay.portal.kernel.webdav.methods.MethodFactoryRegistry;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class MethodFactoryRegistryImpl implements MethodFactoryRegistry {

	@Override
	public MethodFactory getDefaultMethodFactory() {
		return _defaultMethodFactory;
	}

	@Override
	public List<MethodFactory> getMethodFactories() {
		return ListUtil.fromMapValues(_methodFactories);
	}

	@Override
	public MethodFactory getMethodFactory(String className) {
		return _methodFactories.get(className);
	}

	@Override
	public void registerMethodFactory(MethodFactory methodFactory) {
		Class<?> clazz = methodFactory.getClass();

		MethodFactory previousMethodFactory = _methodFactories.put(
			clazz.getName(), methodFactory);

		if (previousMethodFactory == _defaultMethodFactory) {
			_defaultMethodFactory = methodFactory;
		}

		if (_log.isWarnEnabled() && (previousMethodFactory != null)) {
			_log.warn(
				"Replacing " + previousMethodFactory + " for class name " +
					clazz.getName() + " with " + methodFactory);
		}
	}

	public void setDefaultMethodFactory(MethodFactory defaultMethodFactory) {
		_defaultMethodFactory = defaultMethodFactory;
	}

	@Override
	public void unregisterMethodFactory(MethodFactory methodFactory) {
		Class<?> clazz = methodFactory.getClass();

		_methodFactories.remove(clazz.getName());
	}

	private static Log _log = LogFactoryUtil.getLog(
		MethodFactoryRegistryImpl.class);

	private MethodFactory _defaultMethodFactory;
	private Map<String, MethodFactory> _methodFactories =
		new ConcurrentHashMap<String, MethodFactory>();

}