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

package com.liferay.portal.cache.key;

import com.liferay.portal.kernel.cache.key.CacheKeyGenerator;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Test;

/**
 * @author Shuyang Zhou
 */
public abstract class BaseCacheKeyGeneratorTestCase extends TestCase {

	@Test
	public void testConsistency() {
		StringBundler sb = new StringBundler(_KEYS);

		Serializable hashCode1 = cacheKeyGenerator.getCacheKey(sb.toString());
		Serializable hashCode2 = cacheKeyGenerator.getCacheKey(_KEYS);

		assertEquals(hashCode1, hashCode2);

		Serializable hashCode3 = cacheKeyGenerator.getCacheKey(sb);

		assertEquals(hashCode2, hashCode3);
	}

	@Test
	public void testScan() {
		Map<Serializable, String> map = new HashMap<Serializable, String>();

		for (int i = 0; i < 1000000; i++) {
			String value = String.valueOf(i);

			Serializable key = cacheKeyGenerator.getCacheKey(value);

			String oldValue = map.put(key, value);

			if (oldValue != null) {
				fail(
					oldValue + " and " + value + " generate the same key " +
						key);
			}
		}
	}

	@Test
	public void testSpecialCases() {
		Map<Serializable, String> checkMap =
			new HashMap<Serializable, String>();

		for (String[] values : _SPECIAL_CASES) {
			String value = Arrays.toString(values);

			Serializable key = cacheKeyGenerator.getCacheKey(values);

			String oldValue = checkMap.put(key, Arrays.toString(values));

			if (oldValue != null) {
				fail(
					oldValue + " and " + value + " generate the same key " +
						key);
			}
		}
	}

	protected CacheKeyGenerator cacheKeyGenerator;

	private static final String[] _KEYS = {"test1", "test2", "test3", "test4"};

	private static final String[][] _SPECIAL_CASES = {
		{"fetchByT_C_C_P_.java.lang.Long.java.lang.Long.java.lang.Long_A_", ".",
			"10302", ".", "10303", ".", "13710"},
		{"fetchByT_C_C_P_.java.lang.Long.java.lang.Long.java.lang.Long_A_", ".",
			"10302", ".", "10305", ".", "13510"}
	};

}