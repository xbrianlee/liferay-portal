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

package com.liferay.portlet.backgroundtask.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;

/**
 * @author Eduardo Garcia
 */
public class BackgroundTaskComparatorFactoryUtil {

	public static OrderByComparator getBackgroundTaskOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("completion-date")) {
			orderByComparator = new BackgroundTaskCompletionDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("create-date")) {
			orderByComparator = new BackgroundTaskCreateDateComparator(
				orderByAsc);
		}

		return orderByComparator;
	}

}