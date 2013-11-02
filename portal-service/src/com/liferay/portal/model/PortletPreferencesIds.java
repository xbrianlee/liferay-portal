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

/**
 * @author Brian Wing Shun Chan
 */
public class PortletPreferencesIds implements Serializable {

	public PortletPreferencesIds() {
	}

	public PortletPreferencesIds(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId) {

		_companyId = companyId;
		_ownerId = ownerId;
		_ownerType = ownerType;
		_plid = plid;
		_portletId = portletId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public long getOwnerId() {
		return _ownerId;
	}

	public int getOwnerType() {
		return _ownerType;
	}

	public long getPlid() {
		return _plid;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public void setOwnerId(long ownerId) {
		_ownerId = ownerId;
	}

	public void setOwnerType(int ownerType) {
		_ownerType = ownerType;
	}

	public void setPlid(long plid) {
		_plid = plid;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private long _companyId;
	private long _ownerId;
	private int _ownerType;
	private long _plid;
	private String _portletId;

}