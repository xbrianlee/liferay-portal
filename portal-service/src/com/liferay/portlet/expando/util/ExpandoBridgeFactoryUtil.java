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

package com.liferay.portlet.expando.util;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.security.auth.CompanyThreadLocal;
import com.liferay.portlet.expando.model.ExpandoBridge;

/**
 * @author Brian Wing Shun Chan
 */
public class ExpandoBridgeFactoryUtil {

	public static ExpandoBridge getExpandoBridge(
		long companyId, String className) {

		PortalRuntimePermission.checkExpandoBridge(className);

		return getExpandoBridgeFactory().getExpandoBridge(companyId, className);
	}

	public static ExpandoBridge getExpandoBridge(
		long companyId, String className, long classPK) {

		PortalRuntimePermission.checkExpandoBridge(className);

		return getExpandoBridgeFactory().getExpandoBridge(
			companyId, className, classPK);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getExpandoBridge(long,
	 *             String)}
	 */
	public static ExpandoBridge getExpandoBridge(String className) {
		long companyId = CompanyThreadLocal.getCompanyId();

		return getExpandoBridge(companyId, className);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getExpandoBridge(long,
	 *             String, long)}
	 */
	public static ExpandoBridge getExpandoBridge(
		String className, long classPK) {

		long companyId = CompanyThreadLocal.getCompanyId();

		return getExpandoBridge(companyId, className, classPK);
	}

	public static ExpandoBridgeFactory getExpandoBridgeFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ExpandoBridgeFactoryUtil.class);

		return _expandoBridgeFactory;
	}

	public void setExpandoBridgeFactory(
		ExpandoBridgeFactory expandoBridgeFactory) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_expandoBridgeFactory = expandoBridgeFactory;
	}

	private static ExpandoBridgeFactory _expandoBridgeFactory;

}