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

package com.liferay.portal.kernel.poller.comet;

/**
 * @author Edward Han
 * @author Brian Wing Shun Chan
 */
public interface CometSession {

	public void close() throws CometException;

	public Object getAttribute(String name);

	public CometRequest getCometRequest();

	public CometResponse getCometResponse();

	public String getSessionId();

	public void setAttribute(String name, Object object);

	public void setCometRequest(CometRequest cometRequest);

	public void setCometResponse(CometResponse cometResponse);

	public void setSessionId(String sessionId);

}