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

package com.liferay.portlet.shopping;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class CouponLimitCategoriesException extends PortalException {

	public CouponLimitCategoriesException() {
		super();
	}

	public CouponLimitCategoriesException(String msg) {
		super(msg);
	}

	public CouponLimitCategoriesException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CouponLimitCategoriesException(Throwable cause) {
		super(cause);
	}

	public List<Long> getCategoryIds() {
		return _categoryIds;
	}

	public void setCategoryIds(List<Long> categoryIds) {
		_categoryIds = categoryIds;
	}

	private List<Long> _categoryIds;

}