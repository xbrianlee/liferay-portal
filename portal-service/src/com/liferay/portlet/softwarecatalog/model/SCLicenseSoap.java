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

package com.liferay.portlet.softwarecatalog.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.portlet.softwarecatalog.service.http.SCLicenseServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portlet.softwarecatalog.service.http.SCLicenseServiceSoap
 * @generated
 */
public class SCLicenseSoap implements Serializable {
	public static SCLicenseSoap toSoapModel(SCLicense model) {
		SCLicenseSoap soapModel = new SCLicenseSoap();

		soapModel.setLicenseId(model.getLicenseId());
		soapModel.setName(model.getName());
		soapModel.setUrl(model.getUrl());
		soapModel.setOpenSource(model.getOpenSource());
		soapModel.setActive(model.getActive());
		soapModel.setRecommended(model.getRecommended());

		return soapModel;
	}

	public static SCLicenseSoap[] toSoapModels(SCLicense[] models) {
		SCLicenseSoap[] soapModels = new SCLicenseSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SCLicenseSoap[][] toSoapModels(SCLicense[][] models) {
		SCLicenseSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SCLicenseSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SCLicenseSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SCLicenseSoap[] toSoapModels(List<SCLicense> models) {
		List<SCLicenseSoap> soapModels = new ArrayList<SCLicenseSoap>(models.size());

		for (SCLicense model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SCLicenseSoap[soapModels.size()]);
	}

	public SCLicenseSoap() {
	}

	public long getPrimaryKey() {
		return _licenseId;
	}

	public void setPrimaryKey(long pk) {
		setLicenseId(pk);
	}

	public long getLicenseId() {
		return _licenseId;
	}

	public void setLicenseId(long licenseId) {
		_licenseId = licenseId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public boolean getOpenSource() {
		return _openSource;
	}

	public boolean isOpenSource() {
		return _openSource;
	}

	public void setOpenSource(boolean openSource) {
		_openSource = openSource;
	}

	public boolean getActive() {
		return _active;
	}

	public boolean isActive() {
		return _active;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public boolean getRecommended() {
		return _recommended;
	}

	public boolean isRecommended() {
		return _recommended;
	}

	public void setRecommended(boolean recommended) {
		_recommended = recommended;
	}

	private long _licenseId;
	private String _name;
	private String _url;
	private boolean _openSource;
	private boolean _active;
	private boolean _recommended;
}