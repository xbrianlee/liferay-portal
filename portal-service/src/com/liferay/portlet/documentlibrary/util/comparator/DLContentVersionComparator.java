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

package com.liferay.portlet.documentlibrary.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.documentlibrary.model.DLContent;

/**
 * @author Shuyang Zhou
 */
public class DLContentVersionComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "DLContent.version ASC";

	public static final String ORDER_BY_DESC = "DLContent.version DESC";

	public static final String[] ORDER_BY_FIELDS = {"version"};

	public DLContentVersionComparator() {
		this(false);
	}

	public DLContentVersionComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DLContent content1 = (DLContent)obj1;
		DLContent content2 = (DLContent)obj2;

		String version1 = content1.getVersion();
		String version2 = content2.getVersion();

		int value = version1.compareTo(version2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}