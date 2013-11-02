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

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskResult;
import com.liferay.portal.kernel.lar.MissingReferences;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.service.LayoutLocalServiceUtil;

import java.io.File;
import java.io.Serializable;

import java.util.Date;
import java.util.Map;

/**
 * @author Julio Camarero
 */
public class PortletStagingBackgroundTaskExecutor
	extends BaseStagingBackgroundTaskExecutor {

	@Override
	public BackgroundTaskResult execute(BackgroundTask backgroundTask)
		throws Exception {

		Map<String, Serializable> taskContextMap =
			backgroundTask.getTaskContextMap();

		long userId = MapUtil.getLong(taskContextMap, "userId");
		long targetPlid = MapUtil.getLong(taskContextMap, "targetPlid");
		long targetGroupId = MapUtil.getLong(taskContextMap, "targetGroupId");
		String portletId = MapUtil.getString(taskContextMap, "portletId");
		Map<String, String[]> parameterMap =
			(Map<String, String[]>)taskContextMap.get("parameterMap");

		long sourcePlid = MapUtil.getLong(taskContextMap, "sourcePlid");
		long sourceGroupId = MapUtil.getLong(taskContextMap, "sourceGroupId");
		Date startDate = (Date)taskContextMap.get("startDate");
		Date endDate = (Date)taskContextMap.get("endDate");

		File larFile = LayoutLocalServiceUtil.exportPortletInfoAsFile(
			sourcePlid, sourceGroupId, portletId, parameterMap, startDate,
			endDate);

		backgroundTask = markBackgroundTask(backgroundTask, "exported");

		MissingReferences missingReferences = null;

		try {
			missingReferences =
				LayoutLocalServiceUtil.validateImportPortletInfo(
					userId, targetPlid, targetGroupId, portletId, parameterMap,
					larFile);

			backgroundTask = markBackgroundTask(backgroundTask, "validated");

			LayoutLocalServiceUtil.importPortletInfo(
				userId, targetPlid, targetGroupId, portletId, parameterMap,
				larFile);
		}
		finally {
			larFile.delete();
		}

		return processMissingReferences(backgroundTask, missingReferences);
	}

}