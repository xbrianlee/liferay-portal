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

import com.liferay.portal.kernel.exception.SystemException;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public interface UpgradeColumn {

	public String getName();

	public Integer getNewColumnType(Integer defaultType);

	public Object getNewValue();

	public Object getNewValue(Object oldValue) throws Exception;

	public Integer getOldColumnType(Integer defaultType);

	public Object getOldValue();

	public long increment() throws SystemException;

	public boolean isApplicable(String name);

	public void setNewValue(Object newValue);

	public void setOldValue(Object oldValue);

}