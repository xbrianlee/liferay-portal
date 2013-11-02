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
import java.util.Collection;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class UniqueList<E> extends ArrayList<E> {

	public UniqueList() {
		super();
	}

	public UniqueList(Collection<E> c) {
		super(c.size());

		addAll(c);
	}

	public UniqueList(int initialCapacity) {
		super(initialCapacity);
	}

	@Override
	public boolean add(E e) {
		if (!contains(e)) {
			return super.add(e);
		}
		else {
			return false;
		}
	}

	@Override
	public void add(int index, E e) {
		if (!contains(e)) {
			super.add(index, e);
		}
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean modified = false;

		for (E e : c) {
			if (!contains(e)) {
				super.add(e);

				modified = true;
			}
		}

		return modified;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean modified = false;

		for (E e : c) {
			if (!contains(e)) {
				super.add(index++, e);

				modified = true;
			}
		}

		return modified;
	}

	@Override
	public E set(int index, E e) {
		Thread currentThread = Thread.currentThread();

		StackTraceElement[] stackTraceElements = currentThread.getStackTrace();

		if (stackTraceElements.length >= 4) {
			StackTraceElement stackTraceElement = stackTraceElements[3];

			String stackTraceElementString = stackTraceElement.toString();

			if (stackTraceElementString.contains(_STACK_TRACE_COLLECTIONS)) {
				return super.set(index, e);
			}
		}

		if (!contains(e)) {
			return super.set(index, e);
		}
		else {
			return e;
		}
	}

	private static final String _STACK_TRACE_COLLECTIONS =
		"java.util.Collections.sort(Collections.java";

}