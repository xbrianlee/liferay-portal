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

package com.liferay.portlet.messageboards.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.messageboards.model.MBDiscussion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MBDiscussion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBDiscussion
 * @generated
 */
public class MBDiscussionCacheModel implements CacheModel<MBDiscussion>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", discussionId=");
		sb.append(discussionId);
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
		sb.append(", threadId=");
		sb.append(threadId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MBDiscussion toEntityModel() {
		MBDiscussionImpl mbDiscussionImpl = new MBDiscussionImpl();

		if (uuid == null) {
			mbDiscussionImpl.setUuid(StringPool.BLANK);
		}
		else {
			mbDiscussionImpl.setUuid(uuid);
		}

		mbDiscussionImpl.setDiscussionId(discussionId);
		mbDiscussionImpl.setGroupId(groupId);
		mbDiscussionImpl.setCompanyId(companyId);
		mbDiscussionImpl.setUserId(userId);

		if (userName == null) {
			mbDiscussionImpl.setUserName(StringPool.BLANK);
		}
		else {
			mbDiscussionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			mbDiscussionImpl.setCreateDate(null);
		}
		else {
			mbDiscussionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			mbDiscussionImpl.setModifiedDate(null);
		}
		else {
			mbDiscussionImpl.setModifiedDate(new Date(modifiedDate));
		}

		mbDiscussionImpl.setClassNameId(classNameId);
		mbDiscussionImpl.setClassPK(classPK);
		mbDiscussionImpl.setThreadId(threadId);

		mbDiscussionImpl.resetOriginalValues();

		return mbDiscussionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		discussionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		threadId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(discussionId);
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
		objectOutput.writeLong(threadId);
	}

	public String uuid;
	public long discussionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public long threadId;
}