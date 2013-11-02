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

package com.liferay.portlet.documentlibrary.action;

import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.struts.FindAction;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 */
public class FindFolderAction extends FindAction {

	@Override
	protected long getGroupId(long primaryKey) throws Exception {
		Folder folder = DLAppLocalServiceUtil.getFolder(primaryKey);

		return folder.getRepositoryId();
	}

	@Override
	protected String getPrimaryKeyParameterName() {
		return "folderId";
	}

	@Override
	protected String getStrutsAction(
		HttpServletRequest request, String portletId) {

		String rootPortletId = PortletConstants.getRootPortletId(portletId);

		if (rootPortletId.equals(PortletKeys.DOCUMENT_LIBRARY_DISPLAY)) {
			return "/document_library_display/view";
		}
		else if (rootPortletId.equals(PortletKeys.MEDIA_GALLERY_DISPLAY)) {
			return "/image_gallery_display/view";
		}

		return "/document_library/view";
	}

	@Override
	protected String[] initPortletIds() {
		return new String[] {
			PortletKeys.DOCUMENT_LIBRARY, PortletKeys.DOCUMENT_LIBRARY_DISPLAY,
			PortletKeys.MEDIA_GALLERY_DISPLAY};
	}

}