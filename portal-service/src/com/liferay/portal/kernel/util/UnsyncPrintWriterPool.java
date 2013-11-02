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

import com.liferay.portal.kernel.io.OutputStreamWriter;
import com.liferay.portal.kernel.io.unsync.UnsyncPrintWriter;
import com.liferay.portal.kernel.memory.PoolAction;
import com.liferay.portal.kernel.memory.SoftReferencePool;

import java.io.OutputStream;
import java.io.Writer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class UnsyncPrintWriterPool {

	public static UnsyncPrintWriter borrow(OutputStream outputStream) {
		return borrow(new OutputStreamWriter(outputStream));
	}

	public static UnsyncPrintWriter borrow(
		OutputStream outputStream, String charsetName) {

		return borrow(new OutputStreamWriter(outputStream, charsetName));
	}

	public static UnsyncPrintWriter borrow(Writer writer) {
		if (!isEnabled()) {
			return new UnsyncPrintWriter(writer);
		}

		UnsyncPrintWriter unsyncPrintWriter =
			_unsyncPrintWriterSoftReferencePool.borrowObject(writer);

		List<UnsyncPrintWriter> unsyncPrintWriters =
			_borrowedUnsyncPrintWritersThreadLocal.get();

		unsyncPrintWriters.add(unsyncPrintWriter);

		return unsyncPrintWriter;
	}

	public static void cleanUp() {
		List<UnsyncPrintWriter> unsyncPrintWriters =
			_borrowedUnsyncPrintWritersThreadLocal.get();

		for (UnsyncPrintWriter unsyncPrintWriter : unsyncPrintWriters) {
			_unsyncPrintWriterSoftReferencePool.returnObject(unsyncPrintWriter);
		}

		unsyncPrintWriters.clear();
	}

	public static boolean isEnabled() {
		return _enabledThreadLocal.get();
	}

	public static void setEnabled(boolean enabled) {
		_enabledThreadLocal.set(enabled);
	}

	private static ThreadLocal<List<UnsyncPrintWriter>>
		_borrowedUnsyncPrintWritersThreadLocal =
			new AutoResetThreadLocal<List<UnsyncPrintWriter>>(
				UnsyncPrintWriterPool.class.getName() +
					"._borrowedUnsyncPrintWritersThreadLocal",
				new ArrayList<UnsyncPrintWriter>());
	private static ThreadLocal<Boolean> _enabledThreadLocal =
		new AutoResetThreadLocal<Boolean>(
			UnsyncPrintWriterPool.class.getName() + "._enabledThreadLocal",
			false);
	private static SoftReferencePool<UnsyncPrintWriter, Writer>
		_unsyncPrintWriterSoftReferencePool =
			new SoftReferencePool<UnsyncPrintWriter, Writer>(
				new UnsyncPrintWriterPoolAction(), 8192);

	private static class UnsyncPrintWriterPoolAction
		implements PoolAction<UnsyncPrintWriter, Writer> {

		@Override
		public UnsyncPrintWriter onBorrow(
			UnsyncPrintWriter unsyncPrintWriter, Writer writer) {

			unsyncPrintWriter.reset(writer);

			return unsyncPrintWriter;
		}

		@Override
		public UnsyncPrintWriter onCreate(Writer writer) {
			return new UnsyncPrintWriter(writer);
		}

		@Override
		public void onReturn(UnsyncPrintWriter unsyncPrintWriter) {
			unsyncPrintWriter.reset(null);
		}

	}

}