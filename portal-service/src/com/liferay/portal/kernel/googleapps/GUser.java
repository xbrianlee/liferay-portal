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
public class GUser {

	public String getFirstName() {
		return _firstName;
	}

	public String getFullName() {
		return _firstName.concat(StringPool.SPACE).concat(_lastName);
	}

	public String getLastName() {
		return _lastName;
	}

	public long getUserId() {
		return _userId;
	}

	public boolean isActive() {
		return _active;
	}

	public boolean isAdministrator() {
		return _administrator;
	}

	public boolean isAgreedToTermsOfUse() {
		return _agreedToTermsOfUse;
	}

	public void setActive(boolean active) {
		_active = active;
	}

	public void setAdministrator(boolean administrator) {
		_administrator = administrator;
	}

	public void setAgreedToTermsOfUse(boolean agreedToTermsOfUse) {
		_agreedToTermsOfUse = agreedToTermsOfUse;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	private boolean _active;
	private boolean _administrator;
	private boolean _agreedToTermsOfUse;
	private String _firstName;
	private String _lastName;
	private long _userId;

}