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

package com.liferay.portal.kernel.portlet;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;

import javax.portlet.Event;

/**
 * @author Shuyang Zhou
 */
public class ActionResult implements Serializable {

	public static final ActionResult EMPTY_ACTION_RESULT = new ActionResult(
		Collections.<Event>emptyList(), null);

	public ActionResult(List<Event> events, String location) {
		_events = events;
		_location = location;
	}

	public List<Event> getEvents() {
		return _events;
	}

	public String getLocation() {
		return _location;
	}

	private List<Event> _events;
	private String _location;

}