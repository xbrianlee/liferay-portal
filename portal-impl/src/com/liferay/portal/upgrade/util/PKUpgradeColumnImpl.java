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

import com.liferay.portal.kernel.upgrade.util.BaseUpgradeColumnImpl;
import com.liferay.portal.kernel.upgrade.util.ValueMapper;
import com.liferay.portal.kernel.upgrade.util.ValueMapperFactoryUtil;

import java.sql.Types;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class PKUpgradeColumnImpl extends BaseUpgradeColumnImpl {

	public PKUpgradeColumnImpl(String name, boolean trackValues) {
		this(name, null, trackValues);
	}

	public PKUpgradeColumnImpl(
		String name, Integer oldColumnType, boolean trackValues) {

		super(name, oldColumnType);

		_newColumnType = new Integer(Types.BIGINT);
		_trackValues = trackValues;

		if (_trackValues) {
			_valueMapper = ValueMapperFactoryUtil.getValueMapper();
		}
	}

	@Override
	public Integer getNewColumnType(Integer defaultType) {
		return _newColumnType;
	}

	@Override
	public Object getNewValue(Object oldValue) throws Exception {
		Long newValue = new Long(increment());

		if (_trackValues) {
			_valueMapper.mapValue(oldValue, newValue);
		}

		return newValue;
	}

	public ValueMapper getValueMapper() {
		return _valueMapper;
	}

	public boolean isTrackValues() {
		return _trackValues;
	}

	private Integer _newColumnType;
	private boolean _trackValues;
	private ValueMapper _valueMapper;

}