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

package com.liferay.portlet.messageboards.util.comparator;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.messageboards.model.MBThread;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class ThreadLastPostDateComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"MBThread.lastPostDate ASC, MBThread.threadId ASC";

	public static final String[] ORDER_BY_CONDITION_FIELDS = {"lastPostDate"};

	public static final String ORDER_BY_DESC =
		"MBThread.lastPostDate DESC, MBThread.threadId DESC";

	public static final String[] ORDER_BY_FIELDS = {"lastPostDate", "threadId"};

	public ThreadLastPostDateComparator() {
		this(false);
	}

	public ThreadLastPostDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		MBThread thread1 = (MBThread)obj1;
		MBThread thread2 = (MBThread)obj2;

		Date lastPostDate1 = thread1.getLastPostDate();
		Date lastPostDate2 = thread2.getLastPostDate();

		boolean ignoreMilliseconds = false;

		DB db = DBFactoryUtil.getDB();

		if (!db.isSupportsDateMilliseconds()) {
			ignoreMilliseconds = true;
		}

		int value = DateUtil.compareTo(
			lastPostDate1, lastPostDate2, ignoreMilliseconds);

		if (value == 0) {
			if (thread1.getThreadId() < thread2.getThreadId()) {
				value = -1;
			}
			else if (thread1.getThreadId() > thread2.getThreadId()) {
				value = 1;
			}
		}

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
	public String[] getOrderByConditionFields() {
		return ORDER_BY_CONDITION_FIELDS;
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