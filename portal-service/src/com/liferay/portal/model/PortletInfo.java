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

package com.liferay.portal.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class PortletInfo implements Serializable {

	public PortletInfo() {
	}

	public PortletInfo(
		String title, String shortTitle, String keywords, String description) {

		_title = title;
		_shortTitle = shortTitle;
		_keywords = keywords;
		_description = description;
	}

	public String getDescription() {
		return _description;
	}

	public String getKeywords() {
		return _keywords;
	}

	public String getShortTitle() {
		return _shortTitle;
	}

	public String getTitle() {
		return _title;
	}

	private String _description;
	private String _keywords;
	private String _shortTitle;
	private String _title;

}