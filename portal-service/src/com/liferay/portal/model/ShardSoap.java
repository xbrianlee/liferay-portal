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
public class ShardSoap implements Serializable {
	public static ShardSoap toSoapModel(Shard model) {
		ShardSoap soapModel = new ShardSoap();

		soapModel.setShardId(model.getShardId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setName(model.getName());

		return soapModel;
	}

	public static ShardSoap[] toSoapModels(Shard[] models) {
		ShardSoap[] soapModels = new ShardSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ShardSoap[][] toSoapModels(Shard[][] models) {
		ShardSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ShardSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ShardSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ShardSoap[] toSoapModels(List<Shard> models) {
		List<ShardSoap> soapModels = new ArrayList<ShardSoap>(models.size());

		for (Shard model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ShardSoap[soapModels.size()]);
	}

	public ShardSoap() {
	}

	public long getPrimaryKey() {
		return _shardId;
	}

	public void setPrimaryKey(long pk) {
		setShardId(pk);
	}

	public long getShardId() {
		return _shardId;
	}

	public void setShardId(long shardId) {
		_shardId = shardId;
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

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	private long _shardId;
	private long _classNameId;
	private long _classPK;
	private String _name;
}