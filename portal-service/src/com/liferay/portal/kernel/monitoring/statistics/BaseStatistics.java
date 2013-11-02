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
public class BaseStatistics implements Statistics {

	public BaseStatistics(String name) {
		_name = name;
		_startTime = System.currentTimeMillis();
	}

	@Override
	public String getDescription() {
		return _description;
	}

	public long getLastSampleTime() {
		return _lastSampleTime;
	}

	public long getLastTime() {
		return _lastTime;
	}

	public long getLowerBound() {
		return _lowerBound;
	}

	public long getMaxTime() {
		return _maxTime;
	}

	public long getMinTime() {
		return _minTime;
	}

	@Override
	public String getName() {
		return _name;
	}

	public long getStartTime() {
		return _startTime;
	}

	public long getUpperBound() {
		return _upperBound;
	}

	public long getUptime() {
		return System.currentTimeMillis() - _startTime;
	}

	@Override
	public void reset() {
		_maxTime = 0;
		_minTime = 0;
		_lastTime = 0;
		_startTime = System.currentTimeMillis();
		_lastSampleTime = _startTime;
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	public void setLastSampleTime(long lastSampleTime) {
		_lastSampleTime = lastSampleTime;
	}

	public void setLastTime(long lastTime) {
		_lastTime = lastTime;
	}

	public void setLowerBound(long lowerBound) {
		_lowerBound = lowerBound;
	}

	public void setMaxTime(long maxTime) {
		_maxTime = maxTime;
	}

	public void setMinTime(long minTime) {
		_minTime = minTime;
	}

	public void setStartTime(long startTime) {
		_startTime = startTime;
	}

	public void setUpperBound(long upperBound) {
		_upperBound = upperBound;
	}

	private String _description;
	private long _lastSampleTime;
	private long _lastTime;
	private long _lowerBound;
	private long _maxTime;
	private long _minTime;
	private String _name;
	private long _startTime;
	private long _upperBound;

}