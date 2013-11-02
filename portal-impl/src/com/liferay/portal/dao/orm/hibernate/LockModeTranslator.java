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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.LockMode;

/**
 * @author Brian Wing Shun Chan
 */
public class LockModeTranslator {

	public static org.hibernate.LockMode translate(LockMode lockMode) {
		if (lockMode == LockMode.FORCE) {
			return org.hibernate.LockMode.PESSIMISTIC_FORCE_INCREMENT;
		}
		else if (lockMode == LockMode.NONE) {
			return org.hibernate.LockMode.NONE;
		}
		else if (lockMode == LockMode.OPTIMISTIC) {
			return org.hibernate.LockMode.OPTIMISTIC;
		}
		else if (lockMode == LockMode.OPTIMISTIC_FORCE_INCREMENT) {
			return org.hibernate.LockMode.OPTIMISTIC_FORCE_INCREMENT;
		}
		else if (lockMode == LockMode.PESSIMISTIC_FORCE_INCREMENT) {
			return org.hibernate.LockMode.PESSIMISTIC_FORCE_INCREMENT;
		}
		else if (lockMode == LockMode.PESSIMISTIC_READ) {
			return org.hibernate.LockMode.PESSIMISTIC_READ;
		}
		else if (lockMode == LockMode.PESSIMISTIC_WRITE) {
			return org.hibernate.LockMode.PESSIMISTIC_WRITE;
		}
		else if (lockMode == LockMode.READ) {
			return org.hibernate.LockMode.READ;
		}
		else if (lockMode == LockMode.UPGRADE) {
			return org.hibernate.LockMode.PESSIMISTIC_WRITE;
		}
		else if (lockMode == LockMode.UPGRADE_NOWAIT) {
			return org.hibernate.LockMode.UPGRADE_NOWAIT;
		}
		else if (lockMode == LockMode.WRITE) {
			return org.hibernate.LockMode.WRITE;
		}
		else {
			return org.hibernate.LockMode.parse(lockMode.toString());
		}
	}

}