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

package com.liferay.portlet;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class InvokerPortletResponse implements Serializable {

	public InvokerPortletResponse(String title, String content, long time) {
		_title = title;
		_content = content;
		_time = time;
	}

	public String getContent() {
		return _content;
	}

	public long getTime() {
		return _time;
	}

	public String getTitle() {
		return _title;
	}

	public void setContent(String content) {
		_content = content;
	}

	public void setTime(long time) {
		_time = time;
	}

	public void setTitle(String title) {
		_title = title;
	}

	private String _content;
	private long _time;
	private String _title;

}