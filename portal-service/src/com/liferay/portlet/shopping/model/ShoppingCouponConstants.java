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

package com.liferay.portlet.shopping.model;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingCouponConstants {

	public static final String DISCOUNT_TYPE_ACTUAL = "actual";

	public static final String DISCOUNT_TYPE_FREE_SHIPPING = "free-shipping";

	public static final String DISCOUNT_TYPE_PERCENTAGE = "percentage";

	public static final String DISCOUNT_TYPE_TAX_FREE = "tax-free";

	public static final String[] DISCOUNT_TYPES = {
		"percentage", "actual", "free-shipping", "tax-free"
	};

}