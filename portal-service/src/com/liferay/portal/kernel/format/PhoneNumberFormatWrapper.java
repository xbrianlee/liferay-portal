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

/**
 * @author Brian Wing Shun Chan
 * @author Manuel de la Peña
 */
public class PhoneNumberFormatWrapper implements PhoneNumberFormat {

	public PhoneNumberFormatWrapper(PhoneNumberFormat phoneNumberFormat) {
		_originalPhoneNumberFormat = phoneNumberFormat;
		_phoneNumberFormat = phoneNumberFormat;
	}

	@Override
	public String format(String phoneNumber) {
		return _phoneNumberFormat.format(phoneNumber);
	}

	public void setPhoneNumberFormat(PhoneNumberFormat phoneNumberFormat) {
		if (phoneNumberFormat == null) {
			_phoneNumberFormat = _originalPhoneNumberFormat;
		}
		else {
			_phoneNumberFormat = phoneNumberFormat;
		}
	}

	@Override
	public String strip(String phoneNumber) {
		return _phoneNumberFormat.strip(phoneNumber);
	}

	@Override
	public boolean validate(String phoneNumber) {
		return _phoneNumberFormat.validate(phoneNumber);
	}

	private PhoneNumberFormat _originalPhoneNumberFormat;
	private PhoneNumberFormat _phoneNumberFormat;

}