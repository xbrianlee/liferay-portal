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

package com.liferay.portlet.calendar.model.impl;

import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class CalEventImpl extends CalEventBaseImpl {

	public CalEventImpl() {
	}

	@Override
	public TZSRecurrence getRecurrenceObj() {
		if (_recurrenceObj == null) {
			String recurrence = getRecurrence();

			if (Validator.isNotNull(recurrence)) {
				_recurrenceObj = (TZSRecurrence)JSONFactoryUtil.deserialize(
					recurrence);
			}
		}

		return _recurrenceObj;
	}

	@Override
	public void setRecurrence(String recurrence) {
		_recurrenceObj = null;

		super.setRecurrence(recurrence);
	}

	@Override
	public void setRecurrenceObj(TZSRecurrence recurrenceObj) {
		_recurrenceObj = recurrenceObj;

		super.setRecurrence(JSONFactoryUtil.serialize(recurrenceObj));
	}

	private TZSRecurrence _recurrenceObj = null;

}