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
public class UnicodeFormatter_IW {
	public static UnicodeFormatter_IW getInstance() {
		return _instance;
	}

	public java.lang.String bytesToHex(byte[] bytes) {
		return UnicodeFormatter.bytesToHex(bytes);
	}

	public java.lang.String byteToHex(byte b) {
		return UnicodeFormatter.byteToHex(b);
	}

	public char[] byteToHex(byte b, char[] hexes) {
		return UnicodeFormatter.byteToHex(b, hexes);
	}

	public char[] byteToHex(byte b, char[] hexes, boolean upperCase) {
		return UnicodeFormatter.byteToHex(b, hexes, upperCase);
	}

	public java.lang.String charToHex(char c) {
		return UnicodeFormatter.charToHex(c);
	}

	public byte[] hexToBytes(java.lang.String hexString) {
		return UnicodeFormatter.hexToBytes(hexString);
	}

	public java.lang.String parseString(java.lang.String hexString) {
		return UnicodeFormatter.parseString(hexString);
	}

	public java.lang.String toString(char[] array) {
		return UnicodeFormatter.toString(array);
	}

	public java.lang.String toString(java.lang.String s) {
		return UnicodeFormatter.toString(s);
	}

	private UnicodeFormatter_IW() {
	}

	private static UnicodeFormatter_IW _instance = new UnicodeFormatter_IW();
}