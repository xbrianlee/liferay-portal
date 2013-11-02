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

package com.liferay.portal.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.LayoutRevision;

/**
 * @author Raymond Augé
 */
public class LayoutRevisionIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"LayoutRevision.layoutRevisionId ASC";

	public static final String ORDER_BY_DESC =
		"LayoutRevision.layoutRevisionId DESC";

	public static final String[] ORDER_BY_FIELDS = {"layoutRevisionId"};

	public LayoutRevisionIdComparator() {
		this(false);
	}

	public LayoutRevisionIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		LayoutRevision layoutRevision1 = (LayoutRevision)obj1;
		LayoutRevision layoutRevision2 = (LayoutRevision)obj2;

		int value = 0;

		if (layoutRevision1.getLayoutRevisionId() >
				layoutRevision2.getLayoutRevisionId()) {

			value = 1;
		}
		else if (layoutRevision1.getLayoutRevisionId() <
					layoutRevision2.getLayoutRevisionId()) {

			value = -1;
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