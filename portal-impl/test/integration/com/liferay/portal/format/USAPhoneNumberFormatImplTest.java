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
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;

import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 * @author Manuel de la Peña
 */
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class USAPhoneNumberFormatImplTest
	extends BasePhoneNumberFormatImplTestCase {

	@Override
	public String[] getInvalidPhoneNumbers() {
		return new String[0];
	}

	@Override
	public PhoneNumberFormat getPhoneNumberFormat() {
		return new USAPhoneNumberFormatImpl();
	}

	@Override
	public String[] getValidPhoneNumbers() {
		return new String[] {
			"1234567890", "123-456-7890", "123.456.7890", "123 456 7890",
			"(123) 456 7890", "(012) 345-6789", "(123) 456-7890", "012-3456",
			"+1 (123) 456-7890", "1-123-456-7890", "1.123.456.7890"
		};
	}

}