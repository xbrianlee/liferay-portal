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

package com.liferay.counter.service.impl;

import com.liferay.counter.service.CounterLocalService;
import com.liferay.counter.service.base.CounterLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Edward Han
 */
public class CounterLocalServiceImpl
	extends CounterLocalServiceBaseImpl implements CounterLocalService {

	@Override
	public List<String> getNames() throws SystemException {
		return counterFinder.getNames();
	}

	@Override
	@Transactional(
		isolation = Isolation.COUNTER, propagation = Propagation.REQUIRES_NEW)
	public long increment() throws SystemException {
		return counterFinder.increment();
	}

	@Override
	@Transactional(
		isolation = Isolation.COUNTER, propagation = Propagation.REQUIRES_NEW)
	public long increment(String name) throws SystemException {
		return counterFinder.increment(name);
	}

	@Override
	@Transactional(
		isolation = Isolation.COUNTER, propagation = Propagation.REQUIRES_NEW)
	public long increment(String name, int size) throws SystemException {
		return counterFinder.increment(name, size);
	}

	@Override
	@Transactional(
		isolation = Isolation.COUNTER, propagation = Propagation.REQUIRES_NEW)
	public void rename(String oldName, String newName) throws SystemException {
		counterFinder.rename(oldName, newName);
	}

	@Override
	@Transactional(
		isolation = Isolation.COUNTER, propagation = Propagation.REQUIRES_NEW)
	public void reset(String name) throws SystemException {
		counterFinder.reset(name);
	}

	@Override
	@Transactional(
		isolation = Isolation.COUNTER, propagation = Propagation.REQUIRES_NEW)
	public void reset(String name, long size) throws SystemException {
		counterFinder.reset(name, size);
	}

}