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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.lock.BaseLockListener;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.util.PropsValues;
import com.liferay.portlet.documentlibrary.model.DLFileEntryConstants;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;

/**
 * @author Alexander Chow
 */
public class DLFileEntryLockListener extends BaseLockListener {

	@Override
	public String getClassName() {
		return DLFileEntryConstants.getClassName();
	}

	@Override
	public void onAfterExpire(String key) {
		long fileEntryId = GetterUtil.getLong(key);

		try {
			if (PropsValues.DL_FILE_ENTRY_LOCK_POLICY == 1) {
				DLFileEntryServiceUtil.checkInFileEntry(
					fileEntryId, true, "Automatic timeout checkin",
					new ServiceContext());

				if (_log.isDebugEnabled()) {
					_log.debug("Lock expired and checked in " + fileEntryId);
				}
			}
			else {
				DLFileEntryServiceUtil.cancelCheckOut(fileEntryId);

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Lock expired and canceled check out of " +
							fileEntryId);
				}
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DLFileEntryLockListener.class);

}