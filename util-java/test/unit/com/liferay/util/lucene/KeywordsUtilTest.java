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

package com.liferay.util.lucene;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Miguel Pastor
 */
public class KeywordsUtilTest {

	@Test
	public void testEscapeEspecial() {
		Assert.assertEquals(
			"\\{abc \\&& def\\}", KeywordsUtil.escape("{abc && def}"));
	}

	@Test
	public void testEscapeMultipleBracket() {
		Assert.assertEquals("abc\\{", KeywordsUtil.escape("abc{"));
	}

	@Test
	public void testEscapeNoEspecialCharacters() {
		Assert.assertEquals("abc", KeywordsUtil.escape("abc"));
	}

	@Test
	public void testToFuzzyFuzzyText() {
		Assert.assertEquals("abc~", KeywordsUtil.toFuzzy("abc~"));
	}

	@Test
	public void testToFuzzyNonFuzzyText() {
		Assert.assertEquals("abc~", KeywordsUtil.toFuzzy("abc"));
	}

	@Test
	public void testToFuzzyNullText() {
		Assert.assertNull(KeywordsUtil.toFuzzy(null));
	}

	@Test
	public void testToWildcardNullText() {
		Assert.assertNull(KeywordsUtil.toWildcard(null));
	}

	@Test
	public void testToWildcardSimpleText() {
		Assert.assertEquals("abc*", KeywordsUtil.toWildcard("abc"));
	}

	@Test
	public void testToWildcardWildcardText() {
		Assert.assertEquals("abc*", KeywordsUtil.toWildcard("abc*"));
	}

}