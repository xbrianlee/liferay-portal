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

package com.liferay.portlet.asset;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetTagException extends PortalException {

	public static final int AT_LEAST_ONE_TAG = 1;

	public static final int INVALID_CHARACTER = 2;

	public AssetTagException(int type) {
		_type = type;
	}

	public AssetTagException(String message, int type) {
		super(message);

		_type = type;
	}

	public int getType() {
		return _type;
	}

	private int _type;

}