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

package com.liferay.portal.kernel.servlet.taglib.ui;

import java.util.Map;

/**
 * @author Sergio González
 */
public class BreadcrumbEntry {

	public Map<String, Object> getData() {
		return _data;
	}

	public String getTitle() {
		return _title;
	}

	public String getURL() {
		return _url;
	}

	public void setData(Map<String, Object> data) {
		_data = data;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public void setURL(String url) {
		_url = url;
	}

	private Map<String, Object> _data;
	private String _title;
	private String _url;

}