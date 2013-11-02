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

package com.liferay.portal.kernel.scheduler;

import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.scheduler.messaging.SchedulerResponse;
import com.liferay.portal.kernel.util.ObjectValuePair;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.portlet.PortletRequest;

/**
 * @author Michael C. Han
 */
public interface SchedulerEngineHelper {

	public void addJob(
			Trigger trigger, StorageType storageType, String description,
			String destinationName, Message message,
			String messageListenerClassName, String portletId,
			int exceptionsMaxSize)
		throws SchedulerException;

	public void addJob(
			Trigger trigger, StorageType storageType, String description,
			String destinationName, Object payload,
			String messageListenerClassName, String portletId,
			int exceptionsMaxSize)
		throws SchedulerException;

	public void addScriptingJob(
			Trigger trigger, StorageType storageType, String description,
			String language, String script, int exceptionsMaxSize)
		throws SchedulerException;

	public void auditSchedulerJobs(Message message, TriggerState triggerState)
		throws SchedulerException;

	public void delete(SchedulerEntry schedulerEntry, StorageType storageType)
		throws SchedulerException;

	public void delete(String groupName, StorageType storageType)
		throws SchedulerException;

	public void delete(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public String getCronText(Calendar calendar, boolean timeZoneSensitive);

	public String getCronText(
		PortletRequest portletRequest, Calendar calendar,
		boolean timeZoneSensitive, int recurrenceType);

	public Date getEndTime(SchedulerResponse schedulerResponse);

	public Date getEndTime(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public Date getFinalFireTime(SchedulerResponse schedulerResponse);

	public Date getFinalFireTime(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public ObjectValuePair<Exception, Date>[] getJobExceptions(
		SchedulerResponse schedulerResponse);

	public ObjectValuePair<Exception, Date>[] getJobExceptions(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public TriggerState getJobState(SchedulerResponse schedulerResponse);

	public TriggerState getJobState(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public Date getNextFireTime(SchedulerResponse schedulerResponse);

	public Date getNextFireTime(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public Date getPreviousFireTime(SchedulerResponse schedulerResponse);

	public Date getPreviousFireTime(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public SchedulerResponse getScheduledJob(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public List<SchedulerResponse> getScheduledJobs()
		throws SchedulerException;

	public List<SchedulerResponse> getScheduledJobs(StorageType storageType)
		throws SchedulerException;

	public List<SchedulerResponse> getScheduledJobs(
			String groupName, StorageType storageType)
		throws SchedulerException;

	public Date getStartTime(SchedulerResponse schedulerResponse);

	public Date getStartTime(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public void initialize() throws SchedulerException;

	public String namespaceGroupName(String groupName, StorageType storageType);

	public void pause(String groupName, StorageType storageType)
		throws SchedulerException;

	public void pause(String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public void resume(String groupName, StorageType storageType)
		throws SchedulerException;

	public void resume(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public void schedule(
			SchedulerEntry schedulerEntry, StorageType storageType,
			String portletId, int exceptionsMaxSize)
		throws SchedulerException;

	public void schedule(
			Trigger trigger, StorageType storageType, String description,
			String destinationName, Message message, int exceptionsMaxSize)
		throws SchedulerException;

	public void schedule(
			Trigger trigger, StorageType storageType, String description,
			String destinationName, Object payload, int exceptionsMaxSize)
		throws SchedulerException;

	public void shutdown() throws SchedulerException;

	public void start() throws SchedulerException;

	public void suppressError(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public void unschedule(
			SchedulerEntry schedulerEntry, StorageType storageType)
		throws SchedulerException;

	public void unschedule(String groupName, StorageType storageType)
		throws SchedulerException;

	public void unschedule(
			String jobName, String groupName, StorageType storageType)
		throws SchedulerException;

	public void update(
			String jobName, String groupName, StorageType storageType,
			String description, String language, String script,
			int exceptionsMaxSize)
		throws SchedulerException;

	public void update(Trigger trigger, StorageType storageType)
		throws SchedulerException;

	public void updateMemorySchedulerClusterMaster()
		throws SchedulerException;

}