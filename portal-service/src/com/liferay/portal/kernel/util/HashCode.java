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

/**
 * @author Brian Wing Shun Chan
 */
public interface HashCode {

	public HashCode append(boolean value);

	public HashCode append(boolean[] value);

	public HashCode append(byte value);

	public HashCode append(byte[] value);

	public HashCode append(char value);

	public HashCode append(char[] value);

	public HashCode append(double value);

	public HashCode append(double[] value);

	public HashCode append(float value);

	public HashCode append(float[] value);

	public HashCode append(int value);

	public HashCode append(int[] value);

	public HashCode append(long value);

	public HashCode append(long[] value);

	public HashCode append(Object value);

	public HashCode append(Object[] value);

	public HashCode append(short value);

	public HashCode append(short[] value);

	public HashCode appendSuper(int superHashCode);

	public int toHashCode();

}