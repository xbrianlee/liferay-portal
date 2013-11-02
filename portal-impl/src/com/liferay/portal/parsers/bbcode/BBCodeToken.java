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

package com.liferay.portal.parsers.bbcode;

import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Iliyan Peychev
 */
public class BBCodeToken {

	public BBCodeToken(String endTag) {
		_endTag = endTag;
	}

	public BBCodeToken(
		String startTag, String attribute, String endTag, int start, int end) {

		_startTag = StringUtil.lowerCase(startTag);
		_attribute = attribute;
		_endTag = StringUtil.lowerCase(endTag);
		_start = start;
		_end = end;
	}

	public String getAttribute() {
		return _attribute;
	}

	public int getEnd() {
		return _end;
	}

	public String getEndTag() {
		return _endTag;
	}

	public int getStart() {
		return _start;
	}

	public String getStartTag() {
		return _startTag;
	}

	public void setAttribute(String attribute) {
		_attribute = attribute;
	}

	private String _attribute;
	private int _end;
	private String _endTag;
	private int _start;
	private String _startTag;

}