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

package com.liferay.portal.kernel.increment;

/**
 * @author Zsolt Berentey
 */
public interface Increment<T> {

	public void decrease(T delta);

	public Increment<T> decreaseForNew(T delta);

	public T getValue();

	public void increase(T delta);

	public Increment<T> increaseForNew(T delta);

	public void setValue(T value);

}