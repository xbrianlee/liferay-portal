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

package com.liferay.portal.upgrade.v6_1_0;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFileEntryTypeConstants;
import com.liferay.portlet.documentlibrary.model.DLFolder;

/**
 * @author Alexander Chow
 */
public class UpgradeWorkflow extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		StringBundler sb = new StringBundler();

		sb.append("update WorkflowDefinitionLink set classNameId = ");

		long folderClassNameId = PortalUtil.getClassNameId(DLFolder.class);

		sb.append(folderClassNameId);

		sb.append(", typePK = ");
		sb.append(DLFileEntryTypeConstants.FILE_ENTRY_TYPE_ID_ALL);
		sb.append(" where classNameId = ");

		long fileEntryClassNameId = PortalUtil.getClassNameId(
			DLFileEntry.class);

		sb.append(fileEntryClassNameId);

		runSQL(sb.toString());
	}

}