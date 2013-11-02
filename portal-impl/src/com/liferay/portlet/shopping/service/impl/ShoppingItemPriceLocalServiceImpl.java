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

package com.liferay.portlet.shopping.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.shopping.model.ShoppingItem;
import com.liferay.portlet.shopping.model.ShoppingItemPrice;
import com.liferay.portlet.shopping.model.ShoppingItemPriceConstants;
import com.liferay.portlet.shopping.service.base.ShoppingItemPriceLocalServiceBaseImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemPriceLocalServiceImpl
	extends ShoppingItemPriceLocalServiceBaseImpl {

	@Override
	public List<ShoppingItemPrice> getItemPrices(long itemId)
		throws PortalException, SystemException {

		ShoppingItem item = shoppingItemPersistence.findByPrimaryKey(itemId);

		List<ShoppingItemPrice> itemPrices =
			shoppingItemPricePersistence.findByItemId(itemId);

		if (itemPrices.isEmpty()) {
			itemPrices = new ArrayList<ShoppingItemPrice>();

			ShoppingItemPrice itemPrice = shoppingItemPricePersistence.create(
				0);

			itemPrice.setItemId(itemId);
			itemPrice.setMinQuantity(item.getMinQuantity());
			itemPrice.setMaxQuantity(item.getMaxQuantity());
			itemPrice.setPrice(item.getPrice());
			itemPrice.setDiscount(item.getDiscount());
			itemPrice.setTaxable(item.isTaxable());
			itemPrice.setShipping(item.getShipping());
			itemPrice.setUseShippingFormula(item.isUseShippingFormula());
			itemPrice.setStatus(
				ShoppingItemPriceConstants.STATUS_ACTIVE_DEFAULT);

			itemPrices.add(itemPrice);
		}

		return itemPrices;
	}

}