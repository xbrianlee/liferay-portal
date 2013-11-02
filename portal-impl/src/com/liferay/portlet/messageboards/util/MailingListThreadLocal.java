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

package com.liferay.portlet.messageboards.util;

import com.liferay.portal.kernel.util.InitialThreadLocal;

/**
 * @author Thiago Moreira
 */
public class MailingListThreadLocal {

	public static boolean isSourceMailingList() {
		return _sourceMailingList.get();
	}

	public static void setSourceMailingList(boolean sourceMailingList) {
		_sourceMailingList.set(sourceMailingList);
	}

	private static ThreadLocal<Boolean> _sourceMailingList =
		new InitialThreadLocal<Boolean>(
			MailingListThreadLocal.class + "._sourceMailingList", false);

}