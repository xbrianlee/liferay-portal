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
import com.liferay.portal.kernel.util.FileUtil;

/**
 * @author Alexander Chow
 */
public class DLFileEntryNameUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public static String getNewName(String name) throws Exception {
		if (name.startsWith("DLFE-")) {
			name = name.substring(5);
		}

		name = FileUtil.stripExtension(name);

		return name;
	}

	public DLFileEntryNameUpgradeColumnImpl(String name) {
		super(name);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		String name = (String)oldValue;

		return getNewName(name);
	}

}