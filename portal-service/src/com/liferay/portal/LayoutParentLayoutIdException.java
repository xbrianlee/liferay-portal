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
public class LayoutParentLayoutIdException extends PortalException {

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final int FIRST_LAYOUT_HIDDEN = 4;

	public static final int FIRST_LAYOUT_TYPE = 3;

	public static final int NOT_PARENTABLE = 1;

	public static final int NOT_SORTABLE = 5;

	public static final int SELF_DESCENDANT = 2;

	public LayoutParentLayoutIdException(int type) {
		_type = type;
	}

	public int getType() {
		return _type;
	}

	private int _type;

}