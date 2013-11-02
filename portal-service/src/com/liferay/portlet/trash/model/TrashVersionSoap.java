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

package com.liferay.portlet.trash.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class TrashVersionSoap implements Serializable {
	public static TrashVersionSoap toSoapModel(TrashVersion model) {
		TrashVersionSoap soapModel = new TrashVersionSoap();

		soapModel.setVersionId(model.getVersionId());
		soapModel.setEntryId(model.getEntryId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setTypeSettings(model.getTypeSettings());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static TrashVersionSoap[] toSoapModels(TrashVersion[] models) {
		TrashVersionSoap[] soapModels = new TrashVersionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TrashVersionSoap[][] toSoapModels(TrashVersion[][] models) {
		TrashVersionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TrashVersionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new TrashVersionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TrashVersionSoap[] toSoapModels(List<TrashVersion> models) {
		List<TrashVersionSoap> soapModels = new ArrayList<TrashVersionSoap>(models.size());

		for (TrashVersion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TrashVersionSoap[soapModels.size()]);
	}

	public TrashVersionSoap() {
	}

	public long getPrimaryKey() {
		return _versionId;
	}

	public void setPrimaryKey(long pk) {
		setVersionId(pk);
	}

	public long getVersionId() {
		return _versionId;
	}

	public void setVersionId(long versionId) {
		_versionId = versionId;
	}

	public long getEntryId() {
		return _entryId;
	}

	public void setEntryId(long entryId) {
		_entryId = entryId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getTypeSettings() {
		return _typeSettings;
	}

	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _versionId;
	private long _entryId;
	private long _classNameId;
	private long _classPK;
	private String _typeSettings;
	private int _status;
}