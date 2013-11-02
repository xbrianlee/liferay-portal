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

package com.liferay.portal.kernel.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Michael C. Han
 */
public class PrimitiveLongListTest {

	@Test
	public void testAdd() {
		long[] expected = new long[] {10l, 11l, 12l};

		PrimitiveLongList primitiveLongList = new PrimitiveLongList();

		for (int i = 0; i < expected.length; i++) {
			primitiveLongList.add(expected[i]);
		}

		Assert.assertEquals(expected.length, primitiveLongList.size());

		long[] actual = primitiveLongList.getArray();

		Assert.assertEquals(expected.length, actual.length);

		for (int i = 0; i < actual.length; i++) {
			Assert.assertEquals(expected[i], actual[i]);
		}
	}

	@Test
	public void testAddAll() {
		long[] expected = new long[] {10l, 11l, 12l};

		PrimitiveLongList primitiveLongList = new PrimitiveLongList();

		primitiveLongList.addAll(expected);

		Assert.assertEquals(expected.length, primitiveLongList.size());

		long[] actual = primitiveLongList.getArray();

		Assert.assertEquals(expected.length, actual.length);

		for (int i = 0; i < actual.length; i++) {
			Assert.assertEquals(expected[i], actual[i]);
		}
	}

}