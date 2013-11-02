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

package com.liferay.portal.license;

import java.util.Date;

/**
 * @author Amos Fong
 */
public class LicenseInfo {

	public LicenseInfo(
		String owner, String description, String productEntryName,
		String productId, String productVersion, String licenseEntryType,
		String licenseVersion, Date startDate, Date expirationDate,
		long maxUsers, String[] hostNames, String[] ipAddresses,
		String[] macAddresses) {

		_description = description;
		_expirationDate = expirationDate;
		_hostNames = hostNames;
		_ipAddresses = ipAddresses;
		_licenseEntryType = licenseEntryType;
		_licenseVersion = licenseVersion;
		_macAddresses = macAddresses;
		_maxUsers = maxUsers;
		_owner = owner;
		_productEntryName = productEntryName;
		_productId = productId;
		_productVersion = productVersion;
		_startDate = startDate;
	}

	public String getDescription() {
		return _description;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public String[] getHostNames() {
		return _hostNames;
	}

	public String[] getIpAddresses() {
		return _ipAddresses;
	}

	public String getLicenseEntryType() {
		return _licenseEntryType;
	}

	public String getLicenseVersion() {
		return _licenseVersion;
	}

	public String[] getMacAddresses() {
		return _macAddresses;
	}

	public long getMaxUsers() {
		return _maxUsers;
	}

	public String getOwner() {
		return _owner;
	}

	public String getProductEntryName() {
		return _productEntryName;
	}

	public String getProductId() {
		return _productId;
	}

	public String getProductVersion() {
		return _productVersion;
	}

	public Date getStartDate() {
		return _startDate;
	}

	private String _description;
	private Date _expirationDate;
	private String[] _hostNames;
	private String[] _ipAddresses;
	private String _licenseEntryType;
	private String _licenseVersion;
	private String[] _macAddresses;
	private long _maxUsers;
	private String _owner;
	private String _productEntryName;
	private String _productId;
	private String _productVersion;
	private Date _startDate;

}