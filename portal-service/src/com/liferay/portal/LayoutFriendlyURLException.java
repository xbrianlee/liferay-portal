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

package com.liferay.portal;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutFriendlyURLException extends PortalException {

	public static final int ADJACENT_SLASHES = 4;

	public static final int DOES_NOT_START_WITH_SLASH = 1;

	public static final int DUPLICATE = 6;

	public static final int ENDS_WITH_SLASH = 2;

	public static final int INVALID_CHARACTERS = 5;

	public static final int KEYWORD_CONFLICT = 7;

	public static final int POSSIBLE_DUPLICATE = 8;

	public static final int TOO_DEEP = 9;

	public static final int TOO_LONG = 10;

	public static final int TOO_SHORT = 3;

	public LayoutFriendlyURLException(int type) {
		_type = type;
	}

	public String getKeywordConflict() {
		return _keywordConflict;
	}

	public int getType() {
		return _type;
	}

	public void setKeywordConflict(String keywordConflict) {
		_keywordConflict = keywordConflict;
	}

	private String _keywordConflict;
	private int _type;

}