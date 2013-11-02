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

package com.liferay.portal.upgrade.util;

import com.liferay.portal.kernel.upgrade.StagnantRowException;
import com.liferay.portal.kernel.upgrade.util.ValueMapper;

/**
 * @author Brian Wing Shun Chan
 */
public class LazyPKUpgradeColumnImpl extends PKUpgradeColumnImpl {

	public LazyPKUpgradeColumnImpl(String name) {
		super(name, true);
	}

	public LazyPKUpgradeColumnImpl(String name, Integer oldColumnType) {
		super(name, oldColumnType, true);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		ValueMapper valueMapper = getValueMapper();

		Long newValue = null;

		try {
			newValue = (Long)valueMapper.getNewValue(oldValue);
		}
		catch (StagnantRowException sre) {
			newValue = new Long(increment());

			valueMapper.mapValue(oldValue, newValue);
		}

		return newValue;
	}

}