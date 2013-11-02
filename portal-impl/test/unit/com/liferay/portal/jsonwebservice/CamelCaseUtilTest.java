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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.util.CamelCaseUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Igor Spasic
 */
public class CamelCaseUtilTest {

	@Test
	public void testFromCamelCase() {
		Assert.assertEquals(
			"camel-case", CamelCaseUtil.fromCamelCase("camelCase"));
		Assert.assertEquals(
			"camel-case-word", CamelCaseUtil.fromCamelCase("camelCASEWord"));
		Assert.assertEquals(
			"camel-case", CamelCaseUtil.fromCamelCase("camelCASE"));
	}

	@Test
	public void testNormalization() {
		Assert.assertEquals(
			"camelCase", CamelCaseUtil.normalizeCamelCase("camelCase"));
		Assert.assertEquals(
			"camelCaseWord", CamelCaseUtil.normalizeCamelCase("camelCASEWord"));
		Assert.assertEquals(
			"camelCase", CamelCaseUtil.normalizeCamelCase("camelCASE"));
	}

	@Test
	public void testToCamelCase() {
		Assert.assertEquals(
			"camelCase", CamelCaseUtil.toCamelCase("camel-case"));
		Assert.assertEquals(
			"camelCASEWord", CamelCaseUtil.toCamelCase("camel-CASE-word"));
		Assert.assertEquals(
			"camelCASE", CamelCaseUtil.toCamelCase("camel-CASE"));
	}

}