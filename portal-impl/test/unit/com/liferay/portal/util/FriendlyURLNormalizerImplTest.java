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

package com.liferay.portal.util;

import com.liferay.portal.kernel.util.StringPool;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Julio Camarero
 */
public class FriendlyURLNormalizerImplTest {

	@Test
	public void testNormalizeBlank() {
		Assert.assertEquals(
			StringPool.BLANK,
			_friendlyURLNormalizerImpl.normalize(StringPool.BLANK));
	}

	@Test
	public void testNormalizeNull() {
		Assert.assertEquals(null, _friendlyURLNormalizerImpl.normalize(null));
	}

	@Test
	public void testNormalizeSentenceWithBlanks() {
		Assert.assertEquals(
			"sentence-with-blanks",
			_friendlyURLNormalizerImpl.normalize("sentence with blanks"));
	}

	@Test
	public void testNormalizeSentenceWithCapitalLetters() {
		Assert.assertEquals(
			"sentence-with-capital-letters",
			_friendlyURLNormalizerImpl.normalize(
				"Sentence WITH CaPital leTTerS"));
	}

	@Test
	public void testNormalizeSentenceWithDash() {
		Assert.assertEquals(
			"sentence-with-dash",
			_friendlyURLNormalizerImpl.normalize("sentence -with-dash"));
	}

	@Test
	public void testNormalizeSentenceWithDoubleBlanks() {
		Assert.assertEquals(
			"sentence-with-double-blanks",
			_friendlyURLNormalizerImpl.normalize(
				"sentence with   double  blanks"));
	}

	@Test
	public void testNormalizeSentenceWithSpecialCharacters() {
		Assert.assertEquals(
			"sentence-with-special-characters",
			_friendlyURLNormalizerImpl.normalize(
				"sentence&: =()with !@special# %+characters"));
	}

	@Test
	public void testNormalizeSimpleWord() {
		Assert.assertEquals(
			"word", _friendlyURLNormalizerImpl.normalize("word"));
	}

	@Test
	public void testNormalizeSpace() {
		Assert.assertEquals(
			StringPool.SPACE,
			_friendlyURLNormalizerImpl.normalize(StringPool.SPACE));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testNormalizeWordReplacingChars() {
		Assert.assertEquals(
			"s-nt-nc-ith-r-plac-chars",
			_friendlyURLNormalizerImpl.normalize(
				"sentence with replace chars", new char[] {'e', 'w'}));
	}

	@Test
	public void testNormalizeWordWithNonASCIICharacters() {
		Assert.assertEquals(
			"wordnc", _friendlyURLNormalizerImpl.normalize("word\u00F1\u00C7"));
	}

	private FriendlyURLNormalizerImpl _friendlyURLNormalizerImpl =
		new FriendlyURLNormalizerImpl();

}