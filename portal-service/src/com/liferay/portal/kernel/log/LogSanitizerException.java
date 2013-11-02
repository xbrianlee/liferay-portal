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

package com.liferay.portal.kernel.log;

/**
 * @author Tomas Polesovsky
 */
public class LogSanitizerException extends Exception {

	public LogSanitizerException() {
	}

	public LogSanitizerException(String message) {
		super(message);
	}

	public LogSanitizerException(
		String message, StackTraceElement[] stackTraceElements,
		Throwable throwable) {

		super(message, throwable);

		setStackTrace(stackTraceElements);
	}

	public LogSanitizerException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public LogSanitizerException(Throwable throwable) {
		super(throwable);
	}

	@Override
	public String toString() {
		Class<?> clazz = getClass();

		String className = clazz.getName();

		String localizedMessage = getLocalizedMessage();

		if (localizedMessage != null) {
			return localizedMessage;
		}

		return className;
	}

}