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
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.PortletPreferencesServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.http.PortletPreferencesServiceSoap
 * @generated
 */
public class PortletPreferencesSoap implements Serializable {
	public static PortletPreferencesSoap toSoapModel(PortletPreferences model) {
		PortletPreferencesSoap soapModel = new PortletPreferencesSoap();

		soapModel.setPortletPreferencesId(model.getPortletPreferencesId());
		soapModel.setOwnerId(model.getOwnerId());
		soapModel.setOwnerType(model.getOwnerType());
		soapModel.setPlid(model.getPlid());
		soapModel.setPortletId(model.getPortletId());
		soapModel.setPreferences(model.getPreferences());

		return soapModel;
	}

	public static PortletPreferencesSoap[] toSoapModels(
		PortletPreferences[] models) {
		PortletPreferencesSoap[] soapModels = new PortletPreferencesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PortletPreferencesSoap[][] toSoapModels(
		PortletPreferences[][] models) {
		PortletPreferencesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PortletPreferencesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PortletPreferencesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PortletPreferencesSoap[] toSoapModels(
		List<PortletPreferences> models) {
		List<PortletPreferencesSoap> soapModels = new ArrayList<PortletPreferencesSoap>(models.size());

		for (PortletPreferences model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PortletPreferencesSoap[soapModels.size()]);
	}

	public PortletPreferencesSoap() {
	}

	public long getPrimaryKey() {
		return _portletPreferencesId;
	}

	public void setPrimaryKey(long pk) {
		setPortletPreferencesId(pk);
	}

	public long getPortletPreferencesId() {
		return _portletPreferencesId;
	}

	public void setPortletPreferencesId(long portletPreferencesId) {
		_portletPreferencesId = portletPreferencesId;
	}

	public long getOwnerId() {
		return _ownerId;
	}

	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
	}

	public int getOwnerType() {
		return _ownerType;
	}

	public void setOwnerType(int ownerType) {
		_ownerType = ownerType;
	}

	public long getPlid() {
		return _plid;
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private long _portletPreferencesId;
	private long _ownerId;
	private int _ownerType;
	private long _plid;
	private String _portletId;
	private String _preferences;
}