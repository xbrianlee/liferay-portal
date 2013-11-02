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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.social.model.SocialRequest;
import com.liferay.portlet.social.model.SocialRequestFeedEntry;
import com.liferay.portlet.social.model.SocialRequestInterpreter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialRequestInterpreterImpl implements SocialRequestInterpreter {

	public SocialRequestInterpreterImpl(
		String portletId, SocialRequestInterpreter requestInterpreter) {

		_portletId = portletId;
		_requestInterpreter = requestInterpreter;

		String[] classNames = _requestInterpreter.getClassNames();

		for (String className : classNames) {
			_classNames.add(className);
		}
	}

	@Override
	public String[] getClassNames() {
		return _requestInterpreter.getClassNames();
	}

	public String getPortletId() {
		return _portletId;
	}

	public boolean hasClassName(String className) {
		if (_classNames.contains(className)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public SocialRequestFeedEntry interpret(
		SocialRequest request, ThemeDisplay themeDisplay) {

		return _requestInterpreter.interpret(request, themeDisplay);
	}

	@Override
	public boolean processConfirmation(
		SocialRequest request, ThemeDisplay themeDisplay) {

		return _requestInterpreter.processConfirmation(request, themeDisplay);
	}

	@Override
	public boolean processRejection(
		SocialRequest request, ThemeDisplay themeDisplay) {

		return _requestInterpreter.processRejection(request, themeDisplay);
	}

	private Set<String> _classNames = new HashSet<String>();
	private String _portletId;
	private SocialRequestInterpreter _requestInterpreter;

}