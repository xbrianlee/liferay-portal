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

package com.liferay.portal.kernel.plugin;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jorge Ferrer
 */
public class VersionTest {

	@Test
	public void testBugFixNumber() {
		assertPrevious("1.1.0", "1.1.1");
		assertLater("1.1.1", "1.1.0");
		assertLater("1.2.0", "1.1.1");
	}

	@Test
	public void testBuildNumber() {
		assertPrevious("1.1.1.0", "1.1.1.1");
		assertPrevious("1.1.1.9", "1.1.1.10");
		assertLater("1.1.1.20", "1.1.1.19");
	}

	@Test
	public void testMajorNumber() {
		assertPrevious("1.1", "1.1.1");
		assertLater("2", "1.1.1");
		assertLater("2", "1");
		assertLater("10", "9");
	}

	@Test
	public void testMinorNumber() {
		assertPrevious("1.1", "1.1.1");
		assertLater("1.2", "1.1.1");
		assertLater("1.2", "1.1");
		assertLater("1.10", "1.9");
	}

	@Test
	public void testPlus() {
		assertNotIncludes("1+", "0");
		assertIncludes("1+", "1");
		assertIncludes("1+", "2");
		assertNotIncludes("1.1+", "1.0");
		assertIncludes("1.1+", "1.1");
		assertIncludes("1.1+", "1.10");
		assertIncludes("1.1+", "1.2");
		assertNotIncludes("1.1+", "2");
		assertNotIncludes("1.1.2+", "1.1.1");
		assertIncludes("1.1.2+", "1.1.2");
		assertIncludes("1.1.2+", "1.1.13");
		assertNotIncludes("1.1.2+", "1.2");
		assertIncludes("1.1.1.1+", "1.1.1.13");
		assertIncludes("1.1.1.2+", "1.1.1.13");
		assertNotIncludes("1.1.1.2+", "1.1.2");
		assertIncludes("1.1.1.2+", "1.1.1.10");
		assertNotIncludes("1.1.1.10+", "1.1.1.9");
	}

	@Test
	public void testStar() {
		assertIncludes("1.1.*", "1.1.0");
		assertIncludes("1.*", "1.1");
		assertIncludes("*", "1");
		assertIncludes("*", "1.2");
		assertIncludes("*", "1.2.3");
		assertIncludes("*", "1.2.3.4");
	}

	protected void assertIncludes(String first, String second) {
		Version firstVersion = Version.getInstance(first);
		Version secondVersion = Version.getInstance(second);

		Assert.assertTrue(
			first + " does not include " + second,
			firstVersion.includes(secondVersion));
	}

	protected void assertLater(String first, String second) {
		Version firstVersion = Version.getInstance(first);

		Assert.assertTrue(
			first + " is not later than " + second,
			firstVersion.isLaterVersionThan(second.toString()));
	}

	protected void assertNotIncludes(String first, String second) {
		Version firstVersion = Version.getInstance(first);
		Version secondVersion = Version.getInstance(second);

		Assert.assertFalse(
			first + " includes " + second,
			firstVersion.includes(secondVersion));
	}

	protected void assertPrevious(String first, String second) {
		Version firstVersion = Version.getInstance(first);

		Assert.assertTrue(
			first + " is not previous than " + second,
			firstVersion.isPreviousVersionThan(second));
	}

}