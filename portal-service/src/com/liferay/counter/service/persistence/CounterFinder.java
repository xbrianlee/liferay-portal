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

package com.liferay.counter.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface CounterFinder {
	public void afterPropertiesSet();

	public java.util.List<java.lang.String> getNames()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.lang.String getRegistryName();

	public long increment()
		throws com.liferay.portal.kernel.exception.SystemException;

	public long increment(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public long increment(java.lang.String name, int size)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void invalidate();

	public void rename(java.lang.String oldName, java.lang.String newName)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void reset(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void reset(java.lang.String name, long size)
		throws com.liferay.portal.kernel.exception.SystemException;
}