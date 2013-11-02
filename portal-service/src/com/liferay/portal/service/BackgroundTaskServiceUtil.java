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

package com.liferay.portal.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the remote service utility for BackgroundTask. This utility wraps
 * {@link com.liferay.portal.service.impl.BackgroundTaskServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTaskService
 * @see com.liferay.portal.service.base.BackgroundTaskServiceBaseImpl
 * @see com.liferay.portal.service.impl.BackgroundTaskServiceImpl
 * @generated
 */
@ProviderType
public class BackgroundTaskServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.BackgroundTaskServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static int getBackgroundTasksCount(long groupId,
		java.lang.String taskExecutorClassName, java.lang.String completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getBackgroundTasksCount(groupId, taskExecutorClassName,
			completed);
	}

	public static java.lang.String getBackgroundTaskStatusJSON(
		long backgroundTaskId) {
		return getService().getBackgroundTaskStatusJSON(backgroundTaskId);
	}

	public static BackgroundTaskService getService() {
		if (_service == null) {
			_service = (BackgroundTaskService)PortalBeanLocatorUtil.locate(BackgroundTaskService.class.getName());

			ReferenceRegistry.registerReference(BackgroundTaskServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setService(BackgroundTaskService service) {
	}

	private static BackgroundTaskService _service;
}