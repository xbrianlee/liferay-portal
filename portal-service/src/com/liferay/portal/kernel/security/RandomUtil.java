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

package com.liferay.portal.kernel.security;

import java.util.Random;

/**
 * @author Shuyang Zhou
 */
public class RandomUtil {

	public static int nextInt(int n) {
		return random.nextInt(n);
	}

	public static int[] nextInts(int n, int size) {
		if (size > n) {
			size = n;
		}

		int[] numbers = new int[n];

		for (int i = 0; i < n; i++) {
			numbers[i] = i;
		}

		shuffle(random, numbers);

		if (size == n) {
			return numbers;
		}

		int[] results = new int[size];

		System.arraycopy(numbers, 0, results, 0, size);

		return results;
	}

	public static void shuffle(Random random, int[] numbers) {
		for (int i = numbers.length; i > 1; i--) {
			int position = random.nextInt(i);

			if (position != (i - 1)) {
				int number = numbers[position];

				numbers[position] = numbers[i - 1];
				numbers[i - 1] = number;
			}
		}
	}

	public static String shuffle(Random random, String s) {
		StringBuilder sb = new StringBuilder(s);

		shuffle(random, sb);

		return sb.toString();
	}

	public static void shuffle(Random random, StringBuilder sb) {
		for (int i = sb.length(); i > 1; i--) {
			int position = random.nextInt(i);

			if (position != (i - 1)) {
				char c = sb.charAt(position);

				sb.setCharAt(position, sb.charAt(i - 1));
				sb.setCharAt(i - 1, c);
			}
		}
	}

	public static String shuffle(String s) {
		return shuffle(random, s);
	}

	protected static Random random = new Random();

}