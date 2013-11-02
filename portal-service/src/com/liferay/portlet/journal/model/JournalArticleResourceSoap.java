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

package com.liferay.portlet.journal.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class JournalArticleResourceSoap implements Serializable {
	public static JournalArticleResourceSoap toSoapModel(
		JournalArticleResource model) {
		JournalArticleResourceSoap soapModel = new JournalArticleResourceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setResourcePrimKey(model.getResourcePrimKey());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setArticleId(model.getArticleId());

		return soapModel;
	}

	public static JournalArticleResourceSoap[] toSoapModels(
		JournalArticleResource[] models) {
		JournalArticleResourceSoap[] soapModels = new JournalArticleResourceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static JournalArticleResourceSoap[][] toSoapModels(
		JournalArticleResource[][] models) {
		JournalArticleResourceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new JournalArticleResourceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new JournalArticleResourceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static JournalArticleResourceSoap[] toSoapModels(
		List<JournalArticleResource> models) {
		List<JournalArticleResourceSoap> soapModels = new ArrayList<JournalArticleResourceSoap>(models.size());

		for (JournalArticleResource model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new JournalArticleResourceSoap[soapModels.size()]);
	}

	public JournalArticleResourceSoap() {
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

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getArticleId() {
		return _articleId;
	}

	public void setArticleId(String articleId) {
		_articleId = articleId;
	}

	private String _uuid;
	private long _resourcePrimKey;
	private long _groupId;
	private String _articleId;
}