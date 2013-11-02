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

package com.liferay.portal.kernel.poller;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.Message;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 */
public interface PollerResponse {

	public static final String POLLER_HINT_HIGH_CONNECTIVITY =
		"pollerHintHighConnectivity";

	public void close();

	public void createResponseMessage(Message message);

	public PollerHeader getPollerHeader();

	public String getPortletId();

	public boolean isEmpty();

	public void setParameter(String name, JSONArray value)
		throws PollerResponseClosedException;

	public void setParameter(String name, JSONObject value)
		throws PollerResponseClosedException;

	public void setParameter(String name, String value)
		throws PollerResponseClosedException;

	public JSONObject toJSONObject();

}