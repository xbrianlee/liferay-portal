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

import com.liferay.portlet.social.model.SocialActivityDefinition;

import java.util.List;

/**
 * @author Zsolt Berentey
 */
public interface SocialConfiguration {

	public List<String> getActivityCounterNames();

	public List<String> getActivityCounterNames(boolean transientCounter);

	public List<String> getActivityCounterNames(int ownerType);

	public List<String> getActivityCounterNames(
		int ownerType, boolean transientCounter);

	public SocialActivityDefinition getActivityDefinition(
		String modelName, int activityType);

	public List<SocialActivityDefinition> getActivityDefinitions(
		String modelName);

	public String[] getActivityModelNames();

	public List<Object> read(ClassLoader classLoader, String[] xmls)
		throws Exception;

	public void removeActivityDefinition(
		SocialActivityDefinition activityDefinition);

}