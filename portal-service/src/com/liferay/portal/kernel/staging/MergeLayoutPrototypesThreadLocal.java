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

package com.liferay.portal.kernel.staging;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.Validator;

import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Raymond Augé
 * @author Shuyang Zhou
 */
public class MergeLayoutPrototypesThreadLocal {

	public static void clearMergeComplete() {
		_mergeComplete.remove();
	}

	public static boolean isInProgress() {
		return _inProgress.get();
	}

	public static boolean isMergeComplete(Method method, Object[] arguments) {
		Set<MethodKey> methodKeys = _mergeComplete.get();

		return methodKeys.contains(new MethodKey(method, arguments));
	}

	public static void setInProgress(boolean inProgress) {
		_inProgress.set(inProgress);
	}

	public static void setMergeComplete(Method method, Object[] arguments) {
		Set<MethodKey> methodKeys = _mergeComplete.get();

		methodKeys.add(new MethodKey(method, arguments));

		setInProgress(false);
	}

	private static ThreadLocal<Boolean> _inProgress =
		new AutoResetThreadLocal<Boolean>(
			MergeLayoutPrototypesThreadLocal.class + "._inProgress", false);
	private static ThreadLocal<Set<MethodKey>> _mergeComplete =
		new AutoResetThreadLocal<Set<MethodKey>>(
			MergeLayoutPrototypesThreadLocal.class + "._mergeComplete",
			new HashSet<MethodKey>());

	private static class MethodKey {

		public MethodKey(Method method, Object[] arguments) {
			_method = method;
			_arguments = arguments;
		}

		@Override
		public int hashCode() {
			int hashCode = _method.hashCode();

			if (_arguments != null) {
				for (Object obj : _arguments) {
					if (obj == null) {
						hashCode = HashUtil.hash(hashCode, 0);
					}
					else {
						hashCode = HashUtil.hash(hashCode, obj.hashCode());
					}
				}
			}

			return hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			MethodKey methodKey = (MethodKey)obj;

			if (Validator.equals(_method, methodKey._method) &&
				Arrays.equals(_arguments, methodKey._arguments)) {

				return true;
			}

			return false;
		}

		private final Object[] _arguments;
		private final Method _method;

	}

}