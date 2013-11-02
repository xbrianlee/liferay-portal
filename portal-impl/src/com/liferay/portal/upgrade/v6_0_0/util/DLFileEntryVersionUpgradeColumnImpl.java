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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;

import java.text.NumberFormat;

/**
 * @author Brian Wing Shun Chan
 */
public class DLFileEntryVersionUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public DLFileEntryVersionUpgradeColumnImpl(String name) {
		super(name);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		double version = GetterUtil.getDouble(String.valueOf(oldValue));

		NumberFormat numberFormat = NumberFormat.getInstance(LocaleUtil.US);

		numberFormat.setMaximumFractionDigits(1);
		numberFormat.setMinimumFractionDigits(1);

		return numberFormat.format(version);
	}

}