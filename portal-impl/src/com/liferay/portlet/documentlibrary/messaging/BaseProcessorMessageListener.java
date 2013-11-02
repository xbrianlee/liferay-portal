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

package com.liferay.portlet.documentlibrary.messaging;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.repository.model.FileVersion;

/**
 * @author Alexander Chow
 */
public abstract class BaseProcessorMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		Object[] array = (Object[])message.getPayload();

		FileVersion sourceFileVersion = (FileVersion)array[0];
		FileVersion destinationFileVersion = (FileVersion)array[1];

		try {
			generate(sourceFileVersion, destinationFileVersion);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to process file version " +
						destinationFileVersion.getFileVersionId(),
					e);
			}
		}
	}

	protected abstract void generate(
			FileVersion sourceFileVersion, FileVersion destinationFileVersion)
		throws Exception;

	private static Log _log = LogFactoryUtil.getLog(
		BaseProcessorMessageListener.class);

}