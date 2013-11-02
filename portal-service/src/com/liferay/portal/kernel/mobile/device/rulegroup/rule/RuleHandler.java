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

package com.liferay.portal.kernel.mobile.device.rulegroup.rule;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.mobiledevicerules.model.MDRRule;

import java.util.Collection;

/**
 * @author Edward Han
 */
public interface RuleHandler {

	public boolean evaluateRule(MDRRule mdrRule, ThemeDisplay themeDisplay);

	public Collection<String> getPropertyNames();

	public String getType();

}