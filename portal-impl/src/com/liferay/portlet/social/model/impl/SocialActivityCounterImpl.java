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

import com.liferay.portlet.social.model.SocialActivityCounterConstants;
import com.liferay.portlet.social.util.SocialCounterPeriodUtil;

/**
 * @author Zsolt Berentey
 */
public class SocialActivityCounterImpl extends SocialActivityCounterBaseImpl {

	@Override
	public boolean isActivePeriod(int periodLength) {
		if (periodLength ==
				SocialActivityCounterConstants.PERIOD_LENGTH_INFINITE) {

			return true;
		}

		if (periodLength !=
				SocialActivityCounterConstants.PERIOD_LENGTH_SYSTEM) {

			if ((getStartPeriod() + periodLength) >
					SocialCounterPeriodUtil.getActivityDay()) {

				return true;
			}
		}

		if ((getStartPeriod() == SocialCounterPeriodUtil.getStartPeriod()) &&
			((getEndPeriod() == -1) ||
			 (getEndPeriod() == SocialCounterPeriodUtil.getEndPeriod()))) {

			return true;
		}

		return false;
	}

}