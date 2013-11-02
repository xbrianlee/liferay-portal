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

package com.liferay.portal.kernel.io.unsync;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Writer;

/**
 * <p>
 * See http://issues.liferay.com/browse/LPS-6648.
 * </p>
 *
 * @author Shuyang Zhou
 */
public class UnsyncStringWriter extends Writer {

	public UnsyncStringWriter() {
		this(true);
	}

	public UnsyncStringWriter(boolean useStringBundler) {
		if (useStringBundler) {
			stringBundler = new StringBundler();
		}
		else {
			stringBuilder = new StringBuilder();
		}
	}

	public UnsyncStringWriter(boolean useStringBundler, int initialCapacity) {
		if (useStringBundler) {
			stringBundler = new StringBundler(initialCapacity);
		}
		else {
			stringBuilder = new StringBuilder(initialCapacity);
		}
	}

	public UnsyncStringWriter(int initialCapacity) {
		this(true, initialCapacity);
	}

	@Override
	public UnsyncStringWriter append(char c) {
		write(c);

		return this;
	}

	@Override
	public UnsyncStringWriter append(CharSequence charSequence) {
		if (charSequence == null) {
			write(StringPool.NULL);
		}
		else {
			write(charSequence.toString());
		}

		return this;
	}

	@Override
	public UnsyncStringWriter append(
		CharSequence charSequence, int start, int end) {

		if (charSequence == null) {
			charSequence = StringPool.NULL;
		}

		write(charSequence.subSequence(start, end).toString());

		return this;
	}

	@Override
	public void close() {
	}

	@Override
	public void flush() {
	}

	public StringBuilder getStringBuilder() {
		return stringBuilder;
	}

	public StringBundler getStringBundler() {
		return stringBundler;
	}

	public void reset() {
		if (stringBundler != null) {
			stringBundler.setIndex(0);
		}
		else {
			stringBuilder.setLength(0);
		}
	}

	@Override
	public String toString() {
		if (stringBundler != null) {
			return stringBundler.toString();
		}
		else {
			return stringBuilder.toString();
		}
	}

	@Override
	public void write(char[] chars) {
		write(chars, 0, chars.length);
	}

	@Override
	public void write(char[] chars, int offset, int length) {
		if (length <= 0) {
			return;
		}

		if (stringBundler != null) {
			stringBundler.append(new String(chars, offset, length));
		}
		else {
			stringBuilder.append(chars, offset, length);
		}
	}

	@Override
	public void write(int c) {
		if (stringBundler != null) {
			char ch = (char)c;

			if (ch <= 127) {
				stringBundler.append(StringPool.ASCII_TABLE[ch]);
			}
			else {
				stringBundler.append(String.valueOf(ch));
			}
		}
		else {
			stringBuilder.append((char)c);
		}
	}

	@Override
	public void write(String string) {
		if (stringBundler != null) {
			stringBundler.append(string);
		}
		else {
			stringBuilder.append(string);
		}
	}

	@Override
	public void write(String string, int offset, int length) {
		if ((string == null) ||
			((offset == 0) && (length == string.length()))) {

			write(string);
		}
		else if (stringBundler != null) {
			stringBundler.append(string.substring(offset, offset + length));
		}
		else {
			stringBuilder.append(string.substring(offset, offset + length));
		}
	}

	protected StringBuilder stringBuilder;
	protected StringBundler stringBundler;

}