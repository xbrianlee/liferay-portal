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

package com.liferay.portlet.social.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.social.service.http.SocialActivitySettingServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.social.service.http.SocialActivitySettingServiceSoap
 * @generated
 */
public class SocialActivitySettingSoap implements Serializable {
	public static SocialActivitySettingSoap toSoapModel(
		SocialActivitySetting model) {
		SocialActivitySettingSoap soapModel = new SocialActivitySettingSoap();

		soapModel.setActivitySettingId(model.getActivitySettingId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setActivityType(model.getActivityType());
		soapModel.setName(model.getName());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static SocialActivitySettingSoap[] toSoapModels(
		SocialActivitySetting[] models) {
		SocialActivitySettingSoap[] soapModels = new SocialActivitySettingSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SocialActivitySettingSoap[][] toSoapModels(
		SocialActivitySetting[][] models) {
		SocialActivitySettingSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SocialActivitySettingSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SocialActivitySettingSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SocialActivitySettingSoap[] toSoapModels(
		List<SocialActivitySetting> models) {
		List<SocialActivitySettingSoap> soapModels = new ArrayList<SocialActivitySettingSoap>(models.size());

		for (SocialActivitySetting model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SocialActivitySettingSoap[soapModels.size()]);
	}

	public SocialActivitySettingSoap() {
	}

	public long getPrimaryKey() {
		return _activitySettingId;
	}

	public void setPrimaryKey(long pk) {
		setActivitySettingId(pk);
	}

	public long getActivitySettingId() {
		return _activitySettingId;
	}

	public void setActivitySettingId(long activitySettingId) {
		_activitySettingId = activitySettingId;
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

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public int getActivityType() {
		return _activityType;
	}

	public void setActivityType(int activityType) {
		_activityType = activityType;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	private long _activitySettingId;
	private long _groupId;
	private long _companyId;
	private long _classNameId;
	private int _activityType;
	private String _name;
	private String _value;
}