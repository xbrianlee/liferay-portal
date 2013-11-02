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

package com.liferay.portal.security.auth;

import com.liferay.portal.model.UserConstants;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Pastor
 */
public class DefaultFullNameGeneratorTest {

	@Test
	public void testNormalLengthGetFullName() {
		String fullName = _defaultDefaultFullNameGenerator.getFullName(
			"Test", "Test", "Test");

		Assert.assertTrue(
			fullName.length() < UserConstants.FULL_NAME_MAX_LENGTH);
		Assert.assertEquals("Test Test Test", fullName);
	}

	@Test
	public void testVeryLongLengthGetFullName() {
		String fullName = _defaultDefaultFullNameGenerator.getFullName(
			"ThisShouldBeAVeryLongName", "ThisShouldBeAVeryLongMiddleName",
			"ThisShouldBeAVeryLongLastName");

		Assert.assertTrue(
			fullName.length() < UserConstants.FULL_NAME_MAX_LENGTH);
		Assert.assertEquals("T T ThisShouldBeAVeryLongLastName", fullName);
	}

	private DefaultFullNameGenerator _defaultDefaultFullNameGenerator =
		new DefaultFullNameGenerator();

}