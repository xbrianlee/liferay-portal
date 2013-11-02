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

package com.liferay.util.mail;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;

/**
 * @author Brian Wing Shun Chan
 */
public class JavaMailUtil {

	public static byte[] getBytes(Part part)
		throws IOException, MessagingException {

		InputStream is = part.getInputStream();

		return FileUtil.getBytes(is);
	}

	public static String toUnicodeString(Address[] addresses) {
		return toUnicodeString((InternetAddress[])addresses);
	}

	public static String toUnicodeString(InternetAddress[] addresses) {
		if (ArrayUtil.isEmpty(addresses)) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(addresses.length * 2 - 1);

		for (int i = 0; i < addresses.length; i++) {
			if (addresses[i] != null) {
				sb.append(addresses[i].toUnicodeString());
			}

			if ((i + 1) != addresses.length) {
				sb.append(", ");
			}
		}

		return sb.toString();
	}

}