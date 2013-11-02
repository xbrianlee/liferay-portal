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

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.2.0
 */
public class Randomizer extends Random {

	public static Randomizer getInstance() {
		return _instance;
	}

	public Randomizer() {
		super();
	}

	public Randomizer(long seed) {
		super(seed);
	}

	public int[] nextInt(int n, int size) {
		if (size > n) {
			size = n;
		}

		Set<Integer> set = new LinkedHashSet<Integer>();

		for (int i = 0; i < size; i++) {
			while (true) {
				Integer value = new Integer(nextInt(n));

				if (!set.contains(value)) {
					set.add(value);

					break;
				}
			}
		}

		int[] array = new int[set.size()];

		Iterator<Integer> itr = set.iterator();

		for (int i = 0; i < array.length; i++) {
			array[i] = itr.next().intValue();
		}

		return array;
	}

	public void randomize(char[] array) {
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			int x = nextInt(length);
			char y = array[i];

			array[i] = array[i + x];
			array[i + x] = y;

			length--;
		}
	}

	public void randomize(int[] array) {
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			int x = nextInt(length);
			int y = array[i];

			array[i] = array[i + x];
			array[i + x] = y;

			length--;
		}
	}

	public void randomize(List<Object> list) {
		int size = list.size();

		for (int i = 0; i <= size; i++) {
			Object obj = list.get(i);

			int j = nextInt(size);

			list.set(i, list.get(i + j));
			list.set(i + j, obj);

			size--;
		}
	}

	public void randomize(Object[] array) {
		int length = array.length;

		for (int i = 0; i < length - 1; i++) {
			int x = nextInt(length);
			Object y = array[i];

			array[i] = array[i + x];
			array[i + x] = y;

			length--;
		}
	}

	public String randomize(String s) {
		if (s == null) {
			return null;
		}

		char[] array = s.toCharArray();

		randomize(array);

		return new String(array);
	}

	private static Randomizer _instance = new Randomizer();

}