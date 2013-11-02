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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.shopping.model.ShoppingCart;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing ShoppingCart in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see ShoppingCart
 * @generated
 */
public class ShoppingCartCacheModel implements CacheModel<ShoppingCart>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{cartId=");
		sb.append(cartId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", itemIds=");
		sb.append(itemIds);
		sb.append(", couponCodes=");
		sb.append(couponCodes);
		sb.append(", altShipping=");
		sb.append(altShipping);
		sb.append(", insure=");
		sb.append(insure);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public ShoppingCart toEntityModel() {
		ShoppingCartImpl shoppingCartImpl = new ShoppingCartImpl();

		shoppingCartImpl.setCartId(cartId);
		shoppingCartImpl.setGroupId(groupId);
		shoppingCartImpl.setCompanyId(companyId);
		shoppingCartImpl.setUserId(userId);

		if (userName == null) {
			shoppingCartImpl.setUserName(StringPool.BLANK);
		}
		else {
			shoppingCartImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			shoppingCartImpl.setCreateDate(null);
		}
		else {
			shoppingCartImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			shoppingCartImpl.setModifiedDate(null);
		}
		else {
			shoppingCartImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (itemIds == null) {
			shoppingCartImpl.setItemIds(StringPool.BLANK);
		}
		else {
			shoppingCartImpl.setItemIds(itemIds);
		}

		if (couponCodes == null) {
			shoppingCartImpl.setCouponCodes(StringPool.BLANK);
		}
		else {
			shoppingCartImpl.setCouponCodes(couponCodes);
		}

		shoppingCartImpl.setAltShipping(altShipping);
		shoppingCartImpl.setInsure(insure);

		shoppingCartImpl.resetOriginalValues();

		return shoppingCartImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		cartId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		itemIds = objectInput.readUTF();
		couponCodes = objectInput.readUTF();
		altShipping = objectInput.readInt();
		insure = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(cartId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (itemIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(itemIds);
		}

		if (couponCodes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(couponCodes);
		}

		objectOutput.writeInt(altShipping);
		objectOutput.writeBoolean(insure);
	}

	public long cartId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String itemIds;
	public String couponCodes;
	public int altShipping;
	public boolean insure;
}