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

package com.liferay.portal.kernel.notifications;

import java.util.Comparator;

/**
 * @author Edward Han
 */
public class NotificationEventComparator
	implements Comparator<NotificationEvent> {

	public NotificationEventComparator() {
		this(true);
	}

	public NotificationEventComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		NotificationEvent notificationEvent1,
		NotificationEvent notificationEvent2) {

		if (notificationEvent1.equals(notificationEvent2)) {
			return 0;
		}

		long value =
			notificationEvent1.getDeliverBy() -
				notificationEvent2.getDeliverBy();

		if (value == 0) {
			value =
				notificationEvent1.getTimestamp() -
					notificationEvent2.getTimestamp();
		}

		if (value == 0) {
			value =
				notificationEvent1.hashCode() - notificationEvent2.hashCode();
		}

		if (_ascending) {
			return (int)value;
		}
		else {
			return (int)-value;
		}
	}

	private boolean _ascending;

}