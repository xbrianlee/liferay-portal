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

package com.liferay.portlet.dynamicdatamapping.util.comparator;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;

/**
 * @author Miguel Pastor
 */
public class StructureStructureKeyComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "DDMStructure.structureKey ASC";

	public static final String ORDER_BY_DESC = "DDMStructure.structureKey DESC";

	public static final String[] ORDER_BY_FIELDS = {"structureKey"};

	public StructureStructureKeyComparator() {
		this(false);
	}

	public StructureStructureKeyComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DDMStructure structure1 = (DDMStructure)obj1;

		String structureKey1 = structure1.getStructureKey();

		structureKey1 = StringUtil.toLowerCase(structureKey1);

		DDMStructure structure2 = (DDMStructure)obj2;

		String structureKey2 = structure2.getStructureKey();

		structureKey2 = StringUtil.toLowerCase(structureKey2);

		int value = structureKey1.compareTo(structureKey2);

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