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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BackgroundTask;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing BackgroundTask in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BackgroundTask
 * @generated
 */
public class BackgroundTaskCacheModel implements CacheModel<BackgroundTask>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(31);

		sb.append("{backgroundTaskId=");
		sb.append(backgroundTaskId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", servletContextNames=");
		sb.append(servletContextNames);
		sb.append(", taskExecutorClassName=");
		sb.append(taskExecutorClassName);
		sb.append(", taskContext=");
		sb.append(taskContext);
		sb.append(", completed=");
		sb.append(completed);
		sb.append(", completionDate=");
		sb.append(completionDate);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusMessage=");
		sb.append(statusMessage);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public BackgroundTask toEntityModel() {
		BackgroundTaskImpl backgroundTaskImpl = new BackgroundTaskImpl();

		backgroundTaskImpl.setBackgroundTaskId(backgroundTaskId);
		backgroundTaskImpl.setGroupId(groupId);
		backgroundTaskImpl.setCompanyId(companyId);
		backgroundTaskImpl.setUserId(userId);

		if (userName == null) {
			backgroundTaskImpl.setUserName(StringPool.BLANK);
		}
		else {
			backgroundTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			backgroundTaskImpl.setCreateDate(null);
		}
		else {
			backgroundTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			backgroundTaskImpl.setModifiedDate(null);
		}
		else {
			backgroundTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			backgroundTaskImpl.setName(StringPool.BLANK);
		}
		else {
			backgroundTaskImpl.setName(name);
		}

		if (servletContextNames == null) {
			backgroundTaskImpl.setServletContextNames(StringPool.BLANK);
		}
		else {
			backgroundTaskImpl.setServletContextNames(servletContextNames);
		}

		if (taskExecutorClassName == null) {
			backgroundTaskImpl.setTaskExecutorClassName(StringPool.BLANK);
		}
		else {
			backgroundTaskImpl.setTaskExecutorClassName(taskExecutorClassName);
		}

		if (taskContext == null) {
			backgroundTaskImpl.setTaskContext(StringPool.BLANK);
		}
		else {
			backgroundTaskImpl.setTaskContext(taskContext);
		}

		backgroundTaskImpl.setCompleted(completed);

		if (completionDate == Long.MIN_VALUE) {
			backgroundTaskImpl.setCompletionDate(null);
		}
		else {
			backgroundTaskImpl.setCompletionDate(new Date(completionDate));
		}

		backgroundTaskImpl.setStatus(status);

		if (statusMessage == null) {
			backgroundTaskImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			backgroundTaskImpl.setStatusMessage(statusMessage);
		}

		backgroundTaskImpl.resetOriginalValues();

		return backgroundTaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		backgroundTaskId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		servletContextNames = objectInput.readUTF();
		taskExecutorClassName = objectInput.readUTF();
		taskContext = objectInput.readUTF();
		completed = objectInput.readBoolean();
		completionDate = objectInput.readLong();
		status = objectInput.readInt();
		statusMessage = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(backgroundTaskId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (servletContextNames == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(servletContextNames);
		}

		if (taskExecutorClassName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(taskExecutorClassName);
		}

		if (taskContext == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(taskContext);
		}

		objectOutput.writeBoolean(completed);
		objectOutput.writeLong(completionDate);
		objectOutput.writeInt(status);

		if (statusMessage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(statusMessage);
		}
	}

	public long backgroundTaskId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String servletContextNames;
	public String taskExecutorClassName;
	public String taskContext;
	public boolean completed;
	public long completionDate;
	public int status;
	public String statusMessage;
}