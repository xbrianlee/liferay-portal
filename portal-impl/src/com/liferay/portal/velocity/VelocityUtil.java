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

package com.liferay.portal.velocity;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsUtil;

import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

/**
 * @author Brian Wing Shun Chan
 */
public class VelocityUtil {

	public static String evaluate(String input) throws Exception {
		return evaluate(input, null);
	}

	public static String evaluate(String input, Map<String, Object> variables)
		throws Exception {

		VelocityEngine velocityEngine = new VelocityEngine();

		velocityEngine.setProperty(
			RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
			PropsUtil.get(PropsKeys.VELOCITY_ENGINE_LOGGER));

		velocityEngine.setProperty(
			RuntimeConstants.RUNTIME_LOG_LOGSYSTEM + ".log4j.category",
			PropsUtil.get(PropsKeys.VELOCITY_ENGINE_LOGGER_CATEGORY));

		velocityEngine.init();

		VelocityContext velocityContext = new VelocityContext();

		if (variables != null) {
			for (Map.Entry<String, Object> entry : variables.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();

				if (Validator.isNotNull(key)) {
					velocityContext.put(key, value);
				}
			}
		}

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		velocityEngine.evaluate(
			velocityContext, unsyncStringWriter, VelocityUtil.class.getName(),
			input);

		return unsyncStringWriter.toString();
	}

}