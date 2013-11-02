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

package com.liferay.portlet.mobiledevicerules.util;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;

/**
 * @author Edward Han
 */
public class RuleGroupInstancePriorityComparator extends OrderByComparator {

	public static final String ORDER_BY_ASC =
		"MDRRuleGroupInstance.priority ASC";

	public static final String ORDER_BY_DESC =
		"MDRRuleGroupInstance.priority DESC";

	public static final String[] ORDER_BY_FIELDS = {"priority"};

	public RuleGroupInstancePriorityComparator() {
		this(true);
	}

	public RuleGroupInstancePriorityComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Object obj1, Object obj2) {
		MDRRuleGroupInstance ruleGroupInstance1 = (MDRRuleGroupInstance)obj1;
		MDRRuleGroupInstance ruleGroupInstance2 = (MDRRuleGroupInstance)obj2;

		int value =
			ruleGroupInstance2.getPriority() - ruleGroupInstance1.getPriority();

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