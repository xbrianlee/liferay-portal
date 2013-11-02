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

package com.liferay.portal.kernel.systemevent;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.model.SystemEventConstants;
import com.liferay.portal.util.PortalUtil;

import java.util.Stack;

/**
 * @author Zsolt Berentey
 */
public class SystemEventHierarchyEntryThreadLocal {

	public static void clear() {
		Stack<SystemEventHierarchyEntry> systemEventHierarchyEntries =
			_systemEventHierarchyEntries.get();

		systemEventHierarchyEntries.clear();
	}

	public static SystemEventHierarchyEntry peek() {
		Stack<SystemEventHierarchyEntry> systemEventHierarchyEntries =
			_systemEventHierarchyEntries.get();

		if (systemEventHierarchyEntries.isEmpty()) {
			return null;
		}

		return systemEventHierarchyEntries.peek();
	}

	public static SystemEventHierarchyEntry pop() {
		return pop(-1, -1);
	}

	public static SystemEventHierarchyEntry pop(Class<?> clazz) {
		return pop(PortalUtil.getClassNameId(clazz), 0);
	}

	public static SystemEventHierarchyEntry pop(Class<?> clazz, long classPK) {
		return pop(PortalUtil.getClassNameId(clazz), classPK);
	}

	public static SystemEventHierarchyEntry pop(
		long classNameId, long classPK) {

		Stack<SystemEventHierarchyEntry> systemEventHierarchyEntries =
			_systemEventHierarchyEntries.get();

		if (systemEventHierarchyEntries.isEmpty()) {
			return null;
		}

		SystemEventHierarchyEntry systemEventHierarchyEntry =
			systemEventHierarchyEntries.peek();

		if (((classNameId < 0) && (classPK < 0)) ||
			systemEventHierarchyEntry.hasTypedModel(classNameId, classPK)) {

			return systemEventHierarchyEntries.pop();
		}

		return null;
	}

	public static SystemEventHierarchyEntry pop(
		String className, long classPK) {

		return pop(PortalUtil.getClassNameId(className), classPK);
	}

	public static SystemEventHierarchyEntry push() throws SystemException {
		return push(SystemEventConstants.ACTION_SKIP);
	}

	public static SystemEventHierarchyEntry push(Class<?> clazz)
		throws SystemException {

		return push(
			PortalUtil.getClassNameId(clazz), 0,
			SystemEventConstants.ACTION_SKIP);
	}

	public static SystemEventHierarchyEntry push(Class<?> clazz, long classPK)
		throws SystemException {

		return push(
			PortalUtil.getClassNameId(clazz), classPK,
			SystemEventConstants.ACTION_SKIP);
	}

	public static SystemEventHierarchyEntry push(
			Class<?> clazz, long classPK, int action)
		throws SystemException {

		return push(PortalUtil.getClassNameId(clazz), classPK, action);
	}

	public static SystemEventHierarchyEntry push(int action)
		throws SystemException {

		return push(0, 0, action);
	}

	public static SystemEventHierarchyEntry push(
			long classNameId, long classPK, int action)
		throws SystemException {

		long parentSystemEventId = 0;
		long systemEventSetKey = 0;

		Stack<SystemEventHierarchyEntry> systemEventHierarchyEntries =
			_systemEventHierarchyEntries.get();

		SystemEventHierarchyEntry parentSystemEventHierarchyEntry = null;

		if (!systemEventHierarchyEntries.isEmpty()) {
			parentSystemEventHierarchyEntry =
				systemEventHierarchyEntries.peek();
		}

		if (parentSystemEventHierarchyEntry == null) {
			systemEventSetKey = CounterLocalServiceUtil.increment();
		}
		else if (parentSystemEventHierarchyEntry.getAction() ==
					SystemEventConstants.ACTION_SKIP) {

			return null;
		}
		else {
			parentSystemEventId =
				parentSystemEventHierarchyEntry.getSystemEventId();
			systemEventSetKey =
				parentSystemEventHierarchyEntry.getSystemEventSetKey();
		}

		SystemEventHierarchyEntry systemEventHierarchyEntry =
			new SystemEventHierarchyEntry(
				CounterLocalServiceUtil.increment(), classNameId, classPK,
				parentSystemEventId, systemEventSetKey, action);

		return systemEventHierarchyEntries.push(systemEventHierarchyEntry);
	}

	public static SystemEventHierarchyEntry push(
			String className, long classPK, int action)
		throws SystemException {

		return push(PortalUtil.getClassNameId(className), classPK, action);
	}

	private static ThreadLocal<Stack<SystemEventHierarchyEntry>>
		_systemEventHierarchyEntries =
			new AutoResetThreadLocal<Stack<SystemEventHierarchyEntry>>(
				SystemEventHierarchyEntryThreadLocal.class +
					"._systemEventHierarchyEntries",
				new Stack<SystemEventHierarchyEntry>());

}