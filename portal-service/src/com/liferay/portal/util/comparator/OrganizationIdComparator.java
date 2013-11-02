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
import com.liferay.portal.model.Organization;

/**
 * @author Shinn Lok
 */
public class OrganizationIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"Organization_.organizationId ASC";

	public static final String ORDER_BY_DESC =
		"Organization_.organizationId DESC";

	public static final String[] ORDER_BY_FIELDS = {"organizationId"};

	public OrganizationIdComparator() {
		this(false);
	}

	public OrganizationIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		Organization organization1 = (Organization)obj1;
		Organization organization2 = (Organization)obj2;

		long organizationId1 = organization1.getOrganizationId();
		long organizationId2 = organization2.getOrganizationId();

		int value = 0;

		if (organizationId1 < organizationId2) {
			value = -1;
		}
		else if (organizationId1 > organizationId2) {
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