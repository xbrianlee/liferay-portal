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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class ListMergeable<T>
	implements Mergeable<ListMergeable<T>>, Serializable {

	public void add(T t) {
		_list.add(t);
	}

	public boolean contains(T t) {
		return _list.contains(t);
	}

	@Override
	public ListMergeable<T> merge(ListMergeable<T> listMergeable) {
		if ((listMergeable == null) || (listMergeable == this)) {
			return this;
		}

		for (T t : listMergeable._list) {
			if (!_list.contains(t)) {
				_list.add(t);
			}
		}

		return this;
	}

	public String mergeToString(String delimiter) {
		return StringUtil.merge(_list, delimiter);
	}

	private static final long serialVersionUID = 1L;

	private List<T> _list = new ArrayList<T>();

}