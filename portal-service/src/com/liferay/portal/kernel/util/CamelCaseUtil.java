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
 * @author Igor Spasic
 * @author Eduardo Lundgren
 */
public class CamelCaseUtil {

	public static String fromCamelCase(String s) {
		return fromCamelCase(s, CharPool.DASH);
	}

	public static String fromCamelCase(String s, char delimiter) {
		StringBuilder sb = new StringBuilder();

		boolean upperCase = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (!upperCase || !nextUpperCase) {
					sb.append(delimiter);
				}

				c = Character.toLowerCase(c);

				upperCase = true;
			}
			else {
				upperCase = false;
			}

			sb.append(c);
		}

		return sb.toString();
	}

	public static String normalizeCamelCase(String s) {
		StringBuilder sb = new StringBuilder();

		boolean upperCase = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			boolean nextUpperCase = true;

			if (i < (s.length() - 1)) {
				nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
			}

			if ((i > 0) && Character.isUpperCase(c)) {
				if (upperCase && nextUpperCase) {
					c = Character.toLowerCase(c);
				}

				upperCase = true;
			}
			else {
				upperCase = false;
			}

			sb.append(c);
		}

		return sb.toString();
	}

	public static String toCamelCase(String s) {
		return toCamelCase(s, CharPool.DASH);
	}

	public static String toCamelCase(String s, char delimiter) {
		StringBuilder sb = new StringBuilder(s.length());

		boolean upperCase = false;

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == delimiter) {
				upperCase = true;
			}
			else if (upperCase) {
				sb.append(Character.toUpperCase(c));

				upperCase = false;
			}
			else {
				sb.append(c);
			}
		}

		return sb.toString();
	}

}