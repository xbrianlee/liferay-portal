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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Dummy;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class DummyImpl extends BaseModelImpl<Dummy> implements Dummy {

	public DummyImpl() {
	}

	@Override
	public Object clone() {
		return new DummyImpl();
	}

	@Override
	public int compareTo(Dummy dummy) {
		return 0;
	}

	@Override
	public Class<?> getModelClass() {
		return DummyImpl.class;
	}

	@Override
	public String getModelClassName() {
		return DummyImpl.class.getName();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return StringPool.BLANK;
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
	}

	@Override
	public String toXmlString() {
		return StringPool.BLANK;
	}

}