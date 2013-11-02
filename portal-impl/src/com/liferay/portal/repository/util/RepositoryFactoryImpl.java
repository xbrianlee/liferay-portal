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

package com.liferay.portal.repository.util;

import com.liferay.portal.kernel.repository.BaseRepository;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.ProxyFactory;
import com.liferay.portal.repository.proxy.BaseRepositoryProxyBean;

/**
 * @author Mika Koivisto
 */
public class RepositoryFactoryImpl implements RepositoryFactory {

	public RepositoryFactoryImpl(String className) {
		_className = className;
	}

	public RepositoryFactoryImpl(String className, ClassLoader classLoader) {
		_classLoader = classLoader;
		_className = className;
	}

	@Override
	public BaseRepository getInstance() throws Exception {
		if (_classLoader == null) {
			return (BaseRepository)InstanceFactory.newInstance(_className);
		}

		BaseRepository baseRepository =
			(BaseRepository)ProxyFactory.newInstance(
				_classLoader, BaseRepository.class, _className);

		return new BaseRepositoryProxyBean(baseRepository, _classLoader);
	}

	private ClassLoader _classLoader;
	private String _className;

}