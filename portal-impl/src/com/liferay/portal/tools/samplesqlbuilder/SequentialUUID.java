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

package com.liferay.portal.tools.samplesqlbuilder;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.concurrent.atomic.AtomicLong;

/**
 * A simplified UUID generator for sample SQL generation that generates UUID in
 * a sequential order. This should not be used for any other purposes.
 *
 * @author Shuyang Zhou
 */
public class SequentialUUID {

	public static String generate() {
		long count = _counter.getAndIncrement();

		long high = (count >> 48) & 0xffff;
		long low = count & 0xffffffffffffL;

		StringBundler sb = new StringBundler(4);

		sb.append(_UUID_PREFIX);
		sb.append(_toHexString(high, 4));
		sb.append(StringPool.MINUS);
		sb.append(_toHexString(low, 8));

		return sb.toString();
	}

	public static SequentialUUID getSequentialUUID() {
		return _instance;
	}

	private static String _toHexString(long number, int digits) {
		char[] buffer = new char[digits];

		for (int i = 0; i < digits; i++) {
			buffer[i] = '0';
		}

		int index = digits;

		do {
			buffer[--index] = _HEX_DIGITS[(int) (number & 15)];

			number >>>= 4;
		}
		while (number != 0);

		return new String(buffer);
	}

	private static final char[] _HEX_DIGITS = {
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
		'e', 'f'
	};

	private static final String _UUID_PREFIX = "00000000-0000-0000-";

	private static SequentialUUID _instance = new SequentialUUID();

	private static AtomicLong _counter = new AtomicLong();

}