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
public class AverageStatistics extends BaseStatistics {

	public AverageStatistics(String name) {
		super(name);

		_countStatistics = new CountStatistics(name);
	}

	public void addDuration(long duration) {
		_countStatistics.incrementCount();

		setLastTime(duration);

		if (getMaxTime() < duration) {
			setMaxTime(duration);
		}
		else if ((getMinTime() == 0) || (getMinTime() > duration)) {
			setMinTime(duration);
		}

		if (_averageTime == 0) {
			_averageTime = duration;
		}
		else {
			long span = 0;

			if (_countStatistics.getCount() < getLowerBound()) {
				span = getLowerBound();
			}
			else if (_countStatistics.getCount() > getUpperBound()) {
				span = getUpperBound();
			}
			else {
				span = _countStatistics.getCount();
			}

			_averageTime = (_averageTime * span + duration) / (span + 1);
		}

		setLastSampleTime(System.currentTimeMillis());
	}

	public long getAverageTime() {
		return _averageTime;
	}

	public long getCount() {
		return _countStatistics.getCount();
	}

	@Override
	public void reset() {
		super.reset();

		_averageTime = 0;
	}

	private long _averageTime;
	private CountStatistics _countStatistics;

}