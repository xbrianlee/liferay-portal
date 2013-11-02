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

package com.liferay.portlet.wiki.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class WikiPageResourceSoap implements Serializable {
	public static WikiPageResourceSoap toSoapModel(WikiPageResource model) {
		WikiPageResourceSoap soapModel = new WikiPageResourceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setResourcePrimKey(model.getResourcePrimKey());
		soapModel.setNodeId(model.getNodeId());
		soapModel.setTitle(model.getTitle());

		return soapModel;
	}

	public static WikiPageResourceSoap[] toSoapModels(WikiPageResource[] models) {
		WikiPageResourceSoap[] soapModels = new WikiPageResourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WikiPageResourceSoap[][] toSoapModels(
		WikiPageResource[][] models) {
		WikiPageResourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WikiPageResourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WikiPageResourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WikiPageResourceSoap[] toSoapModels(
		List<WikiPageResource> models) {
		List<WikiPageResourceSoap> soapModels = new ArrayList<WikiPageResourceSoap>(models.size());

		for (WikiPageResource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WikiPageResourceSoap[soapModels.size()]);
	}

	public WikiPageResourceSoap() {
	}

	public long getPrimaryKey() {
		return _resourcePrimKey;
	}

	public void setPrimaryKey(long pk) {
		setResourcePrimKey(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		_resourcePrimKey = resourcePrimKey;
	}

	public long getNodeId() {
		return _nodeId;
	}

	public void setNodeId(long nodeId) {
		_nodeId = nodeId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _uuid;
	private long _resourcePrimKey;
	private long _nodeId;
	private String _title;
}