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

package com.liferay.portlet.softwarecatalog.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.softwarecatalog.util.comparator.ProductEntryCreateDateComparator;
import com.liferay.portlet.softwarecatalog.util.comparator.ProductEntryModifiedDateComparator;
import com.liferay.portlet.softwarecatalog.util.comparator.ProductEntryNameComparator;
import com.liferay.portlet.softwarecatalog.util.comparator.ProductEntryTypeComparator;

/**
 * @author Brian Wing Shun Chan
 */
public class SCUtil {

	public static OrderByComparator getProductEntryOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("create-date")) {
			orderByComparator = new ProductEntryCreateDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("modified-date")) {
			orderByComparator = new ProductEntryModifiedDateComparator(
				orderByAsc);
		}
		else if (orderByCol.equals("name")) {
			orderByComparator = new ProductEntryNameComparator(orderByAsc);
		}
		else if (orderByCol.equals("type")) {
			orderByComparator = new ProductEntryTypeComparator(orderByAsc);
		}

		return orderByComparator;
	}

}