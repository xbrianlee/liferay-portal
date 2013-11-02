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

package com.liferay.portal.monitoring.jmx;

import com.liferay.portal.kernel.exception.SystemException;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public interface ServiceManagerMBean {

	public void addMonitoredClass(String className);

	public void addMonitoredMethod(
			String className, String methodName, String[] parameterTypes)
		throws SystemException;

	public long getErrorCount(
			String className, String methodName, String[] parameterTypes)
		throws SystemException;

	public long getMaxTime(
			String className, String methodName, String[] parameterTypes)
		throws SystemException;

	public long getMinTime(
			String className, String methodName, String[] parameterTypes)
		throws SystemException;

	public Set<String> getMonitoredClasses();

	public Set<MethodSignature> getMonitoredMethods();

	public long getRequestCount(
			String className, String methodName, String[] parameterTypes)
		throws SystemException;

	public boolean isActive();

	public boolean isPermissiveMode();

	public void setActive(boolean active);

	public void setPermissiveMode(boolean permissiveMode);

}