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

package com.liferay.portal.security.auth;

import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Shuyang Zhou
 */
public class TransientTokenUtil {

	public static boolean checkToken(String token) {
		long currentTime = System.currentTimeMillis();

		_expungeExpiredToken(currentTime);

		Set<Map.Entry<Long, String>> tokens = _tokens.entrySet();

		Iterator<Map.Entry<Long, String>> itr = tokens.iterator();

		while (itr.hasNext()) {
			Map.Entry<Long, String> entry = itr.next();

			String curToken = entry.getValue();

			if (token.equals(curToken)) {
				itr.remove();

				return true;
			}
		}

		return false;
	}

	public static void clearAll() {
		_tokens.clear();
	}

	public static String createToken(long timeTolive) {
		long currentTime = System.currentTimeMillis();

		long expireTime = currentTime + timeTolive;

		_expungeExpiredToken(currentTime);

		String token = PortalUUIDUtil.generate();

		_tokens.put(expireTime, token);

		return token;
	}

	private static void _expungeExpiredToken(long currentTime) {
		SortedMap<Long, String> headMap = _tokens.headMap(currentTime);

		headMap.clear();
	}

	private static final SortedMap<Long, String> _tokens =
		Collections.synchronizedSortedMap(new TreeMap<Long, String>());

}