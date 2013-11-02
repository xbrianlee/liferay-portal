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
public class ServiceComponentSoap implements Serializable {
	public static ServiceComponentSoap toSoapModel(ServiceComponent model) {
		ServiceComponentSoap soapModel = new ServiceComponentSoap();

		soapModel.setServiceComponentId(model.getServiceComponentId());
		soapModel.setBuildNamespace(model.getBuildNamespace());
		soapModel.setBuildNumber(model.getBuildNumber());
		soapModel.setBuildDate(model.getBuildDate());
		soapModel.setData(model.getData());

		return soapModel;
	}

	public static ServiceComponentSoap[] toSoapModels(ServiceComponent[] models) {
		ServiceComponentSoap[] soapModels = new ServiceComponentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ServiceComponentSoap[][] toSoapModels(
		ServiceComponent[][] models) {
		ServiceComponentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ServiceComponentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ServiceComponentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ServiceComponentSoap[] toSoapModels(
		List<ServiceComponent> models) {
		List<ServiceComponentSoap> soapModels = new ArrayList<ServiceComponentSoap>(models.size());

		for (ServiceComponent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ServiceComponentSoap[soapModels.size()]);
	}

	public ServiceComponentSoap() {
	}

	public long getPrimaryKey() {
		return _serviceComponentId;
	}

	public void setPrimaryKey(long pk) {
		setServiceComponentId(pk);
	}

	public long getServiceComponentId() {
		return _serviceComponentId;
	}

	public void setServiceComponentId(long serviceComponentId) {
		_serviceComponentId = serviceComponentId;
	}

	public String getBuildNamespace() {
		return _buildNamespace;
	}

	public void setBuildNamespace(String buildNamespace) {
		_buildNamespace = buildNamespace;
	}

	public long getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(long buildNumber) {
		_buildNumber = buildNumber;
	}

	public long getBuildDate() {
		return _buildDate;
	}

	public void setBuildDate(long buildDate) {
		_buildDate = buildDate;
	}

	public String getData() {
		return _data;
	}

	public void setData(String data) {
		_data = data;
	}

	private long _serviceComponentId;
	private String _buildNamespace;
	private long _buildNumber;
	private long _buildDate;
	private String _data;
}