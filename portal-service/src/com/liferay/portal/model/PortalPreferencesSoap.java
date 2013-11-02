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
public class PortalPreferencesSoap implements Serializable {
	public static PortalPreferencesSoap toSoapModel(PortalPreferences model) {
		PortalPreferencesSoap soapModel = new PortalPreferencesSoap();

		soapModel.setPortalPreferencesId(model.getPortalPreferencesId());
		soapModel.setOwnerId(model.getOwnerId());
		soapModel.setOwnerType(model.getOwnerType());
		soapModel.setPreferences(model.getPreferences());

		return soapModel;
	}

	public static PortalPreferencesSoap[] toSoapModels(
		PortalPreferences[] models) {
		PortalPreferencesSoap[] soapModels = new PortalPreferencesSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PortalPreferencesSoap[][] toSoapModels(
		PortalPreferences[][] models) {
		PortalPreferencesSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PortalPreferencesSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PortalPreferencesSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PortalPreferencesSoap[] toSoapModels(
		List<PortalPreferences> models) {
		List<PortalPreferencesSoap> soapModels = new ArrayList<PortalPreferencesSoap>(models.size());

		for (PortalPreferences model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PortalPreferencesSoap[soapModels.size()]);
	}

	public PortalPreferencesSoap() {
	}

	public long getPrimaryKey() {
		return _portalPreferencesId;
	}

	public void setPrimaryKey(long pk) {
		setPortalPreferencesId(pk);
	}

	public long getPortalPreferencesId() {
		return _portalPreferencesId;
	}

	public void setPortalPreferencesId(long portalPreferencesId) {
		_portalPreferencesId = portalPreferencesId;
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

	public String getPreferences() {
		return _preferences;
	}

	public void setPreferences(String preferences) {
		_preferences = preferences;
	}

	private long _portalPreferencesId;
	private long _ownerId;
	private int _ownerType;
	private String _preferences;
}