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

package com.liferay.portal.scheduler.messaging;

import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scripting.ScriptingUtil;
import com.liferay.portal.kernel.util.StringPool;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tina Tian
 */
public class ScriptingMessageListener extends BaseMessageListener {

	@Override
	public void doReceive(Message message) throws Exception {
		Map<String, Object> inputObjects = new HashMap<String, Object>();

		String language = (String)message.get(SchedulerEngine.LANGUAGE);
		String script = (String)message.get(SchedulerEngine.SCRIPT);

		ScriptingUtil.exec(
			null, inputObjects, language, script, StringPool.EMPTY_ARRAY);
	}

}