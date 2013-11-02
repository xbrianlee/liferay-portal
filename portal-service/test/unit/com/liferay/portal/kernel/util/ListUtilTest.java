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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Olaf Kock
 */
public class ListUtilTest {

	@Test
	public void testRemoveEmptyElement() {
		List<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		List<String> removeList = new ArrayList<String>();

		List<String> expectedList = new ArrayList<String>();

		expectedList.add("aaa");
		expectedList.add("bbb");
		expectedList.add("ccc");

		Assert.assertEquals(expectedList, ListUtil.remove(list, removeList));
	}

	@Test
	public void testRemoveFromEmptyList() {
		List<String> list = Collections.<String>emptyList();

		List<String> removeList = new ArrayList<String>();

		removeList.add("aaa");
		removeList.add("bbb");

		Assert.assertEquals(
			Collections.emptyList(),
			ListUtil.remove(list, removeList));
	}

	@Test
	public void testRemoveMultipleElements() {
		List<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		List<String> removeList = new ArrayList<String>();

		removeList.add("aaa");
		removeList.add("bbb");
		removeList.add("ccc");

		Assert.assertEquals(
			Collections.emptyList(), ListUtil.remove(list, removeList));
	}

	@Test
	public void testRemoveNullElement() {
		List<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		List<String> expectedList = new ArrayList<String>();

		expectedList.add("aaa");
		expectedList.add("bbb");
		expectedList.add("ccc");

		List<String> removeList = null;

		Assert.assertEquals(expectedList, ListUtil.remove(list, removeList));
	}

	@Test
	public void testRemoveSingleElement() {
		List<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		List<String> removeList = new ArrayList<String>();

		removeList.add("aaa");

		List<String> expectedList = new ArrayList<String>();

		expectedList.add("bbb");
		expectedList.add("ccc");

		Assert.assertEquals(expectedList, ListUtil.remove(list, removeList));
	}

	@Test
	public void testRemoveWrongElement() {
		List<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		List<String> removeList = new ArrayList<String>();

		removeList.add("ddd");

		List<String> expectedList = new ArrayList<String>();

		expectedList.add("aaa");
		expectedList.add("bbb");
		expectedList.add("ccc");

		Assert.assertEquals(expectedList, ListUtil.remove(list, removeList));
	}

	@Test
	public void testSubList() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);

		// Negative start, positive end, within range

		Assert.assertEquals(
			Arrays.asList(1, 2, 3), ListUtil.subList(list, -1, 3));

		// Negative start, positive end, out of range

		Assert.assertEquals(list, ListUtil.subList(list, -1, 5));

		// Negative start, negative end

		Assert.assertEquals(list, ListUtil.subList(list, -1, -1));

		// Proper start and end

		Assert.assertEquals(Arrays.asList(2, 3), ListUtil.subList(list, 1, 3));

		// Start is equal to end

		Assert.assertSame(
			Collections.emptyList(), ListUtil.subList(list, 1, 1));

		// Start is greater than end

		Assert.assertSame(
			Collections.emptyList(), ListUtil.subList(list, 2, 1));
	}

	@Test
	public void testToStringIntegerList() throws Exception {
		List<Integer> list = new ArrayList<Integer>();

		list.add(111);
		list.add(222);
		list.add(333);

		Assert.assertEquals(
			"111,222,333",
			ListUtil.toString(list, StringPool.NULL, StringPool.COMMA));
	}

	@Test
	public void testToStringLongList() throws Exception {
		List<Long> list = new ArrayList<Long>();

		list.add(111L);
		list.add(222L);
		list.add(333L);

		Assert.assertEquals(
			"111, 222, 333",
			ListUtil.toString(
				list, StringPool.BLANK, StringPool.COMMA_AND_SPACE));
	}

	@Test
	public void testToStringStringList() throws Exception {
		List<String> list = new ArrayList<String>();

		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		Assert.assertEquals(
			"aaa.bbb.ccc",
			ListUtil.toString(list, (String)null, StringPool.PERIOD));
	}

}