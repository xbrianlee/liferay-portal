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
public class CronTrigger extends BaseTrigger {

	public CronTrigger(
		String jobName, String groupName, Date startDate, Date endDate,
		String cronText) {

		super(jobName, groupName, TriggerType.CRON, startDate, endDate);

		_cronText = cronText;
	}

	public CronTrigger(
		String jobName, String groupName, Date startDate, String cronText) {

		this(jobName, groupName, startDate, null, cronText);
	}

	public CronTrigger(String jobName, String groupName, String cronText) {
		this(jobName, groupName, null, null, cronText);
	}

	@Override
	public String getTriggerContent() {
		return _cronText;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{cronText=");
		sb.append(_cronText);
		sb.append(", ");
		sb.append(super.toString());
		sb.append("}");

		return sb.toString();
	}

	private String _cronText;

}