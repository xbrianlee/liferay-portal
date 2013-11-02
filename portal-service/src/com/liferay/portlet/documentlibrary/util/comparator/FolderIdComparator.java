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
import com.liferay.portlet.documentlibrary.model.DLFolder;

/**
 * @author Shinn Lok
 */
public class FolderIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "DLFolder.folderId ASC";

	public static final String ORDER_BY_DESC = "DLFolder.folderId DESC";

	public static final String[] ORDER_BY_FIELDS = {"folderId"};

	public FolderIdComparator() {
		this(false);
	}

	public FolderIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DLFolder folder1 = (DLFolder)obj1;
		DLFolder folder2 = (DLFolder)obj2;

		long folderId1 = folder1.getFolderId();
		long folderId2 = folder2.getFolderId();

		int value = 0;

		if (folderId1 < folderId2) {
			value = -1;
		}
		else if (folderId1 > folderId2) {
			value = 1;
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
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private boolean _ascending;

}