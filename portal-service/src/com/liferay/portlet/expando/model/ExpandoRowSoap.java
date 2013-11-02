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

package com.liferay.portlet.expando.model;

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
public class ExpandoRowSoap implements Serializable {
	public static ExpandoRowSoap toSoapModel(ExpandoRow model) {
		ExpandoRowSoap soapModel = new ExpandoRowSoap();

		soapModel.setRowId(model.getRowId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTableId(model.getTableId());
		soapModel.setClassPK(model.getClassPK());

		return soapModel;
	}

	public static ExpandoRowSoap[] toSoapModels(ExpandoRow[] models) {
		ExpandoRowSoap[] soapModels = new ExpandoRowSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ExpandoRowSoap[][] toSoapModels(ExpandoRow[][] models) {
		ExpandoRowSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ExpandoRowSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ExpandoRowSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ExpandoRowSoap[] toSoapModels(List<ExpandoRow> models) {
		List<ExpandoRowSoap> soapModels = new ArrayList<ExpandoRowSoap>(models.size());

		for (ExpandoRow model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ExpandoRowSoap[soapModels.size()]);
	}

	public ExpandoRowSoap() {
	}

	public long getPrimaryKey() {
		return _rowId;
	}

	public void setPrimaryKey(long pk) {
		setRowId(pk);
	}

	public long getRowId() {
		return _rowId;
	}

	public void setRowId(long rowId) {
		_rowId = rowId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getTableId() {
		return _tableId;
	}

	public void setTableId(long tableId) {
		_tableId = tableId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	private long _rowId;
	private long _companyId;
	private Date _modifiedDate;
	private long _tableId;
	private long _classPK;
}