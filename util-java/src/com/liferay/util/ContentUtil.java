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

package com.liferay.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class ContentUtil {

	public static String get(ClassLoader classLoader, String location) {
		return _instance._get(classLoader, location, false);
	}

	public static String get(
		ClassLoader classLoader, String location, boolean all) {

		return _instance._get(classLoader, location, all);
	}

	public static String get(String location) {
		return _instance._get(location, false);
	}

	public static String get(String location, boolean all) {
		return _instance._get(location, all);
	}

	private ContentUtil() {
		_contentPool = new HashMap<String, String>();
	}

	private String _get(ClassLoader classLoader, String location, boolean all) {
		String content = _contentPool.get(location);

		if (content == null) {
			try {
				content = StringUtil.read(classLoader, location, all);

				_put(location, content);
			}
			catch (IOException ioe) {
				_log.error(ioe, ioe);
			}
		}

		return content;
	}

	private String _get(String location, boolean all) {
		return _get(getClass().getClassLoader(), location, all);
	}

	private void _put(String location, String content) {
		_contentPool.put(location, content);
	}

	private static Log _log = LogFactoryUtil.getLog(ContentUtil.class);

	private static ContentUtil _instance = new ContentUtil();

	private Map<String, String> _contentPool;

}