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

package com.liferay.portal.kernel.concurrent;

import com.liferay.portal.kernel.util.MapBackedSet;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class ConcurrentHashSet<E> extends MapBackedSet<E> {

	public ConcurrentHashSet() {
		super(new ConcurrentHashMap<E, Boolean>());
	}

	public ConcurrentHashSet(int capacity) {
		super(new ConcurrentHashMap<E, Boolean>(capacity));
	}

	public ConcurrentHashSet(Set<E> set) {
		super(new ConcurrentHashMap<E, Boolean>());

		addAll(set);
	}

}