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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.shopping.model.ShoppingCategory;
import com.liferay.portlet.shopping.model.ShoppingItem;
import com.liferay.portlet.shopping.model.ShoppingItemPrice;
import com.liferay.portlet.shopping.service.ShoppingCategoryLocalServiceUtil;
import com.liferay.portlet.shopping.service.ShoppingItemPriceLocalServiceUtil;
import com.liferay.portlet.shopping.util.comparator.ItemNameComparator;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemImpl extends ShoppingItemBaseImpl {

	public ShoppingItemImpl() {
	}

	@Override
	public int compareTo(ShoppingItem item) {
		return new ItemNameComparator(true).compare(this, item);
	}

	@Override
	public ShoppingCategory getCategory() {
		ShoppingCategory category = null;

		if (getCategoryId() > 0) {
			try {
				category = ShoppingCategoryLocalServiceUtil.getCategory(
					getCategoryId());
			}
			catch (Exception e) {
				category = new ShoppingCategoryImpl();

				category.setGroupId(getGroupId());

				_log.error(e);
			}
		}
		else {
			category = new ShoppingCategoryImpl();

			category.setGroupId(getGroupId());
		}

		return category;
	}

	@Override
	public String[] getFieldsQuantitiesArray() {
		return _fieldsQuantitiesArray;
	}

	@Override
	public List<ShoppingItemPrice> getItemPrices()
		throws PortalException, SystemException {

		return ShoppingItemPriceLocalServiceUtil.getItemPrices(getItemId());
	}

	@Override
	public void setFieldsQuantities(String fieldsQuantities) {
		_fieldsQuantitiesArray = StringUtil.split(fieldsQuantities);

		super.setFieldsQuantities(fieldsQuantities);
	}

	@Override
	public void setFieldsQuantitiesArray(String[] fieldsQuantitiesArray) {
		_fieldsQuantitiesArray = fieldsQuantitiesArray;

		super.setFieldsQuantities(StringUtil.merge(fieldsQuantitiesArray));
	}

	private static Log _log = LogFactoryUtil.getLog(ShoppingItemImpl.class);

	private String[] _fieldsQuantitiesArray;

}