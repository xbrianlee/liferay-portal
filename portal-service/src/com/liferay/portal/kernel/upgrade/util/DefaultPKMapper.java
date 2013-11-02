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

import com.liferay.portal.kernel.upgrade.StagnantRowException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class DefaultPKMapper extends ValueMapperWrapper {

	public DefaultPKMapper(ValueMapper valueMapper) {
		super(valueMapper);
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		String oldValueString = GetterUtil.getString(String.valueOf(oldValue));

		if (oldValueString.equals("-1") || oldValueString.equals("0") ||
			oldValueString.equals("")) {

			return new Long(0);
		}

		try {
			ValueMapper valueMapper = getValueMapper();

			if (oldValue instanceof String) {
				oldValue = StringUtil.toLowerCase(oldValueString);
			}

			return valueMapper.getNewValue(oldValue);
		}
		catch (StagnantRowException sre) {
			return new Long(0);
		}
	}

}