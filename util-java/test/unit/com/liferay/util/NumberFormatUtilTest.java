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

package com.liferay.util;

import com.liferay.portal.kernel.util.LocaleUtil;

import java.text.NumberFormat;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Pastor
 */
public class NumberFormatUtilTest {

	@Test
	public void testNoDecimal() {
		Assert.assertEquals(
			"1", NumberFormatUtil.format(_numberFormat, 1, 1.1));
	}

	@Test
	public void testOneDecimal() {
		Assert.assertEquals(
			"1.1", NumberFormatUtil.format(_numberFormat, 1.1, 1.1));
	}

	private NumberFormat _numberFormat = NumberFormat.getNumberInstance(
		LocaleUtil.ENGLISH);

}