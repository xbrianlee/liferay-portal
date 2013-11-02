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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.taglib.util.IncludeTag;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Raymond Augé
 */
public class SocialActivitiesTag extends IncludeTag {

	public void setActivities(List<SocialActivity> activities) {
		_activities = activities;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setDisplayRSSFeed(boolean displayRSSFeed) {
		_displayRSSFeed = displayRSSFeed;
	}

	public void setFeedDelta(int feedDelta) {
		_feedDelta = feedDelta;
	}

	public void setFeedDisplayStyle(String feedDisplayStyle) {
		_feedDisplayStyle = feedDisplayStyle;
	}

	public void setFeedEnabled(boolean feedEnabled) {
		_feedEnabled = feedEnabled;
	}

	public void setFeedLink(String feedLink) {
		_feedLink = feedLink;
	}

	public void setFeedLinkMessage(String feedLinkMessage) {
		_feedLinkMessage = feedLinkMessage;
	}

	public void setFeedTitle(String feedTitle) {
		_feedTitle = feedTitle;
	}

	public void setFeedType(String feedType) {
		_feedType = feedType;
	}

	@Override
	protected void cleanUp() {
		_activities = null;
		_className = StringPool.BLANK;
		_classPK = 0;
		_displayRSSFeed = false;
		_feedDelta = 0;
		_feedDisplayStyle = null;
		_feedEnabled = false;
		_feedLink = StringPool.BLANK;
		_feedLinkMessage = StringPool.BLANK;
		_feedTitle = null;
		_feedType = null;
	}

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		request.setAttribute(
			"liferay-ui:social-activities:activities", _activities);
		request.setAttribute(
			"liferay-ui:social-activities:className", _className);
		request.setAttribute(
			"liferay-ui:social-activities:classPK", String.valueOf(_classPK));
		request.setAttribute(
			"liferay-ui:social-activities:displayRSSFeed",
			String.valueOf(_displayRSSFeed));
		request.setAttribute(
			"liferay-ui:social-activities:feedDelta",
			String.valueOf(_feedDelta));
		request.setAttribute(
			"liferay-ui:social-activities:feedDisplayStyle", _feedDisplayStyle);
		request.setAttribute(
			"liferay-ui:social-activities:feedEnabled",
			String.valueOf(_feedEnabled));
		request.setAttribute(
			"liferay-ui:social-activities:feedLink", _feedLink);
		request.setAttribute(
			"liferay-ui:social-activities:feedLinkMessage", _feedLinkMessage);
		request.setAttribute(
			"liferay-ui:social-activities:feedTitle", _feedTitle);
		request.setAttribute(
			"liferay-ui:social-activities:feedType", _feedType);
	}

	private static final String _PAGE =
		"/html/taglib/ui/social_activities/page.jsp";

	private List<SocialActivity> _activities;
	private String _className = StringPool.BLANK;
	private long _classPK;
	private boolean _displayRSSFeed;
	private int _feedDelta;
	private String _feedDisplayStyle;
	private boolean _feedEnabled;
	private String _feedLink = StringPool.BLANK;
	private String _feedLinkMessage = StringPool.BLANK;
	private String _feedTitle;
	private String _feedType;

}