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

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Shuyang Zhou
 */
public class IPDetector {

	public static boolean isPrefersV4() {
		if (_prefersV4 != null) {
			return _prefersV4.booleanValue();
		}

		_prefersV4 = Boolean.valueOf(
			System.getProperty("java.net.preferIPv4Stack"));

		return _prefersV4.booleanValue();
	}

	public static boolean isPrefersV6() {
		if (_prefersV6 != null) {
			return _prefersV6.booleanValue();
		}

		_prefersV6 = Boolean.valueOf(
			System.getProperty("java.net.preferIPv6Stack"));

		return _prefersV6.booleanValue();
	}

	public static boolean isSupportsV6() {
		if (_suppportsV6 != null) {
			return _suppportsV6.booleanValue();
		}

		try {
			InetAddress[] inetAddresses = InetAddress.getAllByName("localhost");

			for (InetAddress inetAddress : inetAddresses) {
				if (inetAddress.getHostAddress().contains(":")) {
					_suppportsV6 = Boolean.TRUE;

					break;
				}
			}
		}
		catch (UnknownHostException uhe) {
			_log.error(uhe, uhe);
		}

		if (_suppportsV6 == null) {
			_suppportsV6 = Boolean.FALSE;
		}

		return _suppportsV6.booleanValue();
	}

	private static Log _log = LogFactoryUtil.getLog(IPDetector.class);

	private static Boolean _prefersV4;
	private static Boolean _prefersV6;
	private static Boolean _suppportsV6;

}