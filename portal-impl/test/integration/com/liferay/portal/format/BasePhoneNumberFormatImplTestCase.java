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

package com.liferay.portal.format;

import com.liferay.portal.kernel.format.PhoneNumberFormat;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Brian Wing Shun Chan
 * @author Manuel de la Peña
 */
public abstract class BasePhoneNumberFormatImplTestCase {

	@Test
	public void testInvalidPhoneNumbers() {
		PhoneNumberFormat phoneNumberFormat = getPhoneNumberFormat();

		String[] phoneNumbers = getInvalidPhoneNumbers();

		for (String phoneNumber : phoneNumbers) {
			if (phoneNumberFormat.validate(phoneNumber)) {
				Assert.fail(phoneNumber);
			}
		}
	}

	@Test
	public void testValidPhoneNumbers() {
		PhoneNumberFormat phoneNumberFormat = getPhoneNumberFormat();

		String[] phoneNumbers = getValidPhoneNumbers();

		for (String phoneNumber : phoneNumbers) {
			if (!phoneNumberFormat.validate(phoneNumber)) {
				Assert.fail(phoneNumber);
			}
		}
	}

	protected abstract String[] getInvalidPhoneNumbers();

	protected abstract PhoneNumberFormat getPhoneNumberFormat();

	protected abstract String[] getValidPhoneNumbers();

}