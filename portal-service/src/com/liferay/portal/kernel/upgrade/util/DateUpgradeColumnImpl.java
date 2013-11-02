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

package com.liferay.portal.kernel.upgrade.util;

import com.liferay.portal.kernel.util.StringPool;

import java.sql.Types;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 */
public class DateUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public DateUpgradeColumnImpl(String name) {
		super(name, Types.TIMESTAMP);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		if (StringPool.NULL.equals(oldValue)) {
			return 0;
		}

		Date oldDate = (Date)oldValue;

		return oldDate.getTime();
	}

}