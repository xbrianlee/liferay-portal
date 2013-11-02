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

package com.liferay.portal.kernel.scheduler;

import com.liferay.portal.kernel.util.StringBundler;

import java.util.Date;

/**
 * @author Shuyang Zhou
 */
public class IntervalTrigger extends BaseTrigger {

	public IntervalTrigger(
		String jobName, String groupName, Date startDate, Date endDate,
		long interval) {

		super(jobName, groupName, TriggerType.SIMPLE, startDate, endDate);

		_interval = interval;
	}

	public IntervalTrigger(
		String jobName, String groupName, Date startDate, long interval) {

		this(jobName, groupName, startDate, null, interval);
	}

	public IntervalTrigger(String jobName, String groupName, long interval) {
		this(jobName, groupName, null, null, interval);
	}

	@Override
	public Long getTriggerContent() {
		return _interval;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{interval=");
		sb.append(_interval);
		sb.append(", ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private Long _interval;

}