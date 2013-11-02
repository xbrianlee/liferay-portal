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

package com.liferay.portal.security.lang;

import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;

/**
 * @author Raymond Augé
 */
public class DoPrivilegedUtil {

	public static <T> T wrap(PrivilegedAction<T> privilegedAction) {
		return _pacl.wrap(privilegedAction);
	}

	public static <T> T wrap(
			PrivilegedExceptionAction<T> privilegedExceptionAction)
		throws Exception {

		return _pacl.wrap(privilegedExceptionAction);
	}

	public static <T> T wrap(T t) {
		return _pacl.wrap(t);
	}

	public static <T> T wrapWhenActive(T t) {
		return _pacl.wrapWhenActive(t);
	}

	public static interface PACL {

		public <T> T wrap(PrivilegedAction<T> privilegedAction);

		public <T> T wrap(
				PrivilegedExceptionAction<T> privilegedExceptionAction)
			throws Exception;

		public <T> T wrap(T t);

		public <T> T wrapWhenActive(T t);

	}

	private static PACL _pacl = new NoPACL();

	private static class NoPACL implements PACL {

		@Override
		public <T> T wrap(PrivilegedAction<T> privilegedAction) {
			return privilegedAction.run();
		}

		@Override
		public <T> T wrap(
				PrivilegedExceptionAction<T> privilegedExceptionAction)
			throws Exception {

			return privilegedExceptionAction.run();
		}

		@Override
		public <T> T wrap(T t) {
			return t;
		}

		@Override
		public <T> T wrapWhenActive(T t) {
			return t;
		}

	}

}