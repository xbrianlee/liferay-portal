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

package com.liferay.portlet;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Tomas Polesovsky
 */
public class StrictPortletPreferencesImpl
	extends PortletPreferencesImpl
	implements Cloneable, Serializable {

	public StrictPortletPreferencesImpl() {
		super();
	}

	public StrictPortletPreferencesImpl(
		long companyId, long ownerId, int ownerType, long plid,
		String portletId, String xml, Map<String, Preference> preferences) {

		super(companyId, ownerId, ownerType, plid, portletId, xml, preferences);

		_companyId = companyId;
	}

	public StrictPortletPreferencesImpl(
		String xml, Map<String, Preference > preferences) {

		super(xml, preferences);
	}

	@Override
	public Object clone() {
		return new StrictPortletPreferencesImpl(
			_companyId, getOwnerId(), getOwnerType(), getPlid(), getPortletId(),
			getOriginalXML(), getOriginalPreferences());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof StrictPortletPreferencesImpl)) {
			return false;
		}

		return super.equals(obj);
	}

	private long _companyId;

}