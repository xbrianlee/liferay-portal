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

package com.liferay.portlet.social.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.social.model.SocialActivityDefinition;

import java.util.List;

/**
 * @author Zsolt Berentey
 */
public class SocialConfigurationUtil {

	public static List<String> getActivityCounterNames() {
		return getSocialConfiguration().getActivityCounterNames();
	}

	public static List<String> getActivityCounterNames(
		boolean transientCounter) {

		return getSocialConfiguration().getActivityCounterNames(
			transientCounter);
	}

	public static List<String> getActivityCounterNames(int ownerType) {
		return getSocialConfiguration().getActivityCounterNames(ownerType);
	}

	public static List<String> getActivityCounterNames(
		int ownerType, boolean transientCounter) {

		return getSocialConfiguration().getActivityCounterNames(
			ownerType, transientCounter);
	}

	public static SocialActivityDefinition getActivityDefinition(
		String modelName, int activityType) {

		return getSocialConfiguration().getActivityDefinition(
			modelName, activityType);
	}

	public static List<SocialActivityDefinition> getActivityDefinitions(
		String modelName) {

		return getSocialConfiguration().getActivityDefinitions(modelName);
	}

	public static String[] getActivityModelNames() {
		return getSocialConfiguration().getActivityModelNames();
	}

	public static SocialConfiguration getSocialConfiguration() {
		PortalRuntimePermission.checkGetBeanProperty(
			SocialConfigurationUtil.class);

		return _socialConfiguration;
	}

	public static List<Object> read(ClassLoader classLoader, String[] xmls)
		throws Exception {

		return getSocialConfiguration().read(classLoader, xmls);
	}

	public static void removeActivityDefinition(
		SocialActivityDefinition activityDefinition) {

		getSocialConfiguration().removeActivityDefinition(activityDefinition);
	}

	public void setSocialConfiguration(
		SocialConfiguration socialConfiguration) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_socialConfiguration = socialConfiguration;
	}

	private static SocialConfiguration _socialConfiguration;

}