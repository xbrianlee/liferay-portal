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

import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusMessageTranslator;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.Validator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Michael C. Han
 */
public class DefaultExportImportBackgroundTaskStatusMessageTranslator
	implements BackgroundTaskStatusMessageTranslator {

	@Override
	public void translate(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		String messageType = message.getString("messageType");

		if (messageType.equals("layout")) {
			translateLayoutMessage(backgroundTaskStatus, message);
		}
		else if (messageType.equals("portlet")) {
			translatePortletMessage(backgroundTaskStatus, message);
		}
		else if (messageType.equals("stagedModel")) {
			translateStagedModelMessage(backgroundTaskStatus, message);
		}
	}

	protected long getTotal(Map<String, LongWrapper> modelCounters) {
		if (modelCounters == null) {
			return 0;
		}

		long total = 0;

		for (Map.Entry<String, LongWrapper> entry : modelCounters.entrySet()) {
			LongWrapper longWrapper = entry.getValue();

			total += longWrapper.getValue();
		}

		return total;
	}

	protected synchronized void translateLayoutMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		Map<String, LongWrapper> modelAdditionCounters =
			(Map<String, LongWrapper>)message.get("modelAdditionCounters");

		backgroundTaskStatus.setAttribute(
			"allModelAdditionCounters",
			new HashMap<String, LongWrapper>(modelAdditionCounters));
		backgroundTaskStatus.setAttribute(
			"allModelAdditionCountersTotal", getTotal(modelAdditionCounters));

		Map<String, LongWrapper> modelDeletionCounters =
			(Map<String, LongWrapper>)message.get("modelDeletionCounters");

		backgroundTaskStatus.setAttribute(
			"allModelDeletionCounters",
			new HashMap<String, LongWrapper>(modelDeletionCounters));
		backgroundTaskStatus.setAttribute(
			"allModelDeletionCountersTotal", getTotal(modelDeletionCounters));
	}

	protected synchronized void translatePortletMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		backgroundTaskStatus.clearAttributes();

		Map<String, LongWrapper> modelAdditionCounters =
			(Map<String, LongWrapper>)message.get("modelAdditionCounters");

		backgroundTaskStatus.setAttribute(
			"allModelAdditionCounters",
			new HashMap<String, LongWrapper>(modelAdditionCounters));
		backgroundTaskStatus.setAttribute(
			"allModelAdditionCountersTotal", getTotal(modelAdditionCounters));

		Map<String, LongWrapper> modelDeletionCounters =
			(Map<String, LongWrapper>)message.get("modelDeletionCounters");

		backgroundTaskStatus.setAttribute(
			"allModelDeletionCounters",
			new HashMap<String, LongWrapper>(modelDeletionCounters));
		backgroundTaskStatus.setAttribute(
			"allModelDeletionCountersTotal", getTotal(modelDeletionCounters));

		String portletId = message.getString("portletId");

		backgroundTaskStatus.setAttribute("portletId", portletId);
	}

	protected synchronized void translateStagedModelMessage(
		BackgroundTaskStatus backgroundTaskStatus, Message message) {

		String portletId = (String)backgroundTaskStatus.getAttribute(
			"portletId");

		if (Validator.isNull(portletId)) {
			return;
		}

		Map<String, LongWrapper> modelAdditionCounters =
			(Map<String, LongWrapper>)message.get("modelAdditionCounters");

		backgroundTaskStatus.setAttribute(
			"currentModelAdditionCounters",
			new HashMap<String, LongWrapper>(modelAdditionCounters));
		backgroundTaskStatus.setAttribute(
			"currentModelAdditionCountersTotal",
			getTotal(modelAdditionCounters));

		Map<String, LongWrapper> modelDeletionCounters =
			(Map<String, LongWrapper>)message.get("modelDeletionCounters");

		backgroundTaskStatus.setAttribute(
			"currentModelDeletionCounters",
			new HashMap<String, LongWrapper>(modelDeletionCounters));
		backgroundTaskStatus.setAttribute(
			"currentModelDeletionCountersTotal",
			getTotal(modelDeletionCounters));

		String stagedModelName = message.getString("stagedModelName");

		backgroundTaskStatus.setAttribute("stagedModelName", stagedModelName);

		String stagedModelType = message.getString("stagedModelType");

		backgroundTaskStatus.setAttribute("stagedModelType", stagedModelType);

		String uuid = message.getString("uuid");

		backgroundTaskStatus.setAttribute("uuid", uuid);
	}

}