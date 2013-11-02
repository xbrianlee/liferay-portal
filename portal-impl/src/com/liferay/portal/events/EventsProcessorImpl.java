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

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.InstancePool;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class EventsProcessorImpl implements EventsProcessor {

	@Override
	public void process(
			String key, String[] classes, String[] ids,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session)
		throws ActionException {

		for (String className : classes) {
			if (Validator.isNull(className)) {
				return;
			}

			if (_log.isDebugEnabled()) {
				_log.debug("Process event " + className);
			}

			Object event = InstancePool.get(className);

			processEvent(event, ids, request, response, session);
		}

		if (Validator.isNull(key)) {
			return;
		}

		List<Object> events = _getEvents(key);

		for (Object event : events) {
			processEvent(event, ids, request, response, session);
		}
	}

	@Override
	public void processEvent(
			Object event, String[] ids, HttpServletRequest request,
			HttpServletResponse response, HttpSession session)
		throws ActionException {

		if (event instanceof Action) {
			Action action = (Action)event;

			try {
				action.run(request, response);
			}
			catch (ActionException ae) {
				throw ae;
			}
			catch (Exception e) {
				throw new ActionException(e);
			}
		}
		else if (event instanceof SessionAction) {
			SessionAction sessionAction = (SessionAction)event;

			try {
				sessionAction.run(session);
			}
			catch (ActionException ae) {
				throw ae;
			}
			catch (Exception e) {
				throw new ActionException(e);
			}
		}
		else if (event instanceof SimpleAction) {
			SimpleAction simpleAction = (SimpleAction)event;

			simpleAction.run(ids);
		}
	}

	@Override
	public void registerEvent(String key, Object event) {
		List<Object> events = _getEvents(key);

		events.add(event);
	}

	@Override
	public void unregisterEvent(String key, Object event) {
		List<Object> events = _getEvents(key);

		events.remove(event);
	}

	private List<Object> _getEvents(String key) {
		List<Object> events = _eventsMap.get(key);

		if (events == null) {
			events = new ArrayList<Object>();

			_eventsMap.put(key, events);
		}

		return events;
	}

	private static Log _log = LogFactoryUtil.getLog(EventsProcessorImpl.class);

	private Map<String, List<Object>> _eventsMap =
		new HashMap<String, List<Object>>();

}