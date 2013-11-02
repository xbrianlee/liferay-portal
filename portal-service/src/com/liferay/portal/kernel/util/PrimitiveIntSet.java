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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Michael C. Han
 */
public class PrimitiveIntSet {

	public PrimitiveIntSet() {
		_elements = new HashSet<Integer>();
	}

	public PrimitiveIntSet(int capacity) {
		_elements = new HashSet<Integer>(capacity);
	}

	public void add(int value) {
		_elements.add(value);
	}

	public void addAll(int[] values) {
		for (int value : values) {
			_elements.add(value);
		}
	}

	public int[] getArray() {
		int[] values = new int[_elements.size()];

		int counter = 0;

		for (Integer element : _elements) {
			values[counter] = element;

			counter++;
		}

		return values;
	}

	public int size() {
		return _elements.size();
	}

	private Set<Integer> _elements;

}