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

package com.liferay.portal.kernel.monitoring.statistics;

/**
 * @author Rajesh Thiagarajan
 * @author Brian Wing Shun Chan
 */
public class CountStatistics extends BaseStatistics {

	public CountStatistics(String name) {
		super(name);
	}

	public void decrementCount() {
		_count--;

		setLastSampleTime(System.currentTimeMillis());
	}

	public long getCount() {
		return _count;
	}

	public void incrementCount() {
		_count++;

		setLastSampleTime(System.currentTimeMillis());
	}

	@Override
	public void reset() {
		super.reset();

		_count = 0;
	}

	public void setCount(long count) {
		_count = count;

		setLastSampleTime(System.currentTimeMillis());
	}

	private long _count;

}