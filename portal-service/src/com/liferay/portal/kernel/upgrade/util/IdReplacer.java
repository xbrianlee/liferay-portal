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

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.StagnantRowException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class IdReplacer {

	public static String replaceLongIds(
			String s, String begin, ValueMapper valueMapper)
		throws Exception {

		if ((s == null) || (begin == null) ||
			(valueMapper == null) || (valueMapper.size() == 0)) {

			return s;
		}

		char[] chars = s.toCharArray();

		StringBundler sb = new StringBundler();

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = _getEndPos(chars, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos));

				break;
			}
			else {
				sb.append(s.substring(pos, x + begin.length()));

				String oldString = s.substring(x + begin.length(), y);

				if (Validator.isNotNull(oldString)) {
					Long oldValue = new Long(GetterUtil.getLong(oldString));

					Long newValue = null;

					try {
						newValue = (Long)valueMapper.getNewValue(oldValue);
					}
					catch (StagnantRowException sre) {
						if (_log.isWarnEnabled()) {
							_log.warn(sre);
						}
					}

					if (newValue == null) {
						newValue = oldValue;
					}

					sb.append(newValue);
				}

				pos = y;
			}
		}

		return sb.toString();
	}

	public String replaceLongIds(
			String s, String begin, String end, ValueMapper valueMapper)
		throws Exception {

		if ((s == null) || (begin == null) || (end == null) ||
			(valueMapper == null) || (valueMapper.size() == 0)) {

			return s;
		}

		StringBundler sb = new StringBundler();

		int pos = 0;

		while (true) {
			int x = s.indexOf(begin, pos);
			int y = s.indexOf(end, x + begin.length());

			if ((x == -1) || (y == -1)) {
				sb.append(s.substring(pos));

				break;
			}
			else {
				sb.append(s.substring(pos, x + begin.length()));

				Long oldValue = new Long(
					GetterUtil.getLong(s.substring(x + begin.length(), y)));

				Long newValue = null;

				try {
					newValue = (Long)valueMapper.getNewValue(oldValue);
				}
				catch (StagnantRowException sre) {
					if (_log.isWarnEnabled()) {
						_log.warn(sre);
					}
				}

				if (newValue == null) {
					newValue = oldValue;
				}

				sb.append(newValue);

				pos = y;
			}
		}

		return sb.toString();
	}

	private static int _getEndPos(char[] chars, int pos) {
		while (true) {
			if (pos >= chars.length) {
				break;
			}

			if (!Character.isDigit(chars[pos])) {
				break;
			}

			pos++;
		}

		return pos;
	}

	private static Log _log = LogFactoryUtil.getLog(IdReplacer.class);

}