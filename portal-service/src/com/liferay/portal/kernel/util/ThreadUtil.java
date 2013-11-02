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

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;

import java.io.InputStream;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.Date;
import java.util.Map;

/**
 * @author Tina Tian
 * @author Shuyang Zhou
 */
public class ThreadUtil {

	public static Thread[] getThreads() {
		Thread currentThread = Thread.currentThread();

		ThreadGroup threadGroup = currentThread.getThreadGroup();

		while (threadGroup.getParent() != null) {
			threadGroup = threadGroup.getParent();
		}

		int threadCountGuess = threadGroup.activeCount();

		Thread[] threads = new Thread[threadCountGuess];

		int threadCountActual = threadGroup.enumerate(threads);

		while (threadCountActual == threadCountGuess) {
			threadCountGuess *= 2;

			threads = new Thread[threadCountGuess];

			threadCountActual = threadGroup.enumerate(threads);
		}

		return threads;
	}

	public static String threadDump() {
		String threadDump = _getThreadDumpFromJstack();

		if (Validator.isNull(threadDump)) {
			threadDump = _getThreadDumpFromStackTrace();
		}

		return "\n\n".concat(threadDump);
	}

	private static String _getThreadDumpFromJstack() {
		UnsyncByteArrayOutputStream outputStream =
			new UnsyncByteArrayOutputStream();

		try {
			String vendorURL = System.getProperty("java.vendor.url");

			if (!vendorURL.equals("http://java.oracle.com/") &&
				!vendorURL.equals("http://java.sun.com/")) {

				return StringPool.BLANK;
			}

			RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

			String name = runtimeMXBean.getName();

			if (Validator.isNull(name)) {
				return StringPool.BLANK;
			}

			int pos = name.indexOf(CharPool.AT);

			if (pos == -1) {
				return StringPool.BLANK;
			}

			String pidString = name.substring(0, pos);

			if (!Validator.isNumber(pidString)) {
				return StringPool.BLANK;
			}

			Runtime runtime = Runtime.getRuntime();

			int pid = GetterUtil.getInteger(pidString);

			String[] cmd = new String[] {"jstack", String.valueOf(pid)};

			Process process = runtime.exec(cmd);

			InputStream inputStream = process.getInputStream();

			StreamUtil.transfer(inputStream, outputStream);
		}
		catch (Exception e) {
		}

		return outputStream.toString();
	}

	private static String _getThreadDumpFromStackTrace() {
		String jvm =
			System.getProperty("java.vm.name") + " " +
				System.getProperty("java.vm.version");

		StringBundler sb = new StringBundler(
			"Full thread dump of " + jvm + " on " + String.valueOf(new Date()) +
				"\n\n");

		Map<Thread, StackTraceElement[]> stackTraces =
			Thread.getAllStackTraces();

		for (Map.Entry<Thread, StackTraceElement[]> entry :
				stackTraces.entrySet()) {

			Thread thread = entry.getKey();
			StackTraceElement[] elements = entry.getValue();

			sb.append(StringPool.QUOTE);
			sb.append(thread.getName());
			sb.append(StringPool.QUOTE);

			if (thread.getThreadGroup() != null) {
				sb.append(StringPool.SPACE);
				sb.append(StringPool.OPEN_PARENTHESIS);
				sb.append(thread.getThreadGroup().getName());
				sb.append(StringPool.CLOSE_PARENTHESIS);
			}

			sb.append(", priority=");
			sb.append(thread.getPriority());
			sb.append(", id=");
			sb.append(thread.getId());
			sb.append(", state=");
			sb.append(thread.getState());
			sb.append("\n");

			for (int i = 0; i < elements.length; i++) {
				sb.append("\t");
				sb.append(elements[i]);
				sb.append("\n");
			}

			sb.append("\n");
		}

		return sb.toString();
	}

}