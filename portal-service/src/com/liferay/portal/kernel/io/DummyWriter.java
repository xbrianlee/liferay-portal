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

package com.liferay.portal.kernel.io;

import java.io.Writer;

/**
 * @author Shuyang Zhou
 */
public class DummyWriter extends Writer {

	@Override
	public Writer append(char c) {
		return this;
	}

	@Override
	public Writer append(CharSequence charSequence) {
		return this;
	}

	@Override
	public Writer append(CharSequence charSequence, int start, int end) {
		return this;
	}

	@Override
	public void close() {
	}

	@Override
	public void flush() {
	}

	@Override
	public void write(char[] chars) {
	}

	@Override
	public void write(char[] chars, int offset, int length) {
	}

	@Override
	public void write(int c) {
	}

	@Override
	public void write(String string) {
	}

	@Override
	public void write(String string, int offset, int length) {
	}

}