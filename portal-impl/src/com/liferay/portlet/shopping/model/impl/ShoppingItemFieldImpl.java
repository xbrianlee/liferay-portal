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

package com.liferay.portlet.shopping.model.impl;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemFieldImpl extends ShoppingItemFieldBaseImpl {

	public ShoppingItemFieldImpl() {
	}

	@Override
	public String[] getValuesArray() {
		return _valuesArray;
	}

	@Override
	public void setValues(String values) {
		_valuesArray = StringUtil.split(values);

		super.setValues(values);
	}

	@Override
	public void setValuesArray(String[] valuesArray) {
		_valuesArray = valuesArray;

		super.setValues(StringUtil.merge(valuesArray));
	}

	private String[] _valuesArray;

}