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

import com.liferay.portal.kernel.process.ProcessUtil;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shuyang Zhou
 */
public class HeapUtil {

	public static void heapDump(boolean live, boolean binary, String file) {
		int processId = _getProcessId();

		StringBundler sb = new StringBundler(5);

		sb.append("-dump:");

		if (live) {
			sb.append("live,");
		}

		if (binary) {
			sb.append("format=b,");
		}

		sb.append("file=");
		sb.append(file);

		List<String> arguments = new ArrayList<String>();

		arguments.add("jmap");
		arguments.add(sb.toString());
		arguments.add(String.valueOf(processId));

		try {
			ProcessUtil.execute(
				ProcessUtil.LOGGING_OUTPUT_PROCESSOR, arguments);
		}
		catch (Exception e) {
			throw new RuntimeException("Unable to perform heap dump", e);
		}
	}

	private static int _getProcessId() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

		String name = runtimeMXBean.getName();

		int index = name.indexOf(CharPool.AT);

		if (index == -1) {
			throw new RuntimeException("Unable to parse process name " + name);
		}

		int pid = GetterUtil.getInteger(name.substring(0, index));

		if (pid == 0) {
			throw new RuntimeException("Unable to parse process name " + name);
		}

		return pid;
	}

}