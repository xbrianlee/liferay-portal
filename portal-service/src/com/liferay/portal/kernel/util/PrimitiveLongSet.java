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
public class PrimitiveLongSet {

	public PrimitiveLongSet() {
		_elements = new HashSet<Long>();
	}

	public PrimitiveLongSet(int capacity) {
		_elements = new HashSet<Long>(capacity);
	}

	public void add(long value) {
		_elements.add(value);
	}

	public void addAll(long[] values) {
		for (long value : values) {
			_elements.add(value);
		}
	}

	public long[] getArray() {
		long[] values = new long[_elements.size()];

		int counter = 0;

		for (Long element : _elements) {
			values[counter] = element;

			counter++;
		}

		return values;
	}

	public int size() {
		return _elements.size();
	}

	private Set<Long> _elements;

}