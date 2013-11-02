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

package com.liferay.portal.lar.backgroundtask;

import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class BackgroundTaskContextMapFactory {

	public static Map<String, Serializable> buildTaskContextMap(
		long userId, long groupId, boolean privateLayout, long[] layoutIds,
		Map<String, String[]> parameterMap, String cmd, Date startDate,
		Date endDate, String fileName) {

		Map<String, Serializable> taskContextMap =
			new HashMap<String, Serializable>();

		if (cmd != null) {
			taskContextMap.put(Constants.CMD, cmd);
		}

		if (endDate != null) {
			taskContextMap.put("endDate", endDate);
		}

		taskContextMap.put("fileName", fileName);
		taskContextMap.put("groupId", groupId);

		if (ArrayUtil.isNotEmpty(layoutIds)) {
			taskContextMap.put("layoutIds", layoutIds);
		}

		if (parameterMap != null) {
			HashMap<String, String[]> serializableParameterMap =
				new HashMap<String, String[]>(parameterMap);

			taskContextMap.put("parameterMap", serializableParameterMap);
		}

		taskContextMap.put("privateLayout", privateLayout);

		if (startDate != null) {
			taskContextMap.put("startDate", startDate);
		}

		taskContextMap.put("userId", userId);

		return taskContextMap;
	}

	public static Map<String, Serializable> buildTaskContextMap(
		long userId, long plid, long groupId, String portletId,
		Map<String, String[]> parameterMap, String cmd, Date startDate,
		Date endDate, String fileName) {

		Map<String, Serializable> taskContextMap =
			new HashMap<String, Serializable>();

		if (cmd != null) {
			taskContextMap.put(Constants.CMD, cmd);
		}

		if (endDate != null) {
			taskContextMap.put("endDate", endDate);
		}

		taskContextMap.put("fileName", fileName);
		taskContextMap.put("groupId", groupId);

		if (parameterMap != null) {
			HashMap<String, String[]> serializableParameterMap =
				new HashMap<String, String[]>(parameterMap);

			taskContextMap.put("parameterMap", serializableParameterMap);
		}

		taskContextMap.put("plid", plid);

		if (Validator.isNotNull(portletId)) {
			taskContextMap.put("portletId", portletId);
		}

		if (startDate != null) {
			taskContextMap.put("startDate", startDate);
		}

		taskContextMap.put("userId", userId);

		return taskContextMap;
	}

}