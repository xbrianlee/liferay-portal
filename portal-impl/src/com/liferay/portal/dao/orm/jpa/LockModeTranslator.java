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

package com.liferay.portal.dao.orm.jpa;

import com.liferay.portal.kernel.dao.orm.LockMode;

import javax.persistence.LockModeType;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class LockModeTranslator {

	public static LockModeType translate(LockMode lockMode) {
		if (lockMode == LockMode.READ) {
			return LockModeType.READ;
		}
		else if (lockMode == LockMode.WRITE) {
			return LockModeType.WRITE;
		}
		else {
			return null;
		}
	}

}