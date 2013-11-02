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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Method;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class InitialThreadLocal<T> extends CentralizedThreadLocal<T> {

	public InitialThreadLocal(String name, T initialValue) {
		this(name, initialValue, false);
	}

	public InitialThreadLocal(String name, T initialValue, boolean shortLived) {
		super(shortLived);

		_name = name;
		_initialValue = initialValue;

		if (_initialValue instanceof Cloneable) {
			try {
				Class<?> clazz = _initialValue.getClass();

				_cloneMethod = clazz.getMethod(_METHOD_CLONE);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	@Override
	public String toString() {
		if (_name != null) {
			return _name;
		}
		else {
			return super.toString();
		}
	}

	@Override
	protected T initialValue() {
		if (_cloneMethod != null) {
			try {
				return (T)_cloneMethod.invoke(_initialValue);
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}

		return _initialValue;
	}

	private static final String _METHOD_CLONE = "clone";

	private static Log _log = LogFactoryUtil.getLog(InitialThreadLocal.class);

	private Method _cloneMethod;
	private T _initialValue;
	private String _name;

}