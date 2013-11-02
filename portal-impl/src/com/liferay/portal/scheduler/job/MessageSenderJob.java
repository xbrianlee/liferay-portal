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

package com.liferay.portal.scheduler.job;

import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterRequest;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.scheduler.JobState;
import com.liferay.portal.kernel.scheduler.JobStateSerializeUtil;
import com.liferay.portal.kernel.scheduler.SchedulerEngine;
import com.liferay.portal.kernel.scheduler.SchedulerEngineHelperUtil;
import com.liferay.portal.kernel.scheduler.StorageType;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.util.PropsValues;

import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;

/**
 * @author Michael C. Han
 * @author Bruno Farache
 */
public class MessageSenderJob implements Job {

	@Override
	public void execute(JobExecutionContext jobExecutionContext) {
		try {
			doExecute(jobExecutionContext);
		}
		catch (Exception e) {
			_log.error("Unable to execute job", e);
		}
	}

	protected void doExecute(JobExecutionContext jobExecutionContext)
		throws Exception {

		JobDetail jobDetail = jobExecutionContext.getJobDetail();

		JobDataMap jobDataMap = jobDetail.getJobDataMap();

		String destinationName = jobDataMap.getString(
			SchedulerEngine.DESTINATION_NAME);

		String messageJSON = (String)jobDataMap.get(SchedulerEngine.MESSAGE);

		Message message = null;

		if (messageJSON == null) {
			message = new Message();
		}
		else {
			message = (Message)JSONFactoryUtil.deserialize(messageJSON);
		}

		message.put(SchedulerEngine.DESTINATION_NAME, destinationName);

		Map<String, Object> jobStateMap = (Map<String, Object>)jobDataMap.get(
			SchedulerEngine.JOB_STATE);

		JobState jobState = JobStateSerializeUtil.deserialize(jobStateMap);

		JobKey jobKey = jobDetail.getKey();

		if (jobExecutionContext.getNextFireTime() == null) {
			message.put(SchedulerEngine.DISABLE, true);

			StorageType storageType = StorageType.valueOf(
				jobDataMap.getString(SchedulerEngine.STORAGE_TYPE));

			if (PropsValues.CLUSTER_LINK_ENABLED &&
				storageType.equals(StorageType.MEMORY_CLUSTERED)) {

				notifyClusterMember(jobKey, storageType);
			}

			if (storageType.equals(StorageType.PERSISTED)) {
				Scheduler scheduler = jobExecutionContext.getScheduler();

				scheduler.deleteJob(jobKey);
			}
		}

		message.put(SchedulerEngine.JOB_NAME, jobKey.getName());
		message.put(SchedulerEngine.JOB_STATE, jobState);
		message.put(SchedulerEngine.GROUP_NAME, jobKey.getGroup());

		MessageBusUtil.sendMessage(destinationName, message);
	}

	protected void notifyClusterMember(JobKey jobKey, StorageType storageType)
		throws Exception {

		MethodHandler methodHandler = new MethodHandler(
			_deleteJobMethodKey, jobKey.getName(), jobKey.getGroup(),
			storageType);

		ClusterRequest clusterRequest = ClusterRequest.createMulticastRequest(
			methodHandler, true);

		ClusterExecutorUtil.execute(clusterRequest);
	}

	private static Log _log = LogFactoryUtil.getLog(MessageSenderJob.class);

	private static MethodKey _deleteJobMethodKey = new MethodKey(
		SchedulerEngineHelperUtil.class, "delete", String.class, String.class,
		StorageType.class);

}