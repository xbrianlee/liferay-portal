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

import com.liferay.portal.kernel.events.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class EventsProcessorUtil {

	public static void process(String key, String[] classes)
		throws ActionException {

		_instance.process(key, classes, null, null, null, null);
	}

	public static void process(
			String key, String[] classes, HttpServletRequest request,
			HttpServletResponse response)
		throws ActionException {

		_instance.process(key, classes, null, request, response, null);
	}

	public static void process(
			String key, String[] classes, HttpSession session)
		throws ActionException {

		_instance.process(key, classes, null, null, null, session);
	}

	public static void process(String key, String[] classes, String[] ids)
		throws ActionException {

		_instance.process(key, classes, ids, null, null, null);
	}

	public static void registerEvent(String key, Object event) {
		_instance.registerEvent(key, event);
	}

	public static void setEventsProcessor(EventsProcessor eventsProcessor) {
		_instance = eventsProcessor;
	}

	public static void unregisterEvent(String key, Object event) {
		_instance.unregisterEvent(key, event);
	}

	private static EventsProcessor _instance = new EventsProcessorImpl();

}