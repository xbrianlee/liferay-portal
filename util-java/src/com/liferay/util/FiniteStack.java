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

package com.liferay.util;

import java.util.Stack;

/**
 * @author Brian Wing Shun Chan
 */
public class FiniteStack<E> extends Stack<E> {

	public FiniteStack(int maxSize) {
		super();

		_maxSize = maxSize;
	}

	@Override
	public E push(E item) {
		super.push(item);

		int size = size();

		if (size > _maxSize) {
			removeElementAt(size - 1);
		}

		return item;
	}

	private int _maxSize;

}