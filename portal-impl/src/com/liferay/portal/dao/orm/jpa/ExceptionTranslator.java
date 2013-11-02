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

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.ObjectNotFoundException;

import javax.persistence.EntityNotFoundException;

/**
 * @author Prashant Dighe
 * @author Brian Wing Shun Chan
 */
public class ExceptionTranslator {

	public static ORMException translate(Exception e) {
		if (e instanceof EntityNotFoundException) {
			return new ObjectNotFoundException(e.getMessage());
		}

		String message = null;

		if (e.getCause() != null) {
			message = e.getMessage() + " - " + e.getCause().getMessage();
		}
		else {
			message = e.getMessage();
		}

		return new ORMException(message);
	}

}