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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PortalUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class SystemEventImpl extends SystemEventBaseImpl {

	public SystemEventImpl() {
	}

	@Override
	public String getReferrerClassName() {
		long referrerClassNameId = getReferrerClassNameId();

		if (referrerClassNameId > 0) {
			return PortalUtil.getClassName(referrerClassNameId);
		}

		return StringPool.BLANK;
	}

	@Override
	public void setReferrerClassName(String referrerClassName) {
		long referrerClassNameId = 0;

		if (Validator.isNotNull(referrerClassName)) {
			referrerClassNameId = PortalUtil.getClassNameId(referrerClassName);
		}

		setReferrerClassNameId(referrerClassNameId);
	}

}