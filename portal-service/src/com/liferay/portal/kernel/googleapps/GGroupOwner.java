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

package com.liferay.portal.kernel.googleapps;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 */
public class GGroupOwner {

	public String getEmailAddress() {
		return _emailAddress;
	}

	public GGroup getGGroup() {
		return _gGroup;
	}

	public GUser getGUser() {
		return _gUser;
	}

	public String getType() {
		return _type;
	}

	public boolean isEveryone() {
		if (_emailAddress.equals(StringPool.STAR)) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setEmailAddress(String emailAddress) {
		_emailAddress = emailAddress;
	}

	public void setGGroup(GGroup gGroup) {
		_gGroup = gGroup;
	}

	public void setGUser(GUser gUser) {
		_gUser = gUser;
	}

	public void setType(String type) {
		_type = type;
	}

	private String _emailAddress = StringPool.BLANK;
	private GGroup _gGroup;
	private GUser _gUser;
	private String _type;

}