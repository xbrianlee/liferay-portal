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

package com.liferay.portlet.asset.model;

import com.liferay.portal.kernel.util.Validator;

/**
 * @author Roberto Díaz
 */
public class AssetQueryRule {

	public AssetQueryRule(
		boolean contains, boolean andOperator, String name, String[] values) {

		_contains = contains;
		_andOperator = andOperator;
		_name = name;
		_values = values;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetQueryRule)) {
			return false;
		}

		AssetQueryRule assetQueryRule = (AssetQueryRule)obj;

		if (Validator.equals(_contains, assetQueryRule._contains) &&
			Validator.equals(_andOperator, assetQueryRule._andOperator) &&
			Validator.equals(_name, assetQueryRule._name)) {

			return true;
		}

		return false;
	}

	public String getName() {
		return _name;
	}

	public String[] getValues() {
		return _values;
	}

	public boolean isAndOperator() {
		return _andOperator;
	}

	public boolean isContains() {
		return _contains;
	}

	public void setAndOperator(boolean andOperator) {
		_andOperator = andOperator;
	}

	public void setContains(boolean contains) {
		_contains = contains;
	}

	public void setName(String name) {
		_name = name;
	}

	public void setValues(String[] values) {
		_values = values;
	}

	private boolean _andOperator;
	private boolean _contains;
	private String _name;
	private String[] _values;

}