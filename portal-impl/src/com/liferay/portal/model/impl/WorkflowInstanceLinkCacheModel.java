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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.WorkflowInstanceLink;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorkflowInstanceLink in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WorkflowInstanceLink
 * @generated
 */
public class WorkflowInstanceLinkCacheModel implements CacheModel<WorkflowInstanceLink>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{workflowInstanceLinkId=");
		sb.append(workflowInstanceLinkId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", workflowInstanceId=");
		sb.append(workflowInstanceId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorkflowInstanceLink toEntityModel() {
		WorkflowInstanceLinkImpl workflowInstanceLinkImpl = new WorkflowInstanceLinkImpl();

		workflowInstanceLinkImpl.setWorkflowInstanceLinkId(workflowInstanceLinkId);
		workflowInstanceLinkImpl.setGroupId(groupId);
		workflowInstanceLinkImpl.setCompanyId(companyId);
		workflowInstanceLinkImpl.setUserId(userId);

		if (userName == null) {
			workflowInstanceLinkImpl.setUserName(StringPool.BLANK);
		}
		else {
			workflowInstanceLinkImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			workflowInstanceLinkImpl.setCreateDate(null);
		}
		else {
			workflowInstanceLinkImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			workflowInstanceLinkImpl.setModifiedDate(null);
		}
		else {
			workflowInstanceLinkImpl.setModifiedDate(new Date(modifiedDate));
		}

		workflowInstanceLinkImpl.setClassNameId(classNameId);
		workflowInstanceLinkImpl.setClassPK(classPK);
		workflowInstanceLinkImpl.setWorkflowInstanceId(workflowInstanceId);

		workflowInstanceLinkImpl.resetOriginalValues();

		return workflowInstanceLinkImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		workflowInstanceLinkId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		workflowInstanceId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(workflowInstanceLinkId);
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
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);
		objectOutput.writeLong(workflowInstanceId);
	}

	public long workflowInstanceLinkId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long workflowInstanceId;
}