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

package com.liferay.portal.kernel.nio.intraband;

/**
 * @author Shuyang Zhou
 */
public enum SystemDataType {

	MAILBOX((byte)3), MESSAGE((byte)2), PORTAL_CACHE((byte)1), RPC((byte)0);

	public byte getValue() {
		return _value;
	}

	private SystemDataType(byte value) {
		_value = value;
	}

	private byte _value;

}