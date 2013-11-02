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
public class UserIdMapperSoap implements Serializable {
	public static UserIdMapperSoap toSoapModel(UserIdMapper model) {
		UserIdMapperSoap soapModel = new UserIdMapperSoap();

		soapModel.setUserIdMapperId(model.getUserIdMapperId());
		soapModel.setUserId(model.getUserId());
		soapModel.setType(model.getType());
		soapModel.setDescription(model.getDescription());
		soapModel.setExternalUserId(model.getExternalUserId());

		return soapModel;
	}

	public static UserIdMapperSoap[] toSoapModels(UserIdMapper[] models) {
		UserIdMapperSoap[] soapModels = new UserIdMapperSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static UserIdMapperSoap[][] toSoapModels(UserIdMapper[][] models) {
		UserIdMapperSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new UserIdMapperSoap[models.length][models[0].length];
		}
		else {
			soapModels = new UserIdMapperSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static UserIdMapperSoap[] toSoapModels(List<UserIdMapper> models) {
		List<UserIdMapperSoap> soapModels = new ArrayList<UserIdMapperSoap>(models.size());

		for (UserIdMapper model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new UserIdMapperSoap[soapModels.size()]);
	}

	public UserIdMapperSoap() {
	}

	public long getPrimaryKey() {
		return _userIdMapperId;
	}

	public void setPrimaryKey(long pk) {
		setUserIdMapperId(pk);
	}

	public long getUserIdMapperId() {
		return _userIdMapperId;
	}

	public void setUserIdMapperId(long userIdMapperId) {
		_userIdMapperId = userIdMapperId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getExternalUserId() {
		return _externalUserId;
	}

	public void setExternalUserId(String externalUserId) {
		_externalUserId = externalUserId;
	}

	private long _userIdMapperId;
	private long _userId;
	private String _type;
	private String _description;
	private String _externalUserId;
}