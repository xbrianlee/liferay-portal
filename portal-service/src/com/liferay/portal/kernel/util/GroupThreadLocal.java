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

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.GroupConstants;

/**
 * @author Shinn Lok
 */
public class GroupThreadLocal {

	public static Long getGroupId() {
		Long groupId = _groupId.get();

		if (_log.isDebugEnabled()) {
			_log.debug("getGroupId " + groupId);
		}

		return groupId;
	}

	public static boolean isDeleteInProcess() {
		return _deleteInProcess.get();
	}

	public static void setDeleteInProcess(boolean deleteInProcess) {
		_deleteInProcess.set(deleteInProcess);
	}

	public static void setGroupId(Long groupId) {
		if (_log.isDebugEnabled()) {
			_log.debug("setGroupId " + groupId);
		}

		if (groupId > 0) {
			_groupId.set(groupId);
		}
		else {
			_groupId.set(GroupConstants.DEFAULT_LIVE_GROUP_ID);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(GroupThreadLocal.class);

	private static ThreadLocal<Boolean> _deleteInProcess =
		new AutoResetThreadLocal<Boolean>(
			GroupThreadLocal.class + "._deleteInProcess", false);
	private static ThreadLocal<Long> _groupId =
		new AutoResetThreadLocal<Long>(
			GroupThreadLocal.class + "._groupId",
			GroupConstants.DEFAULT_LIVE_GROUP_ID);

}