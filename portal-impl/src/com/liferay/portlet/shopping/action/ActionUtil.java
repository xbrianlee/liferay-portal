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

package com.liferay.portlet.shopping.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.shopping.model.ShoppingCategory;
import com.liferay.portlet.shopping.model.ShoppingCategoryConstants;
import com.liferay.portlet.shopping.model.ShoppingCoupon;
import com.liferay.portlet.shopping.model.ShoppingItem;
import com.liferay.portlet.shopping.model.ShoppingOrder;
import com.liferay.portlet.shopping.service.ShoppingCategoryServiceUtil;
import com.liferay.portlet.shopping.service.ShoppingCouponServiceUtil;
import com.liferay.portlet.shopping.service.ShoppingItemServiceUtil;
import com.liferay.portlet.shopping.service.ShoppingOrderServiceUtil;
import com.liferay.portlet.shopping.service.permission.ShoppingPermission;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class ActionUtil {

	public static void getCategory(HttpServletRequest request)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long categoryId = ParamUtil.getLong(request, "categoryId");

		ShoppingCategory category = null;

		if ((categoryId > 0) &&
			(categoryId !=
				ShoppingCategoryConstants.DEFAULT_PARENT_CATEGORY_ID)) {

			category = ShoppingCategoryServiceUtil.getCategory(categoryId);
		}
		else {
			ShoppingPermission.check(
				themeDisplay.getPermissionChecker(),
				themeDisplay.getScopeGroupId(), ActionKeys.VIEW);
		}

		request.setAttribute(WebKeys.SHOPPING_CATEGORY, category);
	}

	public static void getCategory(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getCategory(request);
	}

	public static void getCoupon(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long couponId = ParamUtil.getLong(request, "couponId");

		ShoppingCoupon coupon = null;

		if (couponId > 0) {
			coupon = ShoppingCouponServiceUtil.getCoupon(
				themeDisplay.getScopeGroupId(), couponId);
		}

		request.setAttribute(WebKeys.SHOPPING_COUPON, coupon);
	}

	public static void getCoupon(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getCoupon(request);
	}

	public static void getItem(HttpServletRequest request) throws Exception {
		long itemId = ParamUtil.getLong(request, "itemId");

		ShoppingItem item = null;

		if (itemId > 0) {
			item = ShoppingItemServiceUtil.getItem(itemId);
		}

		request.setAttribute(WebKeys.SHOPPING_ITEM, item);
	}

	public static void getItem(PortletRequest portletRequest) throws Exception {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getItem(request);
	}

	public static void getOrder(HttpServletRequest request) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		long orderId = ParamUtil.getLong(request, "orderId");

		ShoppingOrder order = null;

		if (orderId > 0) {
			order = ShoppingOrderServiceUtil.getOrder(
				themeDisplay.getScopeGroupId(), orderId);
		}

		request.setAttribute(WebKeys.SHOPPING_ORDER, order);
	}

	public static void getOrder(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getOrder(request);
	}

}