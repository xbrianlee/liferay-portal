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

/**
 * @author Brian Wing Shun Chan
 */
public class FiniteUniqueStack<E> extends FiniteStack<E> {

	public FiniteUniqueStack(int maxSize) {
		super(maxSize);
	}

	@Override
	public E push(E item) {
		if (!contains(item)) {
			super.push(item);
		}
		else {
			if (!item.equals(peek())) {
				remove(item);
				super.push(item);
			}
		}

		return item;
	}

}