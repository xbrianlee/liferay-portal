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

package com.liferay.portal.increment;

import com.liferay.portal.kernel.concurrent.IncreasableEntry;
import com.liferay.portal.kernel.increment.Increment;

import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Zsolt Berentey
 */
public class BufferedIncreasableEntry<K, T>
	extends IncreasableEntry<K, Increment<T>> {

	public BufferedIncreasableEntry(
		MethodInvocation methodInvocation, K key, Increment<T> value) {

		super(key, value);

		_methodInvocation = methodInvocation;
	}

	@Override
	public Increment<T> doIncrease(
		Increment<T> originalValue, Increment<T> deltaValue) {

		return originalValue.increaseForNew(deltaValue.getValue());
	}

	public void proceed() throws Throwable {
		Object[] arguments = _methodInvocation.getArguments();

		arguments[arguments.length - 1] = getValue().getValue();

		_methodInvocation.proceed();
	}

	@Override
	public String toString() {
		return _methodInvocation.toString();
	}

	private MethodInvocation _methodInvocation;

}