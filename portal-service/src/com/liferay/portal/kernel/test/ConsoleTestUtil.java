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

package com.liferay.portal.kernel.test;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.util.StringPool;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * @author Shuyang Zhou
 */
public class ConsoleTestUtil {

	public static UnsyncByteArrayOutputStream hijackStdErr() {
		System.err.flush();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		PrintStream printStream = new PrintStream(unsyncByteArrayOutputStream);

		System.setErr(printStream);

		return unsyncByteArrayOutputStream;
	}

	public static UnsyncByteArrayOutputStream hijackStdOut() {
		System.out.flush();

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		PrintStream printStream = new PrintStream(unsyncByteArrayOutputStream);

		System.setOut(printStream);

		return unsyncByteArrayOutputStream;
	}

	public static String restoreStdErr(
			UnsyncByteArrayOutputStream unsyncByteArrayOutputStream)
		throws UnsupportedEncodingException {

		System.out.flush();

		FileOutputStream fileOutputStream = new FileOutputStream(
			FileDescriptor.err);

		PrintStream printStream = new PrintStream(fileOutputStream);

		System.setErr(printStream);

		return unsyncByteArrayOutputStream.toString(
			StringPool.DEFAULT_CHARSET_NAME);
	}

	public static String restoreStdOut(
			UnsyncByteArrayOutputStream unsyncByteArrayOutputStream)
		throws UnsupportedEncodingException {

		System.out.flush();

		FileOutputStream fileOutputStream = new FileOutputStream(
			FileDescriptor.out);

		PrintStream printStream = new PrintStream(fileOutputStream);

		System.setOut(printStream);

		return unsyncByteArrayOutputStream.toString(
			StringPool.DEFAULT_CHARSET_NAME);
	}

}