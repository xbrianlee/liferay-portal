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

package com.liferay.portal.mobile.device.rulegroup;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.rulegroup.ActionHandlerManager;
import com.liferay.portal.kernel.mobile.device.rulegroup.action.ActionHandler;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Edward C. Han
 */
public class DefaultActionHandlerManagerImpl implements ActionHandlerManager {

	@Override
	public void applyActions(
			List<MDRAction> mdrActions, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException {

		for (MDRAction mdrAction : mdrActions) {
			applyAction(mdrAction, request, response);
		}
	}

	@Override
	public ActionHandler getActionHandler(String actionType) {
		return _deviceActionHandlers.get(actionType);
	}

	@Override
	public Collection<ActionHandler> getActionHandlers() {
		return Collections.unmodifiableCollection(
			_deviceActionHandlers.values());
	}

	@Override
	public Collection<String> getActionHandlerTypes() {
		return _deviceActionHandlers.keySet();
	}

	@Override
	public void registerActionHandler(ActionHandler actionHandler) {
		ActionHandler oldActionHandler = _deviceActionHandlers.put(
			actionHandler.getType(), actionHandler);

		if ((oldActionHandler != null) && _log.isWarnEnabled()) {
			_log.warn(
				"Replacing existing action handler type " +
					actionHandler.getType());
		}
	}

	public void setActionHandlers(Collection<ActionHandler> actionHandlers) {
		for (ActionHandler actionHandler : actionHandlers) {
			registerActionHandler(actionHandler);
		}
	}

	@Override
	public ActionHandler unregisterActionHandler(String actionType) {
		return _deviceActionHandlers.remove(actionType);
	}

	protected void applyAction(
			MDRAction mdrAction, HttpServletRequest request,
			HttpServletResponse response)
		throws PortalException, SystemException {

		ActionHandler actionHandler = _deviceActionHandlers.get(
			mdrAction.getType());

		if (actionHandler != null) {
			actionHandler.applyAction(mdrAction, request, response);
		}
		else if (_log.isWarnEnabled()) {
			_log.warn(
				"No action handler registered for type " + mdrAction.getType());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DefaultActionHandlerManagerImpl.class);

	private Map<String, ActionHandler> _deviceActionHandlers =
		new HashMap<String, ActionHandler>();

}