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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.model.StagedModel;

/**
 * @author Michael C. Han
 */
public interface PortletDataHandlerStatusMessageSender {

	public void sendStatusMessage(
		String messageType, ManifestSummary manifestSummary);

	public void sendStatusMessage(
		String messageType, String portletId, ManifestSummary manifestSummary);

	public <T extends StagedModel> void sendStatusMessage(
		String messageType, T stagedModel, ManifestSummary manifestSummary);

}