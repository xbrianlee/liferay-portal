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

package com.liferay.portlet.documentlibrary.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLSyncEventSoap implements Serializable {
	public static DLSyncEventSoap toSoapModel(DLSyncEvent model) {
		DLSyncEventSoap soapModel = new DLSyncEventSoap();

		soapModel.setSyncEventId(model.getSyncEventId());
		soapModel.setModifiedTime(model.getModifiedTime());
		soapModel.setEvent(model.getEvent());
		soapModel.setType(model.getType());
		soapModel.setTypePK(model.getTypePK());

		return soapModel;
	}

	public static DLSyncEventSoap[] toSoapModels(DLSyncEvent[] models) {
		DLSyncEventSoap[] soapModels = new DLSyncEventSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DLSyncEventSoap[][] toSoapModels(DLSyncEvent[][] models) {
		DLSyncEventSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DLSyncEventSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DLSyncEventSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DLSyncEventSoap[] toSoapModels(List<DLSyncEvent> models) {
		List<DLSyncEventSoap> soapModels = new ArrayList<DLSyncEventSoap>(models.size());

		for (DLSyncEvent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DLSyncEventSoap[soapModels.size()]);
	}

	public DLSyncEventSoap() {
	}

	public long getPrimaryKey() {
		return _syncEventId;
	}

	public void setPrimaryKey(long pk) {
		setSyncEventId(pk);
	}

	public long getSyncEventId() {
		return _syncEventId;
	}

	public void setSyncEventId(long syncEventId) {
		_syncEventId = syncEventId;
	}

	public long getModifiedTime() {
		return _modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		_modifiedTime = modifiedTime;
	}

	public String getEvent() {
		return _event;
	}

	public void setEvent(String event) {
		_event = event;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public long getTypePK() {
		return _typePK;
	}

	public void setTypePK(long typePK) {
		_typePK = typePK;
	}

	private long _syncEventId;
	private long _modifiedTime;
	private String _event;
	private String _type;
	private long _typePK;
}