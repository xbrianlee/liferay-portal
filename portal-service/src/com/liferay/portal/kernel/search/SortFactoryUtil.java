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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class SortFactoryUtil {

	public static Sort create(String fieldName, boolean reverse) {
		return getSortFactory().create(fieldName, reverse);
	}

	public static Sort create(String fieldName, int type, boolean reverse) {
		return getSortFactory().create(fieldName, type, reverse);
	}

	public static Sort[] getDefaultSorts() {
		return getSortFactory().getDefaultSorts();
	}

	public static Sort getSort(
		Class<?> clazz, int type, String orderByCol, String orderByType) {

		return getSortFactory().getSort(clazz, type, orderByCol, orderByType);
	}

	public static Sort getSort(
		Class<?> clazz, String orderByCol, String orderByType) {

		return getSortFactory().getSort(clazz, orderByCol, orderByType);
	}

	public static SortFactory getSortFactory() {
		PortalRuntimePermission.checkGetBeanProperty(SortFactoryUtil.class);

		return _sortFactory;
	}

	public static Sort[] toArray(List<Sort> sorts) {
		return getSortFactory().toArray(sorts);
	}

	public void setSortFactory(SortFactory sortFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_sortFactory = sortFactory;
	}

	private static SortFactory _sortFactory;

}