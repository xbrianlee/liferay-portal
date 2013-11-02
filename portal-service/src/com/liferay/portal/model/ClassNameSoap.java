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
 * This class is used by SOAP remote services, specifically {@link com.liferay.portal.service.http.ClassNameServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.http.ClassNameServiceSoap
 * @generated
 */
public class ClassNameSoap implements Serializable {
	public static ClassNameSoap toSoapModel(ClassName model) {
		ClassNameSoap soapModel = new ClassNameSoap();

		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setValue(model.getValue());

		return soapModel;
	}

	public static ClassNameSoap[] toSoapModels(ClassName[] models) {
		ClassNameSoap[] soapModels = new ClassNameSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ClassNameSoap[][] toSoapModels(ClassName[][] models) {
		ClassNameSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ClassNameSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ClassNameSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ClassNameSoap[] toSoapModels(List<ClassName> models) {
		List<ClassNameSoap> soapModels = new ArrayList<ClassNameSoap>(models.size());

		for (ClassName model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ClassNameSoap[soapModels.size()]);
	}

	public ClassNameSoap() {
	}

	public long getPrimaryKey() {
		return _classNameId;
	}

	public void setPrimaryKey(long pk) {
		setClassNameId(pk);
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	private long _classNameId;
	private String _value;
}