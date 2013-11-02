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

package com.liferay.portal.kernel.format;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Brian Wing Shun Chan
 * @author Manuel de la Peña
 */
public class PhoneNumberFormatUtil {

	public static String format(String phoneNumber) {
		return getPhoneNumberFormat().format(phoneNumber);
	}

	public static PhoneNumberFormat getPhoneNumberFormat() {
		PortalRuntimePermission.checkGetBeanProperty(
			PhoneNumberFormatUtil.class);

		return _phoneNumberFormat;
	}

	public static String strip(String phoneNumber) {
		return getPhoneNumberFormat().strip(phoneNumber);
	}

	public static boolean validate(String phoneNumber) {
		return getPhoneNumberFormat().validate(phoneNumber);
	}

	public void setPhoneNumberFormat(PhoneNumberFormat phoneNumberFormat) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_phoneNumberFormat = phoneNumberFormat;
	}

	private static PhoneNumberFormat _phoneNumberFormat;

}