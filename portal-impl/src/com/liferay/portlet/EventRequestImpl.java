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

package com.liferay.portlet;

import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class EventRequestImpl
	extends PortletRequestImpl implements EventRequest {

	@Override
	public Event getEvent() {
		return _event;
	}

	@Override
	public String getLifecycle() {
		return PortletRequest.EVENT_PHASE;
	}

	public void setEvent(Event event) {
		_event = event;
	}

	private Event _event;

}