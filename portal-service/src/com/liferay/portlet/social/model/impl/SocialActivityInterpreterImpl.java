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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.model.SocialActivityFeedEntry;
import com.liferay.portlet.social.model.SocialActivityInterpreter;
import com.liferay.portlet.social.model.SocialActivitySet;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityInterpreterImpl
	implements SocialActivityInterpreter {

	public SocialActivityInterpreterImpl(
		String portletId, SocialActivityInterpreter activityInterpreter) {

		_portletId = portletId;
		_activityInterpreter = activityInterpreter;

		String[] classNames = _activityInterpreter.getClassNames();

		for (String className : classNames) {
			_classNames.add(className);
		}
	}

	@Override
	public String[] getClassNames() {
		return _activityInterpreter.getClassNames();
	}

	public String getPortletId() {
		return _portletId;
	}

	@Override
	public String getSelector() {
		return _activityInterpreter.getSelector();
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
	public boolean hasPermission(
			PermissionChecker permissionChecker, SocialActivity activity,
			String actionId, ServiceContext serviceContext)
		throws Exception {

		return _activityInterpreter.hasPermission(
			permissionChecker, activity, actionId, serviceContext);
	}

	@Override
	public SocialActivityFeedEntry interpret(
		SocialActivity activity, ServiceContext serviceContext) {

		return _activityInterpreter.interpret(activity, serviceContext);
	}

	@Override
	public SocialActivityFeedEntry interpret(
		SocialActivitySet activitySet, ServiceContext serviceContext) {

		return _activityInterpreter.interpret(activitySet, serviceContext);
	}

	@Override
	public void updateActivitySet(long activityId)
		throws PortalException, SystemException {

		_activityInterpreter.updateActivitySet(activityId);
	}

	private SocialActivityInterpreter _activityInterpreter;
	private Set<String> _classNames = new HashSet<String>();
	private String _portletId;

}