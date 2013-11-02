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

package com.liferay.portlet.messageboards.model;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Alexander Chow
 * @author Juan Fernández
 */
public class MBMessageConstants {

	public static final String DEFAULT_FORMAT = PropsUtil.get(
		PropsKeys.MESSAGE_BOARDS_MESSAGE_FORMATS_DEFAULT);

	public static final long DEFAULT_PARENT_MESSAGE_ID = 0;

	public static final String[] FORMATS = PropsUtil.getArray(
		PropsKeys.MESSAGE_BOARDS_MESSAGE_FORMATS);

}