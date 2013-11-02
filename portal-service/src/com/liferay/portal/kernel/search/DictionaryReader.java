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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.Iterator;

/**
 * @author Michael C. Han
 */
public class DictionaryReader {

	public DictionaryReader(InputStream inputStream)
		throws UnsupportedEncodingException {

		this(inputStream, StringPool.UTF8);
	}

	public DictionaryReader(InputStream inputStream, String encoding)
		throws UnsupportedEncodingException {

		_encoding = encoding;

		_bufferedReader = new BufferedReader(
			new InputStreamReader(inputStream, encoding));
	}

	public Iterator<DictionaryEntry> getDictionaryEntriesIterator() {
		return new DictionaryIterator();
	}

	private static final int _UNICODE_BYTE_ORDER_MARK = 65279;

	private BufferedReader _bufferedReader;
	private final String _encoding;

	private class DictionaryIterator implements Iterator<DictionaryEntry> {

		@Override
		public DictionaryEntry next() {
			if (!_calledHasNext) {
				hasNext();
			}

			_calledHasNext = false;

			if (StringPool.UTF8.equals(_encoding) &&
				(_line.charAt(0) == _UNICODE_BYTE_ORDER_MARK)) {

				_line = _line.substring(1);
			}

			return new DictionaryEntry(_line);
		}

		@Override
		public boolean hasNext() {
			if (!_calledHasNext) {
				try {
					_line = _bufferedReader.readLine();

					_calledHasNext = true;
				}
				catch (IOException ioe) {
					throw new IllegalStateException(ioe);
				}
			}

			if (Validator.isNotNull(_line)) {
				return true;
			}

			return false;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		private boolean _calledHasNext;
		private String _line;

	}

}