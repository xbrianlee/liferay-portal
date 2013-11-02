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

import java.util.Date;

/**
 * @author Shuyang Zhou
 */
public class TriggerFactoryUtil {

	public static Trigger buildTrigger(
			TriggerType triggerType, String jobName, String groupName,
			Date startDate, Date endDate, Object triggerContent)
		throws SchedulerException {

		if (triggerType.equals(TriggerType.CRON)) {
			return new CronTrigger(
				jobName, groupName, startDate, endDate,
				String.valueOf(triggerContent));
		}
		else if (triggerType.equals(TriggerType.SIMPLE)) {
			Number number = (Number)triggerContent;

			return new IntervalTrigger(
				jobName, groupName, startDate, endDate, number.longValue());
		}
		else {
			throw new SchedulerException("Unknown trigger type " + triggerType);
		}
	}

}