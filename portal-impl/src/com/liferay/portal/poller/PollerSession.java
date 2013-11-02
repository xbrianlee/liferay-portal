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

package com.liferay.portal.poller;

import com.liferay.portal.kernel.poller.PollerRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Edward Han
 */
public class PollerSession {

	public PollerSession(String pollerSessionId) {
		_pollerSessionId = pollerSessionId;
	}

	public synchronized boolean beginPortletProcessing(
		PollerRequestResponsePair pollerRequestResponsePair,
		String responseId) {

		PollerRequest pollerRequest =
			pollerRequestResponsePair.getPollerRequest();

		String portletId = pollerRequest.getPortletId();

		// Do not process a new request if there is a request already pending.
		// This prevents flooding the server in the event of slow receive
		// requests.

		if (_pendingResponseIds.containsKey(portletId)) {
			return false;
		}

		_pendingResponseIds.put(portletId, responseId);

		_pollerRequestResponsePairs.put(portletId, pollerRequestResponsePair);

		return true;
	}

	public synchronized boolean completePortletProcessing(
		String portletId, String responseId) {

		String pendingResponseId = _pendingResponseIds.get(portletId);

		if (responseId.equals(pendingResponseId)) {
			_pendingResponseIds.remove(portletId);

			_pollerRequestResponsePairs.remove(portletId);
		}

		return _pendingResponseIds.isEmpty();
	}

	public String getPollerSessionId() {
		return _pollerSessionId;
	}

	private Map<String, String> _pendingResponseIds =
		new HashMap<String, String>();
	private Map<String, PollerRequestResponsePair> _pollerRequestResponsePairs =
		new HashMap<String, PollerRequestResponsePair>();
	private String _pollerSessionId;

}