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
import com.liferay.portal.kernel.poller.PollerResponse;

/**
 * @author Edward Han
 */
public class PollerRequestResponsePair {

	public PollerRequestResponsePair(PollerRequest pollerRequest) {
		_pollerRequest = pollerRequest;
	}

	public PollerRequestResponsePair(
		PollerRequest pollerRequest, PollerResponse pollerResponse) {

		_pollerRequest = pollerRequest;
		_pollerResponse = pollerResponse;
	}

	public PollerRequest getPollerRequest() {
		return _pollerRequest;
	}

	public PollerResponse getPollerResponse() {
		return _pollerResponse;
	}

	public void setPollerRequest(PollerRequest pollerRequest) {
		_pollerRequest = pollerRequest;
	}

	public void setPollerResponse(PollerResponse pollerResponse) {
		_pollerResponse = pollerResponse;
	}

	private PollerRequest _pollerRequest;
	private PollerResponse _pollerResponse;

}