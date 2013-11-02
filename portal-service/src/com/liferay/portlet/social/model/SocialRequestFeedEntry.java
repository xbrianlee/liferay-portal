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
 * @author Brian Wing Shun Chan
 */
public class SocialRequestFeedEntry {

	public SocialRequestFeedEntry(String title, String body) {
		_title = title;
		_body = body;
	}

	public String getBody() {
		return _body;
	}

	public String getPortletId() {
		return _portletId;
	}

	public String getTitle() {
		return _title;
	}

	public void setBody(String body) {
		_body = body;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _body;
	private String _portletId;
	private String _title;

}