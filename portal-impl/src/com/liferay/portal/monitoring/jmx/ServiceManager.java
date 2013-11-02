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

import com.liferay.portal.monitoring.statistics.service.ServerStatistics;
import com.liferay.portal.monitoring.statistics.service.ServiceMonitorAdvice;

import java.util.Set;

/**
 * @author Michael C. Han
 */
public class ServiceManager implements ServiceManagerMBean {

	@Override
	public void addMonitoredClass(String className) {
		_serviceMonitorAdvice.addMonitoredClass(className);
	}

	@Override
	public void addMonitoredMethod(
		String className, String methodName, String[] parameterTypes) {

		_serviceMonitorAdvice.addMonitoredMethod(
			className, methodName, parameterTypes);
	}

	@Override
	public long getErrorCount(
		String className, String methodName, String[] parameterTypes) {

		return _serverStatistics.getErrorCount(
			className, methodName, parameterTypes);
	}

	@Override
	public long getMaxTime(
		String className, String methodName, String[] parameterTypes) {

		return _serverStatistics.getMaxTime(
			className, methodName, parameterTypes);
	}

	@Override
	public long getMinTime(
		String className, String methodName, String[] parameterTypes) {

		return _serverStatistics.getMinTime(
			className, methodName, parameterTypes);
	}

	@Override
	public Set<String> getMonitoredClasses() {
		return _serviceMonitorAdvice.getMonitoredClasses();
	}

	@Override
	public Set<MethodSignature> getMonitoredMethods() {
		return _serviceMonitorAdvice.getMonitoredMethods();
	}

	@Override
	public long getRequestCount(
		String className, String methodName, String[] parameterTypes) {

		return _serverStatistics.getRequestCount(
			className, methodName, parameterTypes);
	}

	@Override
	public boolean isActive() {
		return ServiceMonitorAdvice.isActive();
	}

	@Override
	public boolean isPermissiveMode() {
		return _serviceMonitorAdvice.isPermissiveMode();
	}

	@Override
	public void setActive(boolean active) {
		_serviceMonitorAdvice.setActive(active);
	}

	@Override
	public void setPermissiveMode(boolean permissiveMode) {
		_serviceMonitorAdvice.setPermissiveMode(permissiveMode);
	}

	public void setServerStatistics(ServerStatistics serverStatistics) {
		_serverStatistics = serverStatistics;
	}

	public void setServiceMonitorAdvice(
		ServiceMonitorAdvice serviceMonitorAdvice) {

		_serviceMonitorAdvice = serviceMonitorAdvice;
	}

	private ServerStatistics _serverStatistics;
	private ServiceMonitorAdvice _serviceMonitorAdvice;

}