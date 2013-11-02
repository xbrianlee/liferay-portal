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

package com.liferay.portal.kernel.mobile.device.rulegroup;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.mobile.device.rulegroup.action.ActionHandler;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public class ActionHandlerManagerUtil {

	public static void applyActions(
			List<MDRAction> mdrActions, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException {

		getActionHandlerManager().applyActions(mdrActions, request, response);
	}

	public static ActionHandler getActionHandler(String actionType) {
		return getActionHandlerManager().getActionHandler(actionType);
	}

	public static ActionHandlerManager getActionHandlerManager() {
		PortalRuntimePermission.checkGetBeanProperty(
			ActionHandlerManagerUtil.class);

		return _actionHandlerManager;
	}

	public static Collection<ActionHandler> getActionHandlers() {
		return getActionHandlerManager().getActionHandlers();
	}

	public static Collection<String> getActionHandlerTypes() {
		return getActionHandlerManager().getActionHandlerTypes();
	}

	public static void registerActionHandler(ActionHandler actionHandler) {
		getActionHandlerManager().registerActionHandler(actionHandler);
	}

	public static ActionHandler unregisterActionHandler(String actionType) {
		return getActionHandlerManager().unregisterActionHandler(actionType);
	}

	public void setActionHandlerManager(
		ActionHandlerManager actionHandlerManager) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_actionHandlerManager = actionHandlerManager;
	}

	private static ActionHandlerManager _actionHandlerManager;

}