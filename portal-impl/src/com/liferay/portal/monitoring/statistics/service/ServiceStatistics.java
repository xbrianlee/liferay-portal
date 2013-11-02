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

package com.liferay.portal.monitoring.statistics.service;

import com.liferay.portal.kernel.monitoring.RequestStatus;
import com.liferay.portal.kernel.monitoring.statistics.DataSampleProcessor;
import com.liferay.portal.kernel.monitoring.statistics.RequestStatistics;
import com.liferay.portal.monitoring.jmx.MethodSignature;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael C. Han
 */
public class ServiceStatistics
	implements DataSampleProcessor<ServiceRequestDataSample> {

	public ServiceStatistics(String className) {
		_className = className;
	}

	public long getAverageTime(String methodName, String[] parameterTypes) {
		MethodSignature methodSignature = new MethodSignature(
			_className, methodName, parameterTypes);

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodSignature);

		if (requestStatistics != null) {
			return requestStatistics.getAverageTime();
		}

		return -1;
	}

	public long getErrorCount(String methodName, String[] parameterTypes) {
		MethodSignature methodSignature = new MethodSignature(
			_className, methodName, parameterTypes);

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodSignature);

		if (requestStatistics != null) {
			return requestStatistics.getErrorCount();
		}

		return -1;
	}

	public long getMaxTime(String methodName, String[] parameterTypes) {
		MethodSignature methodSignature = new MethodSignature(
			_className, methodName, parameterTypes);

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodSignature);

		if (requestStatistics != null) {
			return requestStatistics.getMaxTime();
		}

		return -1;
	}

	public long getMinTime(String methodName, String[] parameterTypes) {
		MethodSignature methodSignature = new MethodSignature(
			_className, methodName, parameterTypes);

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodSignature);

		if (requestStatistics != null) {
			return requestStatistics.getMinTime();
		}

		return -1;
	}

	public long getRequestCount(String methodName, String[] parameterTypes) {
		MethodSignature methodSignature = new MethodSignature(
			_className, methodName, parameterTypes);

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodSignature);

		if (requestStatistics != null) {
			return requestStatistics.getRequestCount();
		}

		return -1;
	}

	@Override
	public void processDataSample(
		ServiceRequestDataSample serviceRequestDataSample) {

		MethodSignature methodSignature =
			serviceRequestDataSample.getMethodSignature();

		RequestStatistics requestStatistics = _methodRequestStatistics.get(
			methodSignature);

		if (requestStatistics == null) {
			requestStatistics = new RequestStatistics(
				methodSignature.toString());

			_methodRequestStatistics.put(methodSignature, requestStatistics);
		}

		RequestStatus requestStatus =
			serviceRequestDataSample.getRequestStatus();

		if (requestStatus == RequestStatus.ERROR) {
			requestStatistics.incrementError();
		}
		else if (requestStatus == RequestStatus.TIMEOUT) {
			requestStatistics.incrementTimeout();
		}
		else if (requestStatus == RequestStatus.SUCCESS) {
			requestStatistics.incrementSuccessDuration(
				serviceRequestDataSample.getDuration());
		}
	}

	private String _className;
	private Map<MethodSignature, RequestStatistics> _methodRequestStatistics =
		new ConcurrentHashMap<MethodSignature, RequestStatistics>();

}