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

package com.liferay.counter.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class CounterSoap implements Serializable {
	public static CounterSoap toSoapModel(Counter model) {
		CounterSoap soapModel = new CounterSoap();

		soapModel.setName(model.getName());
		soapModel.setCurrentId(model.getCurrentId());

		return soapModel;
	}

	public static CounterSoap[] toSoapModels(Counter[] models) {
		CounterSoap[] soapModels = new CounterSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CounterSoap[][] toSoapModels(Counter[][] models) {
		CounterSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CounterSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CounterSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CounterSoap[] toSoapModels(List<Counter> models) {
		List<CounterSoap> soapModels = new ArrayList<CounterSoap>(models.size());

		for (Counter model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CounterSoap[soapModels.size()]);
	}

	public CounterSoap() {
	}

	public String getPrimaryKey() {
		return _name;
	}

	public void setPrimaryKey(String pk) {
		setName(pk);
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getCurrentId() {
		return _currentId;
	}

	public void setCurrentId(long currentId) {
		_currentId = currentId;
	}

	private String _name;
	private long _currentId;
}