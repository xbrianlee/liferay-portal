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
public class ShoppingOrderConstants {

	public static final String STATUS_CHECKOUT = "LIFERAY_STATUS_CHECKOUT";

	public static final String STATUS_COMPLETED = "Completed";

	public static final String STATUS_DENIED = "Denied";

	public static final String STATUS_LATEST = "LIFERAY_STATUS_LATEST";

	public static final String STATUS_PENDING = "Pending";

	public static final String STATUS_REFUNDED = "Refunded";

	public static final String[] STATUSES = {
		"checkout", "completed", "denied", "pending", "refunded"
	};

}