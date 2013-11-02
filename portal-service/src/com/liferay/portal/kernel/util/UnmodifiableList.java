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

import java.io.Serializable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * This is a read-only wrapper around any <code>java.util.List</code>. Query
 * operations will "read through" to the specified list. Attempts to modify the
 * list directly or via its iterator will result in a
 * <code>java.lang.UnsupportedOperationException</code>.
 * </p>
 *
 * @author Alexander Chow
 */
public class UnmodifiableList<E> implements List<E>, Serializable {

	public UnmodifiableList(List<? extends E> list) {
		if (list == null) {
			throw new NullPointerException();
		}

		_list = list;
	}

	@Override
	public boolean add(E element) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public boolean contains(Object object) {
		return _list.contains(object);
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		return _list.containsAll(collection);
	}

	@Override
	public boolean equals(Object object) {
		return _list.equals(object);
	}

	@Override
	public E get(int index) {
		return _list.get(index);
	}

	@Override
	public int hashCode() {
		return _list.hashCode();
	}

	@Override
	public int indexOf(Object object) {
		return _list.indexOf(object);
	}

	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			Iterator<? extends E> itr = _list.iterator();

			@Override
			public boolean hasNext() {
				return itr.hasNext();
			}

			@Override
			public E next() {
				return itr.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(_MESSAGE);
			}

		};
	}

	@Override
	public int lastIndexOf(Object o) {
		return _list.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return listIterator(0);
	}

	@Override
	public ListIterator<E> listIterator(final int index) {
		return new ListIterator<E>() {

			ListIterator<? extends E> itr = _list.listIterator(index);

			@Override
			public void add(E element) {
				throw new UnsupportedOperationException(_MESSAGE);
			}

			@Override
			public boolean hasNext() {
				return itr.hasNext();
			}

			@Override
			public E next() {
				return itr.next();
			}

			@Override
			public boolean hasPrevious() {
				return itr.hasPrevious();
			}

			@Override
			public E previous() {
				return itr.previous();
			}

			@Override
			public int nextIndex() {
				return itr.nextIndex();
			}

			@Override
			public int previousIndex() {
				return itr.previousIndex();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException(_MESSAGE);
			}

			@Override
			public void set(E element) {
				throw new UnsupportedOperationException(_MESSAGE);
			}

		};
	}

	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public boolean remove(Object object) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException(_MESSAGE);
	}

	@Override
	public int size() {
		return _list.size();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return new UnmodifiableList<E>(_list.subList(fromIndex, toIndex));
	}

	@Override
	public Object[] toArray() {
		return _list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return _list.toArray(a);
	}

	@Override
	public String toString() {
		return _list.toString();
	}

	private static final String _MESSAGE =
		"Please make a copy of this read-only list before modifying it.";

	private List<? extends E> _list;

}