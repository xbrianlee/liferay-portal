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
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class PasswordTrackerSoap implements Serializable {
	public static PasswordTrackerSoap toSoapModel(PasswordTracker model) {
		PasswordTrackerSoap soapModel = new PasswordTrackerSoap();

		soapModel.setPasswordTrackerId(model.getPasswordTrackerId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setPassword(model.getPassword());

		return soapModel;
	}

	public static PasswordTrackerSoap[] toSoapModels(PasswordTracker[] models) {
		PasswordTrackerSoap[] soapModels = new PasswordTrackerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PasswordTrackerSoap[][] toSoapModels(
		PasswordTracker[][] models) {
		PasswordTrackerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PasswordTrackerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PasswordTrackerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PasswordTrackerSoap[] toSoapModels(
		List<PasswordTracker> models) {
		List<PasswordTrackerSoap> soapModels = new ArrayList<PasswordTrackerSoap>(models.size());

		for (PasswordTracker model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PasswordTrackerSoap[soapModels.size()]);
	}

	public PasswordTrackerSoap() {
	}

	public long getPrimaryKey() {
		return _passwordTrackerId;
	}

	public void setPrimaryKey(long pk) {
		setPasswordTrackerId(pk);
	}

	public long getPasswordTrackerId() {
		return _passwordTrackerId;
	}

	public void setPasswordTrackerId(long passwordTrackerId) {
		_passwordTrackerId = passwordTrackerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	private long _passwordTrackerId;
	private long _userId;
	private Date _createDate;
	private String _password;
}