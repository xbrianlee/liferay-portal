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

package com.liferay.portal.events;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ShutdownHook implements Runnable {

	@Override
	public void run() {
		if (GetterUtil.getBoolean(
				System.getProperty("shutdown.hook.print.full.thread.dump"))) {

			printFullThreadDump();
		}
	}

	protected void printFullThreadDump() {
		StringBundler sb = new StringBundler();

		sb.append("Full thread dump ");
		sb.append(System.getProperty("java.vm.name"));
		sb.append(" ");
		sb.append(System.getProperty("java.vm.version"));
		sb.append("\n\n");

		Map<Thread, StackTraceElement[]> stackTraces =
			Thread.getAllStackTraces();

		for (Map.Entry<Thread, StackTraceElement[]> entry :
				stackTraces.entrySet()) {

			Thread thread = entry.getKey();
			StackTraceElement[] elements = entry.getValue();

			sb.append("\"");
			sb.append(thread.getName());
			sb.append("\"");

			if (thread.getThreadGroup() != null) {
				sb.append(" (");
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

		System.out.println(sb);
	}

}