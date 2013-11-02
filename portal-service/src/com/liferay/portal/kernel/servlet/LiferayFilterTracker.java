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

package com.liferay.portal.kernel.servlet;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class LiferayFilterTracker {

	public static void addLiferayFilter(LiferayFilter liferayFilter) {
		Class<?> clazz = liferayFilter.getClass();

		Set<LiferayFilter> liferayFilters = _liferayFilters.get(
			clazz.getName());

		if (liferayFilters == null) {
			liferayFilters = new HashSet<LiferayFilter>();

			_liferayFilters.put(clazz.getName(), liferayFilters);
		}

		liferayFilters.add(liferayFilter);
	}

	public static Set<String> getClassNames() {
		return Collections.unmodifiableSet(_liferayFilters.keySet());
	}

	public static Set<LiferayFilter> getLiferayFilters(String className) {
		Set<LiferayFilter> liferayFilters = _liferayFilters.get(className);

		if (liferayFilters == null) {
			return Collections.emptySet();
		}

		return Collections.unmodifiableSet(liferayFilters);
	}

	public static void removeLiferayFilter(LiferayFilter liferayFilter) {
		Class<?> clazz = liferayFilter.getClass();

		Set<LiferayFilter> liferayFilters = _liferayFilters.get(
			clazz.getName());

		if (liferayFilters != null) {
			liferayFilters.remove(liferayFilter);
		}
	}

	private static Map<String, Set<LiferayFilter>> _liferayFilters =
		new HashMap<String, Set<LiferayFilter>>();

}