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
import com.liferay.portal.kernel.repository.RepositoryException;
import com.liferay.portal.util.PropsValues;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mika Koivisto
 */
public class RepositoryFactoryUtil {

	public static BaseRepository getInstance(String className)
		throws Exception {

		RepositoryFactory repositoryFactory = _repositoryFactories.get(
			className);

		BaseRepository baseRepository = null;

		if (repositoryFactory != null) {
			baseRepository = repositoryFactory.getInstance();
		}

		if (baseRepository != null) {
			return baseRepository;
		}

		throw new RepositoryException(
			"Repository with class name " + className + " is unavailable");
	}

	public static String[] getRepositoryClassNames() {
		Set<String> classNames = _repositoryFactories.keySet();

		return classNames.toArray(new String[classNames.size()]);
	}

	public static void registerRepositoryFactory(
		String className, RepositoryFactory repositoryFactory) {

		_repositoryFactories.put(className, repositoryFactory);
	}

	public static void unregisterRepositoryFactory(String className) {
		_repositoryFactories.remove(className);
	}

	private static ConcurrentHashMap<String, RepositoryFactory>
		_repositoryFactories =
			new ConcurrentHashMap<String, RepositoryFactory>();

	static {
		for (String className : PropsValues.DL_REPOSITORY_IMPL) {
			RepositoryFactory repositoryFactory = new RepositoryFactoryImpl(
				className);

			_repositoryFactories.put(className, repositoryFactory);
		}
	}

}