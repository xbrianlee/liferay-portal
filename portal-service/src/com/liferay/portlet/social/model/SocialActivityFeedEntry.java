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

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Brian Wing Shun Chan
 */
public class SocialActivityFeedEntry {

	public SocialActivityFeedEntry(String title, String body) {
		this(null, title, body);
	}

	public SocialActivityFeedEntry(String link, String title, String body) {
		setLink(link);
		setTitle(title);
		setBody(body);
	}

	public String getBody() {
		return _body;
	}

	public String getLink() {
		return _link;
	}

	public String getPortletId() {
		return _portletId;
	}

	public String getTitle() {
		return _title;
	}

	public void setBody(String body) {
		_body = GetterUtil.getString(body);
	}

	public void setLink(String link) {
		_link = GetterUtil.getString(link);
	}

	public void setPortletId(String portletId) {
		_portletId = GetterUtil.getString(portletId);
	}

	public void setTitle(String title) {
		_title = GetterUtil.getString(title);
	}

	private String _body;
	private String _link;
	private String _portletId = StringPool.BLANK;
	private String _title;

}