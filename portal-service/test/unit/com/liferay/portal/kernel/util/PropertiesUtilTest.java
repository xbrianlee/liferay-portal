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

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;

import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class PropertiesUtilTest {

	@Test
	public void testLoad() throws Exception {
		Properties properties = PropertiesUtil.load(_PROPERTIES_STRING);

		for (String[] property : _PROPERTIES_ARRAY) {
			Assert.assertEquals(property[1], properties.get(property[0]));
		}
	}

	@Test
	public void testLoadJDK5() throws Exception {
		byte[] utf8Encoded = _PROPERTIES_STRING.getBytes(StringPool.UTF8);

		Properties properties = PropertiesUtil.loadJDK5(
			new UnsyncByteArrayInputStream(utf8Encoded), StringPool.UTF8);

		for (String[] property : _PROPERTIES_ARRAY) {
			Assert.assertEquals(property[1], properties.get(property[0]));
		}
	}

	@Test
	public void testLoadJDK6() throws Exception {
		if (JavaDetector.isJDK6()) {
			Properties properties = PropertiesUtil.loadJDK6(
				new UnsyncStringReader(_PROPERTIES_STRING));

			for (String[] property : _PROPERTIES_ARRAY) {
				Assert.assertEquals(property[1], properties.get(property[0]));
			}
		}
	}

	private static final String[][] _PROPERTIES_ARRAY = {
		{"testKey", "testValue"}, {"测试键", "测试值"}
	};

	private static final String _PROPERTIES_STRING;

	static {
		StringBundler sb = new StringBundler(_PROPERTIES_ARRAY.length * 4);

		for (String[] property : _PROPERTIES_ARRAY) {
			sb.append(property[0]);
			sb.append(StringPool.EQUAL);
			sb.append(property[1]);
			sb.append(StringPool.NEW_LINE);
		}

		_PROPERTIES_STRING = sb.toString();
	}

}