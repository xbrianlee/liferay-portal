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

package com.liferay.portal.kernel.memory;

import com.liferay.portal.kernel.util.Validator;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author Shuyang Zhou
 */
public class EqualityWeakReference<T> extends WeakReference<T> {

	public EqualityWeakReference(T referent) {
		super(referent);

		_hashCode = referent.hashCode();
	}

	public EqualityWeakReference(
		T referent, ReferenceQueue<? super T> referenceQueue) {

		super(referent, referenceQueue);

		_hashCode = referent.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof EqualityWeakReference<?>)) {
			return false;
		}

		EqualityWeakReference<?> equalityWeakReference =
			(EqualityWeakReference<?>)obj;

		if (Validator.equals(get(), equalityWeakReference.get())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return _hashCode;
	}

	private final int _hashCode;

}