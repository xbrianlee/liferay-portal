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

package com.liferay.portal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class ResourceActionSoap implements Serializable {
	public static ResourceActionSoap toSoapModel(ResourceAction model) {
		ResourceActionSoap soapModel = new ResourceActionSoap();

		soapModel.setResourceActionId(model.getResourceActionId());
		soapModel.setName(model.getName());
		soapModel.setActionId(model.getActionId());
		soapModel.setBitwiseValue(model.getBitwiseValue());

		return soapModel;
	}

	public static ResourceActionSoap[] toSoapModels(ResourceAction[] models) {
		ResourceActionSoap[] soapModels = new ResourceActionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ResourceActionSoap[][] toSoapModels(ResourceAction[][] models) {
		ResourceActionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ResourceActionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ResourceActionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ResourceActionSoap[] toSoapModels(List<ResourceAction> models) {
		List<ResourceActionSoap> soapModels = new ArrayList<ResourceActionSoap>(models.size());

		for (ResourceAction model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ResourceActionSoap[soapModels.size()]);
	}

	public ResourceActionSoap() {
	}

	public long getPrimaryKey() {
		return _resourceActionId;
	}

	public void setPrimaryKey(long pk) {
		setResourceActionId(pk);
	}

	public long getResourceActionId() {
		return _resourceActionId;
	}

	public void setResourceActionId(long resourceActionId) {
		_resourceActionId = resourceActionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getActionId() {
		return _actionId;
	}

	public void setActionId(String actionId) {
		_actionId = actionId;
	}

	public long getBitwiseValue() {
		return _bitwiseValue;
	}

	public void setBitwiseValue(long bitwiseValue) {
		_bitwiseValue = bitwiseValue;
	}

	private long _resourceActionId;
	private String _name;
	private String _actionId;
	private long _bitwiseValue;
}