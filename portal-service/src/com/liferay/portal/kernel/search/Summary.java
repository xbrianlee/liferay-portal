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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.util.StringUtil;

import java.util.Locale;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 * @author Ryan Park
 */
public class Summary {

	public Summary(
		Locale locale, String title, String content, PortletURL portletURL) {

		_title = title;
		_content = content;
		_locale = locale;
		_portletURL = portletURL;
	}

	public Summary(String title, String content, PortletURL portletURL) {
		_title = title;
		_content = content;
		_portletURL = portletURL;
	}

	public String getContent() {
		return _content;
	}

	public Locale getLocale() {
		return _locale;
	}

	public int getMaxContentLength() {
		return _maxContentLength;
	}

	public PortletURL getPortletURL() {
		return _portletURL;
	}

	public String getTitle() {
		return _title;
	}

	public void setContent(String content) {
		_content = content;

		if ((_content != null) && (_maxContentLength > 0) &&
			(_content.length() > _maxContentLength)) {

			_content = StringUtil.shorten(_content, _maxContentLength);
		}
	}

	public void setLocale(Locale locale) {
		_locale = locale;
	}

	public void setMaxContentLength(int maxContentLength) {
		_maxContentLength = maxContentLength;

		setContent(_content);
	}

	public void setPortletURL(PortletURL portletURL) {
		_portletURL = portletURL;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _content;
	private Locale _locale;
	private int _maxContentLength;
	private PortletURL _portletURL;
	private String _title;

}