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

/**
 * @author Sergio González
 */
public class LayoutQueryStringComposite {

	public LayoutQueryStringComposite(
		Layout layout, String friendlyURL, String queryString) {

		_friendlyURL = friendlyURL;
		_layout = layout;
		_queryString = queryString;
	}

	public String getFriendlyURL() {
		return _friendlyURL;
	}

	public Layout getLayout() {
		return _layout;
	}

	public String getQueryString() {
		return _queryString;
	}

	public void setFriendlyURL(String friendlyURL) {
		_friendlyURL = friendlyURL;
	}

	public void setLayout(Layout layout) {
		_layout = layout;
	}

	public void setQueryString(String queryString) {
		_queryString = queryString;
	}

	private String _friendlyURL;
	private Layout _layout;
	private String _queryString;

}