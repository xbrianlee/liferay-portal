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

package com.liferay.portal.kernel.util;

import java.util.Date;

/**
 * @author Eduardo Garcia
 */
public class DateRange {

	public DateRange(Date startDate, Date endDate) {
		_startDate = startDate;
		_endDate = endDate;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	private Date _endDate;
	private Date _startDate;

}