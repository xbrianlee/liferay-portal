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

package com.liferay.portlet.dynamicdatamapping.storage.query;

/**
 * @author Michael C. Han
 */
public enum ComparisonOperator {

	EQUALS("="), EXCLUDES(" EXCLUDES "), GREATER_THAN(">"),
	GREATER_THAN_OR_EQUAL_TO(">="), IN(" IN "), INCLUDES(" INCLUDES "),
	JOIN(" = "), LESS_THAN("<"), LESS_THAN_OR_EQUAL_TO("<="), LIKE(" LIKE "),
	NOT_EQUALS("!="), NOT_IN(" NOT IN ");

	public String getValue() {
		return _value;
	}

	@Override
	public String toString() {
		return _value;
	}

	private ComparisonOperator(String value) {
		_value = value;
	}

	private String _value;

}