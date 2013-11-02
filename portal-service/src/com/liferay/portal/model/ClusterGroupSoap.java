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
public class ClusterGroupSoap implements Serializable {
	public static ClusterGroupSoap toSoapModel(ClusterGroup model) {
		ClusterGroupSoap soapModel = new ClusterGroupSoap();

		soapModel.setClusterGroupId(model.getClusterGroupId());
		soapModel.setName(model.getName());
		soapModel.setClusterNodeIds(model.getClusterNodeIds());
		soapModel.setWholeCluster(model.getWholeCluster());

		return soapModel;
	}

	public static ClusterGroupSoap[] toSoapModels(ClusterGroup[] models) {
		ClusterGroupSoap[] soapModels = new ClusterGroupSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static ClusterGroupSoap[][] toSoapModels(ClusterGroup[][] models) {
		ClusterGroupSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new ClusterGroupSoap[models.length][models[0].length];
		}
		else {
			soapModels = new ClusterGroupSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static ClusterGroupSoap[] toSoapModels(List<ClusterGroup> models) {
		List<ClusterGroupSoap> soapModels = new ArrayList<ClusterGroupSoap>(models.size());

		for (ClusterGroup model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new ClusterGroupSoap[soapModels.size()]);
	}

	public ClusterGroupSoap() {
	}

	public long getPrimaryKey() {
		return _clusterGroupId;
	}

	public void setPrimaryKey(long pk) {
		setClusterGroupId(pk);
	}

	public long getClusterGroupId() {
		return _clusterGroupId;
	}

	public void setClusterGroupId(long clusterGroupId) {
		_clusterGroupId = clusterGroupId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getClusterNodeIds() {
		return _clusterNodeIds;
	}

	public void setClusterNodeIds(String clusterNodeIds) {
		_clusterNodeIds = clusterNodeIds;
	}

	public boolean getWholeCluster() {
		return _wholeCluster;
	}

	public boolean isWholeCluster() {
		return _wholeCluster;
	}

	public void setWholeCluster(boolean wholeCluster) {
		_wholeCluster = wholeCluster;
	}

	private long _clusterGroupId;
	private String _name;
	private String _clusterNodeIds;
	private boolean _wholeCluster;
}