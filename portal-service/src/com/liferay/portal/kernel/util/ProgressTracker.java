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

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Jorge Ferrer
 * @author Sergio González
 */
public class ProgressTracker implements Serializable {

	public static final String PERCENT =
		ProgressTracker.class.getName() + "_PERCENT";

	public ProgressTracker(String progressId) {
		_progressId = progressId;
		addProgress(ProgressStatusConstants.PREPARED, 0, StringPool.BLANK);
	}

	public void addProgress(int status, int percent, String message) {
		Tuple tuple = new Tuple(percent, message);

		_progress.put(status, tuple);
	}

	public void finish(HttpServletRequest request) {
		finish(request.getSession());
	}

	public void finish(HttpSession session) {
		session.removeAttribute(PERCENT + _progressId);
	}

	public void finish(PortletRequest portletRequest) {
		finish(portletRequest.getPortletSession());
	}

	public void finish(PortletSession portletSession) {
		portletSession.removeAttribute(
			PERCENT + _progressId, PortletSession.APPLICATION_SCOPE);
	}

	public String getMessage() {
		Tuple tuple = _progress.get(_status);

		String message = GetterUtil.getString(tuple.getObject(1));

		return message;
	}

	public int getPercent() {
		return _percent;
	}

	public int getStatus() {
		return _status;
	}

	public void initialize(HttpServletRequest request) {
		initialize(request.getSession());
	}

	public void initialize(HttpSession session) {
		session.setAttribute(PERCENT + _progressId, this);
	}

	public void initialize(PortletRequest portletRequest) {
		initialize(portletRequest.getPortletSession());
	}

	public void initialize(PortletSession portletSession) {
		portletSession.setAttribute(
			PERCENT + _progressId, this, PortletSession.APPLICATION_SCOPE);
	}

	public void setPercent(int percent) {
		_percent = percent;
	}

	public void setStatus(int status) {
		_status = status;

		Tuple tuple = _progress.get(_status);

		_percent = GetterUtil.getInteger(tuple.getObject(0));
	}

	public void start(HttpServletRequest request) {
		start(request.getSession());
	}

	public void start(HttpSession session) {
		initialize(session);

		setPercent(1);
	}

	public void start(PortletRequest portletRequest) {
		start(portletRequest.getPortletSession());
	}

	public void start(PortletSession portletSession) {
		initialize(portletSession);

		setPercent(1);
	}

	private int _percent;
	private Map<Integer, Tuple> _progress = new HashMap<Integer, Tuple>();
	private String _progressId;
	private int _status = ProgressStatusConstants.PREPARED;

}