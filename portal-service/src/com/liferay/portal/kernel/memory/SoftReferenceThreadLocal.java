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

import com.liferay.portal.kernel.util.CentralizedThreadLocal;

import java.lang.ref.SoftReference;

/**
 * @author Shuyang Zhou
 */
public class SoftReferenceThreadLocal<T> extends CentralizedThreadLocal<T> {

	public SoftReferenceThreadLocal() {
		super(false);
	}

	@Override
	public T get() {
		SoftReference<T> softReference = _softReferenceThreadLocal.get();

		if (softReference == _nullSoftReference) {
			return null;
		}

		T value = null;

		if (softReference != null) {
			value = softReference.get();
		}

		if (value == null) {
			value = initialValue();

			set(value);
		}

		return value;
	}

	@Override
	public void remove() {
		_softReferenceThreadLocal.remove();
	}

	@Override
	public void set(T value) {
		if (value == null) {
			_softReferenceThreadLocal.set((SoftReference<T>)_nullSoftReference);
		}
		else {
			_softReferenceThreadLocal.set(new SoftReference<T>(value));
		}
	}

	private static SoftReference<Object> _nullSoftReference =
		new SoftReference<Object>(null);

	private ThreadLocal<SoftReference<T>> _softReferenceThreadLocal =
		new CentralizedThreadLocal<SoftReference<T>>(false);

}