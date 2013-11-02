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

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.documentlibrary.model.DLFileRank;

/**
 * @author Brian Wing Shun Chan
 */
public class FileRankCreateDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "DLFileRank.createDate ASC";

	public static final String ORDER_BY_DESC = "DLFileRank.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public FileRankCreateDateComparator() {
		this(false);
	}

	public FileRankCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DLFileRank dlFileRank1 = (DLFileRank)obj1;
		DLFileRank dlFileRank2 = (DLFileRank)obj2;

		int value = DateUtil.compareTo(
			dlFileRank1.getCreateDate(), dlFileRank2.getCreateDate());

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