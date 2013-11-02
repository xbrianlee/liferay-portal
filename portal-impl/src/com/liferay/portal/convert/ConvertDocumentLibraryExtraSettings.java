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

package com.liferay.portal.convert;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;

/**
 * @author Alexander Chow
 */
public class ConvertDocumentLibraryExtraSettings extends ConvertProcess {

	@Override
	public String getDescription() {
		return "convert-extra-settings-from-document-library-files";
	}

	@Override
	public String getPath() {
		return "/admin_server/edit_document_library_extra_settings";
	}

	@Override
	public boolean isEnabled() {
		try {
			return DLFileEntryLocalServiceUtil.hasExtraSettings();
		}
		catch (Exception e) {
			_log.error(e, e);

			return false;
		}
	}

	@Override
	protected void doConvert() throws Exception {
	}

	private static Log _log = LogFactoryUtil.getLog(
		ConvertDocumentLibraryExtraSettings.class);

}