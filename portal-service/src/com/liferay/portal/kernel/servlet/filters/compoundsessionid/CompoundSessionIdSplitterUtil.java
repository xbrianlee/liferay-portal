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

package com.liferay.portal.kernel.servlet.filters.compoundsessionid;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-18587.
 * </p>
 *
 * @author Michael C. Han
 * @author Shuyang Zhou
 */
public class CompoundSessionIdSplitterUtil {

	public static String getSessionIdDelimiter() {
		return _sessionIdDelimiter;
	}

	public static boolean hasSessionDelimiter() {
		return _hasSessionDelimiter;
	}

	public static String parseSessionId(String sessionId) {
		if (!_hasSessionDelimiter) {
			return sessionId;
		}

		int pos = sessionId.indexOf(_sessionIdDelimiter);

		if (pos == -1) {
			return sessionId;
		}

		return sessionId.substring(0, pos);
	}

	private static final boolean _hasSessionDelimiter;
	private static final String _sessionIdDelimiter;

	static {
		String sessionIdDelimiter = PropsUtil.get(
			PropsKeys.SESSION_ID_DELIMITER);

		if (Validator.isNull(sessionIdDelimiter)) {
			sessionIdDelimiter = PropsUtil.get(
				"session.id." + ServerDetector.getServerId() + ".delimiter");
		}

		if (Validator.isNotNull(sessionIdDelimiter)) {
			_hasSessionDelimiter = true;
			_sessionIdDelimiter = sessionIdDelimiter;
		}
		else {
			_hasSessionDelimiter = false;
			_sessionIdDelimiter = StringPool.BLANK;
		}
	}

}