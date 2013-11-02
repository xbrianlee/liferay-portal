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

package com.liferay.portal.kernel.language;

/**
 * @author Brian Wing Shun Chan
 */
public class LanguageWrapper {

	public LanguageWrapper(String before, String text, String after) {
		_before = before;
		_text = text;
		_after = after;
	}

	public String getAfter() {
		return _after;
	}

	public String getBefore() {
		return _before;
	}

	public String getText() {
		return _text;
	}

	private String _after;
	private String _before;
	private String _text;

}