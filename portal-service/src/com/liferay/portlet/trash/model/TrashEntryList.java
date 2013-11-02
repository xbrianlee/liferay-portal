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

package com.liferay.portlet.trash.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class TrashEntryList implements Serializable {

	public TrashEntryList() {
	}

	public TrashEntrySoap[] getArray() {
		return _array;
	}

	public int getCount() {
		return _count;
	}

	public boolean isApproximate() {
		return _approximate;
	}

	public void setApproximate(boolean approximate) {
		_approximate = approximate;
	}

	public void setArray(TrashEntrySoap[] array) {
		_array = array;
	}

	public void setCount(int count) {
		_count = count;
	}

	private boolean _approximate;
	private TrashEntrySoap[] _array;
	private int _count;

}