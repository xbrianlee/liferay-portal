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

package com.liferay.portal.upgrade.v6_0_0.util;

import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.UpgradeColumn;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alexander Chow
 */
public class DLFileEntryTitleUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public DLFileEntryTitleUpgradeColumnImpl(
		UpgradeColumn nameColumn, String title) {

		super(title);

		_nameColumn = nameColumn;
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		String title = (String)oldValue;

		String name = (String)_nameColumn.getOldValue();
		String extension = FileUtil.getExtension(name);

		if (Validator.isNotNull(extension)) {
			return title + StringPool.PERIOD + extension;
		}
		else {
			return title;
		}
	}

	private UpgradeColumn _nameColumn;

}