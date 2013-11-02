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
 * @author Marcellus Tavares
 */
public class FieldConditionImpl implements FieldCondition {

	public FieldConditionImpl(
		String name, Object value, ComparisonOperator comparisonOperator) {

		_name = name;
		_value = value;
		_comparisonOperator = comparisonOperator;
	}

	@Override
	public ComparisonOperator getComparisonOperator() {
		return _comparisonOperator;
	}

	@Override
	public String getName() {
		return _name;
	}

	@Override
	public Object getValue() {
		return _value;
	}

	@Override
	public boolean isJunction() {
		return _JUNCTION;
	}

	private static final boolean _JUNCTION = false;

	private ComparisonOperator _comparisonOperator;
	private String _name;
	private Object _value;

}