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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.shopping.model.ShoppingItem;
import com.liferay.portlet.shopping.model.ShoppingItemField;
import com.liferay.portlet.shopping.model.ShoppingItemPrice;
import com.liferay.portlet.shopping.service.base.ShoppingItemServiceBaseImpl;
import com.liferay.portlet.shopping.service.permission.ShoppingCategoryPermission;
import com.liferay.portlet.shopping.service.permission.ShoppingItemPermission;

import java.io.File;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class ShoppingItemServiceImpl extends ShoppingItemServiceBaseImpl {

	@Override
	public void addBookItems(long groupId, long categoryId, String[] isbns)
		throws PortalException, SystemException {

		ShoppingCategoryPermission.check(
			getPermissionChecker(), groupId, categoryId, ActionKeys.ADD_ITEM);

		shoppingItemLocalService.addBookItems(
			getUserId(), groupId, categoryId, isbns);
	}

	@Override
	public ShoppingItem addItem(
			long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			File smallFile, boolean mediumImage, String mediumImageURL,
			File mediumFile, boolean largeImage, String largeImageURL,
			File largeFile, List<ShoppingItemField> itemFields,
			List<ShoppingItemPrice> itemPrices, ServiceContext serviceContext)
		throws PortalException, SystemException {

		ShoppingCategoryPermission.check(
			getPermissionChecker(), groupId, categoryId, ActionKeys.ADD_ITEM);

		return shoppingItemLocalService.addItem(
			getUserId(), groupId, categoryId, sku, name, description,
			properties, fieldsQuantities, requiresShipping, stockQuantity,
			featured, sale, smallImage, smallImageURL, smallFile, mediumImage,
			mediumImageURL, mediumFile, largeImage, largeImageURL, largeFile,
			itemFields, itemPrices, serviceContext);
	}

	@Override
	public void deleteItem(long itemId)
		throws PortalException, SystemException {

		ShoppingItemPermission.check(
			getPermissionChecker(), itemId, ActionKeys.DELETE);

		shoppingItemLocalService.deleteItem(itemId);
	}

	@Override
	public int getCategoriesItemsCount(long groupId, List<Long> categoryIds)
		throws SystemException {

		return shoppingItemFinder.filterCountByG_C(groupId, categoryIds);
	}

	@Override
	public ShoppingItem getItem(long itemId)
		throws PortalException, SystemException {

		ShoppingItemPermission.check(
			getPermissionChecker(), itemId, ActionKeys.VIEW);

		return shoppingItemLocalService.getItem(itemId);
	}

	@Override
	public List<ShoppingItem> getItems(long groupId, long categoryId)
		throws SystemException {

		return shoppingItemPersistence.filterFindByG_C(groupId, categoryId);
	}

	@Override
	public List<ShoppingItem> getItems(
			long groupId, long categoryId, int start, int end,
			OrderByComparator obc)
		throws SystemException {

		return shoppingItemPersistence.filterFindByG_C(
			groupId, categoryId, start, end, obc);
	}

	@Override
	public int getItemsCount(long groupId, long categoryId)
		throws SystemException {

		return shoppingItemPersistence.filterCountByG_C(groupId, categoryId);
	}

	@Override
	public ShoppingItem[] getItemsPrevAndNext(
			long itemId, OrderByComparator obc)
		throws PortalException, SystemException {

		ShoppingItem item = shoppingItemPersistence.findByPrimaryKey(itemId);

		return shoppingItemPersistence.filterFindByG_C_PrevAndNext(
			item.getItemId(), item.getGroupId(), item.getCategoryId(), obc);
	}

	@Override
	public ShoppingItem updateItem(
			long itemId, long groupId, long categoryId, String sku, String name,
			String description, String properties, String fieldsQuantities,
			boolean requiresShipping, int stockQuantity, boolean featured,
			Boolean sale, boolean smallImage, String smallImageURL,
			File smallFile, boolean mediumImage, String mediumImageURL,
			File mediumFile, boolean largeImage, String largeImageURL,
			File largeFile, List<ShoppingItemField> itemFields,
			List<ShoppingItemPrice> itemPrices, ServiceContext serviceContext)
		throws PortalException, SystemException {

		ShoppingItemPermission.check(
			getPermissionChecker(), itemId, ActionKeys.UPDATE);

		return shoppingItemLocalService.updateItem(
			getUserId(), itemId, groupId, categoryId, sku, name, description,
			properties, fieldsQuantities, requiresShipping, stockQuantity,
			featured, sale, smallImage, smallImageURL, smallFile, mediumImage,
			mediumImageURL, mediumFile, largeImage, largeImageURL, largeFile,
			itemFields, itemPrices, serviceContext);
	}

}