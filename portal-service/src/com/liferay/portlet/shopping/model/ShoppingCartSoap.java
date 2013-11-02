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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingCartSoap implements Serializable {
	public static ShoppingCartSoap toSoapModel(ShoppingCart model) {
		ShoppingCartSoap soapModel = new ShoppingCartSoap();

		soapModel.setCartId(model.getCartId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setItemIds(model.getItemIds());
		soapModel.setCouponCodes(model.getCouponCodes());
		soapModel.setAltShipping(model.getAltShipping());
		soapModel.setInsure(model.getInsure());

		return soapModel;
	}

	public static ShoppingCartSoap[] toSoapModels(ShoppingCart[] models) {
		ShoppingCartSoap[] soapModels = new ShoppingCartSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ShoppingCartSoap[][] toSoapModels(ShoppingCart[][] models) {
		ShoppingCartSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ShoppingCartSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ShoppingCartSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ShoppingCartSoap[] toSoapModels(List<ShoppingCart> models) {
		List<ShoppingCartSoap> soapModels = new ArrayList<ShoppingCartSoap>(models.size());

		for (ShoppingCart model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ShoppingCartSoap[soapModels.size()]);
	}

	public ShoppingCartSoap() {
	}

	public long getPrimaryKey() {
		return _cartId;
	}

	public void setPrimaryKey(long pk) {
		setCartId(pk);
	}

	public long getCartId() {
		return _cartId;
	}

	public void setCartId(long cartId) {
		_cartId = cartId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getItemIds() {
		return _itemIds;
	}

	public void setItemIds(String itemIds) {
		_itemIds = itemIds;
	}

	public String getCouponCodes() {
		return _couponCodes;
	}

	public void setCouponCodes(String couponCodes) {
		_couponCodes = couponCodes;
	}

	public int getAltShipping() {
		return _altShipping;
	}

	public void setAltShipping(int altShipping) {
		_altShipping = altShipping;
	}

	public boolean getInsure() {
		return _insure;
	}

	public boolean isInsure() {
		return _insure;
	}

	public void setInsure(boolean insure) {
		_insure = insure;
	}

	private long _cartId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _itemIds;
	private String _couponCodes;
	private int _altShipping;
	private boolean _insure;
}