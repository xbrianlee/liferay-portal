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
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

/**
 * @author Eduardo Garcia
 */
public class TemplateIdComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC = "DDMTemplate.templateId ASC";

	public static final String ORDER_BY_DESC = "DDMTemplate.templateId DESC";

	public static final String[] ORDER_BY_FIELDS = {"templateId"};

	public TemplateIdComparator() {
		this(false);
	}

	public TemplateIdComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		DDMTemplate template1 = (DDMTemplate)obj1;
		DDMTemplate template2 = (DDMTemplate)obj2;

		long templateId1 = template1.getTemplateId();
		long templateId2 = template2.getTemplateId();

		int value = 0;

		if (templateId1 < templateId2) {
			value = -1;
		}
		else if (templateId1 > templateId2) {
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