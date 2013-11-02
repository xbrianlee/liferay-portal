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

package com.liferay.counter.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class CounterFinderUtil {
	public static void afterPropertiesSet() {
		getFinder().afterPropertiesSet();
	}

	public static java.util.List<java.lang.String> getNames()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().getNames();
	}

	public static java.lang.String getRegistryName() {
		return getFinder().getRegistryName();
	}

	public static long increment()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().increment();
	}

	public static long increment(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().increment(name);
	}

	public static long increment(java.lang.String name, int size)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getFinder().increment(name, size);
	}

	public static void invalidate() {
		getFinder().invalidate();
	}

	public static void rename(java.lang.String oldName, java.lang.String newName)
		throws com.liferay.portal.kernel.exception.SystemException {
		getFinder().rename(oldName, newName);
	}

	public static void reset(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getFinder().reset(name);
	}

	public static void reset(java.lang.String name, long size)
		throws com.liferay.portal.kernel.exception.SystemException {
		getFinder().reset(name, size);
	}

	public static CounterFinder getFinder() {
		if (_finder == null) {
			_finder = (CounterFinder)PortalBeanLocatorUtil.locate(CounterFinder.class.getName());

			ReferenceRegistry.registerReference(CounterFinderUtil.class,
				"_finder");
		}

		return _finder;
	}

	public void setFinder(CounterFinder finder) {
		_finder = finder;

		ReferenceRegistry.registerReference(CounterFinderUtil.class, "_finder");
	}

	private static CounterFinder _finder;
}