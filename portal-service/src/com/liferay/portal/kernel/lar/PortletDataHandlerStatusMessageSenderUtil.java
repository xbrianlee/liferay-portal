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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.model.StagedModel;

/**
 * @author Michael C. Han
 */
public class PortletDataHandlerStatusMessageSenderUtil {

	public static PortletDataHandlerStatusMessageSender
		getPortletDataHandlerStatusMessageSender() {

		PortalRuntimePermission.checkGetBeanProperty(
			PortletDataHandlerStatusMessageSenderUtil.class);

		return _dataHandlerStatusMessageSender;
	}

	public static void sendStatusMessage(
		String messageType, ManifestSummary manifestSummary) {

		getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, manifestSummary);
	}

	public static void sendStatusMessage(
		String messageType, String portletId, ManifestSummary manifestSummary) {

		getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, portletId, manifestSummary);
	}

	public static <T extends StagedModel> void sendStatusMessage(
		String messageType, T stagedModel, ManifestSummary manifestSummary) {

		getPortletDataHandlerStatusMessageSender().sendStatusMessage(
			messageType, stagedModel, manifestSummary);
	}

	public void setPortletDataHandlerStatusMessageSender(
		PortletDataHandlerStatusMessageSender
		portletDataHandlerStatusMessageSender) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_dataHandlerStatusMessageSender = portletDataHandlerStatusMessageSender;
	}

	private static PortletDataHandlerStatusMessageSender
		_dataHandlerStatusMessageSender;

}