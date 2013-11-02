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

package com.liferay.portlet.asset.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class AssetTagStatsSoap implements Serializable {
	public static AssetTagStatsSoap toSoapModel(AssetTagStats model) {
		AssetTagStatsSoap soapModel = new AssetTagStatsSoap();

		soapModel.setTagStatsId(model.getTagStatsId());
		soapModel.setTagId(model.getTagId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setAssetCount(model.getAssetCount());

		return soapModel;
	}

	public static AssetTagStatsSoap[] toSoapModels(AssetTagStats[] models) {
		AssetTagStatsSoap[] soapModels = new AssetTagStatsSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetTagStatsSoap[][] toSoapModels(AssetTagStats[][] models) {
		AssetTagStatsSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetTagStatsSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetTagStatsSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetTagStatsSoap[] toSoapModels(List<AssetTagStats> models) {
		List<AssetTagStatsSoap> soapModels = new ArrayList<AssetTagStatsSoap>(models.size());

		for (AssetTagStats model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetTagStatsSoap[soapModels.size()]);
	}

	public AssetTagStatsSoap() {
	}

	public long getPrimaryKey() {
		return _tagStatsId;
	}

	public void setPrimaryKey(long pk) {
		setTagStatsId(pk);
	}

	public long getTagStatsId() {
		return _tagStatsId;
	}

	public void setTagStatsId(long tagStatsId) {
		_tagStatsId = tagStatsId;
	}

	public long getTagId() {
		return _tagId;
	}

	public void setTagId(long tagId) {
		_tagId = tagId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public int getAssetCount() {
		return _assetCount;
	}

	public void setAssetCount(int assetCount) {
		_assetCount = assetCount;
	}

	private long _tagStatsId;
	private long _tagId;
	private long _classNameId;
	private int _assetCount;
}