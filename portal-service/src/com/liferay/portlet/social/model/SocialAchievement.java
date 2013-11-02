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

package com.liferay.portlet.social.model;

/**
 * @author Zsolt Berentey
 */
public interface SocialAchievement {

	public String getDescriptionKey();

	public String getIcon();

	public String getName();

	public String getNameKey();

	public void initialize(SocialActivityDefinition activityDefinition);

	public void processActivity(SocialActivity activity);

	public void setIcon(String icon);

	public void setName(String name);

	public void setProperty(String name, String value);

}