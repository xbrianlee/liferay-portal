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
 * @author Zsolt Berentey
 */
public class TrashPermissionException extends PortalException {

	public static final int DELETE = 1;

	public static final int EMPTY_TRASH = 2;

	public static final int MOVE = 3;

	public static final int RESTORE = 4;

	public static final int RESTORE_OVERWRITE = 5;

	public static final int RESTORE_RENAME = 6;

	public TrashPermissionException(int type) {
		_type = type;
	}

	public int getType() {
		return _type;
	}

	private int _type;

}