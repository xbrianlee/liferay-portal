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
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ShoppingItemFieldSoap implements Serializable {
	public static ShoppingItemFieldSoap toSoapModel(ShoppingItemField model) {
		ShoppingItemFieldSoap soapModel = new ShoppingItemFieldSoap();

		soapModel.setItemFieldId(model.getItemFieldId());
		soapModel.setItemId(model.getItemId());
		soapModel.setName(model.getName());
		soapModel.setValues(model.getValues());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static ShoppingItemFieldSoap[] toSoapModels(
		ShoppingItemField[] models) {
		ShoppingItemFieldSoap[] soapModels = new ShoppingItemFieldSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ShoppingItemFieldSoap[][] toSoapModels(
		ShoppingItemField[][] models) {
		ShoppingItemFieldSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ShoppingItemFieldSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ShoppingItemFieldSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ShoppingItemFieldSoap[] toSoapModels(
		List<ShoppingItemField> models) {
		List<ShoppingItemFieldSoap> soapModels = new ArrayList<ShoppingItemFieldSoap>(models.size());

		for (ShoppingItemField model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ShoppingItemFieldSoap[soapModels.size()]);
	}

	public ShoppingItemFieldSoap() {
	}

	public long getPrimaryKey() {
		return _itemFieldId;
	}

	public void setPrimaryKey(long pk) {
		setItemFieldId(pk);
	}

	public long getItemFieldId() {
		return _itemFieldId;
	}

	public void setItemFieldId(long itemFieldId) {
		_itemFieldId = itemFieldId;
	}

	public long getItemId() {
		return _itemId;
	}

	public void setItemId(long itemId) {
		_itemId = itemId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getValues() {
		return _values;
	}

	public void setValues(String values) {
		_values = values;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private long _itemFieldId;
	private long _itemId;
	private String _name;
	private String _values;
	private String _description;
}