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

import com.liferay.portal.kernel.util.HashCode;
import com.liferay.portal.kernel.util.HashCodeFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.shopping.model.ShoppingCartItem;
import com.liferay.portlet.shopping.model.ShoppingItem;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCartItemImpl implements ShoppingCartItem {

	public static String[] getFieldsArray(String fields) {
		return StringUtil.split(fields, '&');
	}

	public ShoppingCartItemImpl(ShoppingItem item, String fields) {
		_item = item;
		_fields = fields;
	}

	@Override
	public int compareTo(ShoppingCartItem cartItem) {
		if (cartItem == null) {
			return -1;
		}

		int value = getItem().compareTo(cartItem.getItem());

		if (value == 0) {
			value = getFields().compareTo(cartItem.getFields());
		}

		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ShoppingCartItem)) {
			return false;
		}

		ShoppingCartItem cartItem = (ShoppingCartItem)obj;

		if (getItem().equals(cartItem.getItem()) &&
			getFields().equals(cartItem.getFields())) {

			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String getCartItemId() {
		long itemId = getItem().getItemId();

		if (Validator.isNull(_fields)) {
			return String.valueOf(itemId);
		}
		else {
			return itemId + "|" + _fields;
		}
	}

	@Override
	public String getFields() {
		return _fields;
	}

	@Override
	public String[] getFieldsArray() {
		return getFieldsArray(_fields);
	}

	@Override
	public ShoppingItem getItem() {
		return _item;
	}

	@Override
	public int hashCode() {
		HashCode hashCode = HashCodeFactoryUtil.getHashCode();

		hashCode.append(_item.getItemId());
		hashCode.append(_fields);

		return hashCode.toHashCode();
	}

	private String _fields;
	private ShoppingItem _item;

}