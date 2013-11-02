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

import java.net.URLEncoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class URLCodecTest {

	@Test
	public void testDecodeURL() throws Exception {
		for (int i = 0; i < _RAW_URLS.length; i++) {
			String result = URLCodec.decodeURL(
				_ENCODED_URLS[i], StringPool.UTF8, false);

			Assert.assertEquals(_RAW_URLS[i], result);

			result = URLCodec.decodeURL(
				_ESCAPE_SPACES_ENCODED_URLS[i], StringPool.UTF8, true);

			Assert.assertEquals(_RAW_URLS[i], result);
		}
	}

	@Test
	public void testEncodeURL() throws Exception {
		for (int i = 0; i < _RAW_URLS.length; i++) {
			String result = URLCodec.encodeURL(
				_RAW_URLS[i], StringPool.UTF8, false);

			Assert.assertTrue(
				StringUtil.equalsIgnoreCase(_ENCODED_URLS[i], result));

			result = URLCodec.encodeURL(_RAW_URLS[i], StringPool.UTF8, true);

			Assert.assertTrue(
				StringUtil.equalsIgnoreCase(
					_ESCAPE_SPACES_ENCODED_URLS[i], result));
		}
	}

	private static final String[] _ENCODED_URLS = new String[9];

	private static final String[] _ESCAPE_SPACES_ENCODED_URLS = new String[9];

	private static final String[] _RAW_URLS = {
		"abcdefghijklmnopqrstuvwxyz", "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
		"0123456789", ".-*_", " ", "~`!@#$%^&()+={[}]|\\:;\"'<,>?/", "中文测试",
		"/abc/def", "abc <def> ghi"
	};

	static {
		try {
			for (int i = 0; i < _RAW_URLS.length; i++) {
				_ENCODED_URLS[i] = URLEncoder.encode(
					_RAW_URLS[i], StringPool.UTF8);

				_ESCAPE_SPACES_ENCODED_URLS[i] = StringUtil.replace(
					_ENCODED_URLS[i], StringPool.PLUS, "%20");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}