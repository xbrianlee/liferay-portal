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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.shopping.NoSuchCouponException;
import com.liferay.portlet.shopping.model.ShoppingCartItem;
import com.liferay.portlet.shopping.model.ShoppingCoupon;
import com.liferay.portlet.shopping.service.ShoppingCartLocalServiceUtil;
import com.liferay.portlet.shopping.service.ShoppingCouponLocalServiceUtil;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCartImpl extends ShoppingCartBaseImpl {

	public ShoppingCartImpl() {
	}

	@Override
	public void addItemId(long itemId, String fields) {
		setItemIds(
			StringUtil.add(
				getItemIds(), itemId + fields, StringPool.COMMA, true));
	}

	@Override
	public ShoppingCoupon getCoupon() throws PortalException, SystemException {
		ShoppingCoupon coupon = null;

		if (Validator.isNotNull(getCouponCodes())) {
			String code = StringUtil.split(getCouponCodes())[0];

			try {
				coupon = ShoppingCouponLocalServiceUtil.getCoupon(code);
			}
			catch (NoSuchCouponException nsce) {
			}
		}

		return coupon;
	}

	@Override
	public Map<ShoppingCartItem, Integer> getItems() throws SystemException {
		return ShoppingCartLocalServiceUtil.getItems(
			getGroupId(), getItemIds());
	}

	@Override
	public int getItemsSize() {
		return StringUtil.split(getItemIds()).length;
	}

}