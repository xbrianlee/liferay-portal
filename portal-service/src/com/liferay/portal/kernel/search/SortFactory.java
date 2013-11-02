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

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface SortFactory {

	public Sort create(String fieldName, boolean reverse);

	public Sort create(String fieldName, int type, boolean reverse);

	public Sort[] getDefaultSorts();

	public Sort getSort(
		Class<?> clazz, int type, String orderByCol, String orderByType);

	public Sort getSort(Class<?> clazz, String orderByCol, String orderByType);

	public Sort[] toArray(List<Sort> sorts);

}