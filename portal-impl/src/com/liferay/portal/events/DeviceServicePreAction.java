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

package com.liferay.portal.events;

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceDetectionUtil;
import com.liferay.portal.kernel.mobile.device.UnknownDevice;
import com.liferay.portal.kernel.mobile.device.rulegroup.ActionHandlerManagerUtil;
import com.liferay.portal.kernel.mobile.device.rulegroup.RuleGroupProcessorUtil;
import com.liferay.portal.kernel.util.TransientValue;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.mobiledevicerules.model.MDRAction;
import com.liferay.portlet.mobiledevicerules.model.MDRRuleGroupInstance;
import com.liferay.portlet.mobiledevicerules.service.MDRActionLocalServiceUtil;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Edward Han
 */
public class DeviceServicePreAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ThemeDisplay themeDisplay = (ThemeDisplay)request.getAttribute(
			WebKeys.THEME_DISPLAY);

		Device device = null;

		if (PropsValues.MOBILE_DEVICE_SESSION_CACHE_ENABLED) {
			TransientValue<Device> transientValue =
				(TransientValue<Device>)session.getAttribute(WebKeys.DEVICE);

			if (transientValue != null) {
				device = transientValue.getValue();
			}
		}

		if (device == null) {
			device = DeviceDetectionUtil.detectDevice(request);

			if (PropsValues.MOBILE_DEVICE_SESSION_CACHE_ENABLED) {
				session.setAttribute(
					WebKeys.DEVICE, new TransientValue<Device>(device));
			}
		}

		themeDisplay.setDevice(device);

		UnknownDevice unknownDevice = UnknownDevice.getInstance();

		if (device.equals(unknownDevice)) {
			return;
		}

		MDRRuleGroupInstance mdrRuleGroupInstance = null;

		try {
			mdrRuleGroupInstance = RuleGroupProcessorUtil.evaluateRuleGroups(
				themeDisplay);

			if (_log.isDebugEnabled()) {
				String logMessage =
					"Rule group evaluation returned rule group instance ";

				if (mdrRuleGroupInstance != null) {
					logMessage += mdrRuleGroupInstance.getRuleGroupInstanceId();
				}
				else {
					logMessage += "null";
				}

				_log.debug(logMessage);
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to retrieve rule group", e);
			}

			return;
		}

		themeDisplay.setMDRRuleGroupInstance(mdrRuleGroupInstance);

		if (mdrRuleGroupInstance == null) {
			return;
		}

		try {
			List<MDRAction> mdrActions = MDRActionLocalServiceUtil.getActions(
				mdrRuleGroupInstance.getRuleGroupInstanceId());

			ActionHandlerManagerUtil.applyActions(
				mdrActions, request, response);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to apply device profile", e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DeviceServicePreAction.class);

}