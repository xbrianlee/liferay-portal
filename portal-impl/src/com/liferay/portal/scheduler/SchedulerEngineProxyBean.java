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

package com.liferay.portal.scheduler;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.Trigger;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;

import java.util.List;

/**
 * @author Tina Tian
 */
public class SchedulerEngineProxyBean
	extends BaseProxyBean implements SchedulerEngine {

	@Override
	public void delete(String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(String jobName, String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SchedulerResponse getScheduledJob(String jobName, String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SchedulerResponse> getScheduledJobs() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<SchedulerResponse> getScheduledJobs(String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void pause(String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void pause(String jobName, String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resume(String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void resume(String jobName, String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void schedule(
		Trigger trigger, String description, String destinationName,
		Message message) {

		throw new UnsupportedOperationException();
	}

	@Override
	public void shutdown() {
	}

	@Override
	public void start() {
	}

	@Override
	public void suppressError(String jobName, String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unschedule(String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void unschedule(String jobName, String groupName) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Trigger trigger) {
		throw new UnsupportedOperationException();
	}

}