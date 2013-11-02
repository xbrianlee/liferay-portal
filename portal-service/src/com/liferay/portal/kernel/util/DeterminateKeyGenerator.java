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

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6872.
 * </p>
 *
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class DeterminateKeyGenerator {

	public static String generate(String input) {
		return generate(input, _DEFAULT_LENGTH);
	}

	public static String generate(String input, int length) {
		if (input == null) {
			throw new IllegalArgumentException("Input is null");
		}

		if (length <= 0) {
			throw new IllegalArgumentException(
				"Length is less than or equal to 0");
		}

		Map<String, Integer> seedMap = _seedMap.get();

		Integer previousSeed = seedMap.get(input);

		int seed = 0;

		if (previousSeed == null) {
			seed = input.hashCode();
		}
		else {
			seed = previousSeed;
		}

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = 0;

			if (seed > 0) {
				index = seed % 26;
			}
			else {
				index = -seed % 26;
			}

			sb.append(_CHARACTERS[index]);

			seed = _nextRandom(seed);
		}

		seedMap.put(input, seed);

		return sb.toString();
	}

	public static void reset() {
		Map<String, Integer> seedMap = _seedMap.get();

		seedMap.clear();
	}

	public static void reset(String key) {
		Map<String, Integer> seedMap = _seedMap.get();

		seedMap.remove(key);
	}

	private static int _nextRandom(int seed) {
		return (seed % 127773) * 16807 - (seed / 127773) * 2836;
	}

	private static final char[] _CHARACTERS =
		"abcdefghijklmnopqrstuvwxyz".toCharArray();

	private static final int _DEFAULT_LENGTH = 4;

	private static ThreadLocal<Map<String, Integer>> _seedMap =
		new AutoResetThreadLocal<Map<String, Integer>>(
			DeterminateKeyGenerator.class + "._seedMap",
			new HashMap<String, Integer>());

}