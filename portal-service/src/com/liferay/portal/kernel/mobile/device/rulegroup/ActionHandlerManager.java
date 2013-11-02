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
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward Han
 */
public interface ActionHandlerManager {

	public void applyActions(
			List<MDRAction> mdrActions, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException;

	public ActionHandler getActionHandler(String actionType);

	public Collection<ActionHandler> getActionHandlers();

	public Collection<String> getActionHandlerTypes();

	public void registerActionHandler(ActionHandler actionHandler);

	public ActionHandler unregisterActionHandler(String actionType);

}