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

package com.liferay.portal.resiliency.spi.action;

import com.liferay.portal.kernel.portlet.ActionResult;
import com.liferay.portal.kernel.portlet.PortletContainer;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.util.WebKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Shuyang Zhou
 */
public class MockPortletContainer implements PortletContainer {

	public static final String MOCK_LAYOUT_TYPE_SETTINGS =
		"MOCK_LAYOUT_TYPE_SETTINGS";

	@Override
	public void preparePortlet(HttpServletRequest request, Portlet portlet) {
		prepared = true;
	}

	@Override
	public ActionResult processAction(
		HttpServletRequest request, HttpServletResponse response,
		Portlet portlet) {

		this.request = request;
		this.response = response;
		this.portlet = portlet;

		if (modifyLayoutTypeSettings) {
			Layout requestLayout = (Layout)request.getAttribute(WebKeys.LAYOUT);

			requestLayout.setTypeSettings(MOCK_LAYOUT_TYPE_SETTINGS);
		}

		actionResult = new ActionResult(null, null);

		return actionResult;
	}

	@Override
	public List<Event> processEvent(
		HttpServletRequest request, HttpServletResponse response,
		Portlet portlet, Layout layout, Event event) {

		this.request = request;
		this.response = response;
		this.portlet = portlet;
		this.layout = layout;
		this.event = event;

		if (modifyLayoutTypeSettings) {
			Layout requestLayout = (Layout)request.getAttribute(WebKeys.LAYOUT);

			requestLayout.setTypeSettings(MOCK_LAYOUT_TYPE_SETTINGS);
		}

		events = new ArrayList<Event>();

		return events;
	}

	@Override
	public void render(
		HttpServletRequest request, HttpServletResponse response,
		Portlet portlet) {

		this.request = request;
		this.response = response;
		this.portlet = portlet;
	}

	@Override
	public void serveResource(
		HttpServletRequest request, HttpServletResponse response,
		Portlet portlet) {

		this.request = request;
		this.response = response;
		this.portlet = portlet;
	}

	public ActionResult actionResult;
	public Event event;
	public List<Event> events;
	public Layout layout;
	public boolean modifyLayoutTypeSettings;
	public Portlet portlet;
	public boolean prepared;
	public HttpServletRequest request;
	public HttpServletResponse response;

}