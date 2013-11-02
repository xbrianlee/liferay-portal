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

package com.liferay.portlet.social.util;

import com.liferay.portal.kernel.util.AutoResetThreadLocal;
import com.liferay.portal.util.PortalUtil;

import java.util.Stack;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityHierarchyEntryThreadLocal {

	public static void clear() {
		Stack<SocialActivityHierarchyEntry> activityHierarchyEntries =
			_activityHierarchyEntries.get();

		activityHierarchyEntries.clear();
	}

	public static SocialActivityHierarchyEntry peek() {
		Stack<SocialActivityHierarchyEntry> activityHierarchyEntries =
			_activityHierarchyEntries.get();

		if (activityHierarchyEntries.isEmpty()) {
			return null;
		}

		return activityHierarchyEntries.peek();
	}

	public static SocialActivityHierarchyEntry pop() {
		Stack<SocialActivityHierarchyEntry> activityHierarchyEntries =
			_activityHierarchyEntries.get();

		if (activityHierarchyEntries.isEmpty()) {
			return null;
		}

		return activityHierarchyEntries.pop();
	}

	public static void push(Class<?> clazz, long classPK) {
		long classNameId = PortalUtil.getClassNameId(clazz);

		push(classNameId, classPK);
	}

	public static void push(long classNameId, long classPK) {
		Stack<SocialActivityHierarchyEntry> activityHierarchyEntries =
			_activityHierarchyEntries.get();

		activityHierarchyEntries.push(
			new SocialActivityHierarchyEntry(classNameId, classPK));
	}

	public static void push(String className, long classPK) {
		long classNameId = PortalUtil.getClassNameId(className);

		push(classNameId, classPK);
	}

	private static ThreadLocal<Stack<SocialActivityHierarchyEntry>>
		_activityHierarchyEntries =
			new AutoResetThreadLocal<Stack<SocialActivityHierarchyEntry>>(
				SocialActivityHierarchyEntryThreadLocal.class +
					"._activityHierarchyEntries",
				new Stack<SocialActivityHierarchyEntry>());

}