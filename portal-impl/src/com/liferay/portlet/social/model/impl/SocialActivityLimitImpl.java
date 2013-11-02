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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portlet.social.model.SocialActivityCounterDefinition;
import com.liferay.portlet.social.util.SocialCounterPeriodUtil;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityLimitImpl extends SocialActivityLimitBaseImpl {

	@Override
	public int getCount() {
		String value = getValue();

		if (!value.contains(StringPool.SLASH)) {
			return getCount(
				SocialActivityCounterDefinition.LIMIT_PERIOD_LIFETIME);
		}

		String[] valueParts = StringUtil.split(value, StringPool.SLASH);

		if (valueParts[0].contains(StringPool.DASH)) {
			return getCount(
				SocialActivityCounterDefinition.LIMIT_PERIOD_PERIOD);
		}

		return getCount(SocialActivityCounterDefinition.LIMIT_PERIOD_DAY);
	}

	@Override
	public int getCount(int limitPeriod) {
		String[] valueParts = StringUtil.split(getValue(), StringPool.SLASH);

		if ((limitPeriod !=
				SocialActivityCounterDefinition.LIMIT_PERIOD_LIFETIME) &&
			(valueParts.length < 2)) {

			return 0;
		}

		int count = GetterUtil.getInteger(valueParts[valueParts.length-1], 0);

		if (limitPeriod == SocialActivityCounterDefinition.LIMIT_PERIOD_DAY) {
			int activityDay = SocialCounterPeriodUtil.getActivityDay();

			if (activityDay == GetterUtil.getInteger(valueParts[0], 0)) {
				return count;
			}
		}
		else if (limitPeriod ==
					SocialActivityCounterDefinition.LIMIT_PERIOD_LIFETIME) {

			return count;
		}
		else if (limitPeriod ==
					SocialActivityCounterDefinition.LIMIT_PERIOD_PERIOD) {

			int activityDay = SocialCounterPeriodUtil.getActivityDay();

			String[] periodParts = StringUtil.split(
				valueParts[0], StringPool.DASH);

			int startPeriod = GetterUtil.getInteger(periodParts[0]);
			int endPeriod = GetterUtil.getInteger(periodParts[1]);

			if ((activityDay >= startPeriod) && (activityDay <= endPeriod)) {
				return count;
			}
		}

		return 0;
	}

	@Override
	public void setCount(int limitPeriod, int count) {
		if (limitPeriod == SocialActivityCounterDefinition.LIMIT_PERIOD_DAY) {
			setValue(
				String.valueOf(SocialCounterPeriodUtil.getActivityDay()) +
					StringPool.SLASH + String.valueOf(count));
		}
		else if (limitPeriod ==
					SocialActivityCounterDefinition.LIMIT_PERIOD_LIFETIME) {

			setValue(String.valueOf(count));
		}
		else if (limitPeriod ==
					SocialActivityCounterDefinition.LIMIT_PERIOD_PERIOD) {

			StringBundler sb = new StringBundler(5);

			sb.append(SocialCounterPeriodUtil.getStartPeriod());
			sb.append(StringPool.DASH);
			sb.append(SocialCounterPeriodUtil.getEndPeriod());
			sb.append(StringPool.SLASH);
			sb.append(count);

			setValue(sb.toString());
		}
	}

}