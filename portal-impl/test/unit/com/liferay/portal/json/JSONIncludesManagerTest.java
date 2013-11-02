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

package com.liferay.portal.json;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONIncludesManagerUtil;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Igor Spasic
 */
public class JSONIncludesManagerTest {

	@Before
	public void setUp() throws Exception {

		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());

		JSONIncludesManagerUtil jsonIncludesManagerUtil =
			new JSONIncludesManagerUtil();

		jsonIncludesManagerUtil.setJSONIncludesManager(
			new JSONIncludesManagerImpl());
	}

	@Test
	public void testExtendsOne() {
		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(
			ExtendsOne.class);

		Assert.assertEquals(1, excludes.length);
		Assert.assertEquals("*", excludes[0]);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(
			ExtendsOne.class);

		Assert.assertEquals(1, includes.length);
		Assert.assertEquals("ftwo", includes[0]);
	}

	@Test
	public void testExtendsTwo() {
		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(
			ExtendsTwo.class);

		Assert.assertEquals(1, excludes.length);
		Assert.assertEquals("*", excludes[0]);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(
			ExtendsTwo.class);

		Assert.assertEquals(1, includes.length);
		Assert.assertEquals("ftwo", includes[0]);
	}

	@Test
	public void testFour() {
		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(Four.class);

		Assert.assertEquals(1, excludes.length);
		Assert.assertEquals("*", excludes[0]);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(Four.class);

		Arrays.sort(includes);

		Assert.assertEquals(2, includes.length);
		Assert.assertEquals("number", includes[0]);
		Assert.assertEquals("value", includes[1]);

		Four four = new Four();

		String json = JSONFactoryUtil.looseSerialize(four);

		Assert.assertTrue(json.contains("number"));
		Assert.assertTrue(json.contains("value"));
	}

	@Test
	public void testOne() {
		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(One.class);

		Assert.assertEquals(1, excludes.length);
		Assert.assertEquals("not", excludes[0]);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(One.class);

		Assert.assertEquals(1, includes.length);
		Assert.assertEquals("ftwo", includes[0]);
	}

	@Test
	public void testThree() {
		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(Three.class);

		Assert.assertEquals(1, excludes.length);
		Assert.assertEquals("ignore", excludes[0]);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(Three.class);

		Assert.assertEquals(0, includes.length);
	}

	@Test
	public void testTwo() {
		String[] excludes = JSONIncludesManagerUtil.lookupExcludes(Two.class);

		Assert.assertEquals(1, excludes.length);
		Assert.assertEquals("*", excludes[0]);

		String[] includes = JSONIncludesManagerUtil.lookupIncludes(Two.class);

		Assert.assertEquals(1, includes.length);
		Assert.assertEquals("ftwo", includes[0]);
	}

}