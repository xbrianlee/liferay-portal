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

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DLContentSoap implements Serializable {
	public static DLContentSoap toSoapModel(DLContent model) {
		DLContentSoap soapModel = new DLContentSoap();

		soapModel.setContentId(model.getContentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setRepositoryId(model.getRepositoryId());
		soapModel.setPath(model.getPath());
		soapModel.setVersion(model.getVersion());
		soapModel.setData(model.getData());
		soapModel.setSize(model.getSize());

		return soapModel;
	}

	public static DLContentSoap[] toSoapModels(DLContent[] models) {
		DLContentSoap[] soapModels = new DLContentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DLContentSoap[][] toSoapModels(DLContent[][] models) {
		DLContentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new DLContentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new DLContentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DLContentSoap[] toSoapModels(List<DLContent> models) {
		List<DLContentSoap> soapModels = new ArrayList<DLContentSoap>(models.size());

		for (DLContent model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new DLContentSoap[soapModels.size()]);
	}

	public DLContentSoap() {
	}

	public long getPrimaryKey() {
		return _contentId;
	}

	public void setPrimaryKey(long pk) {
		setContentId(pk);
	}

	public long getContentId() {
		return _contentId;
	}

	public void setContentId(long contentId) {
		_contentId = contentId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getRepositoryId() {
		return _repositoryId;
	}

	public void setRepositoryId(long repositoryId) {
		_repositoryId = repositoryId;
	}

	public String getPath() {
		return _path;
	}

	public void setPath(String path) {
		_path = path;
	}

	public String getVersion() {
		return _version;
	}

	public void setVersion(String version) {
		_version = version;
	}

	public Blob getData() {
		return _data;
	}

	public void setData(Blob data) {
		_data = data;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	private long _contentId;
	private long _groupId;
	private long _companyId;
	private long _repositoryId;
	private String _path;
	private String _version;
	private Blob _data;
	private long _size;
}