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

package com.liferay.portlet.polls.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.polls.model.PollsChoice;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing PollsChoice in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PollsChoice
 * @generated
 */
public class PollsChoiceCacheModel implements CacheModel<PollsChoice>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", choiceId=");
		sb.append(choiceId);
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
		sb.append(", questionId=");
		sb.append(questionId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PollsChoice toEntityModel() {
		PollsChoiceImpl pollsChoiceImpl = new PollsChoiceImpl();

		if (uuid == null) {
			pollsChoiceImpl.setUuid(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setUuid(uuid);
		}

		pollsChoiceImpl.setChoiceId(choiceId);
		pollsChoiceImpl.setGroupId(groupId);
		pollsChoiceImpl.setCompanyId(companyId);
		pollsChoiceImpl.setUserId(userId);

		if (userName == null) {
			pollsChoiceImpl.setUserName(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			pollsChoiceImpl.setCreateDate(null);
		}
		else {
			pollsChoiceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			pollsChoiceImpl.setModifiedDate(null);
		}
		else {
			pollsChoiceImpl.setModifiedDate(new Date(modifiedDate));
		}

		pollsChoiceImpl.setQuestionId(questionId);

		if (name == null) {
			pollsChoiceImpl.setName(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setName(name);
		}

		if (description == null) {
			pollsChoiceImpl.setDescription(StringPool.BLANK);
		}
		else {
			pollsChoiceImpl.setDescription(description);
		}

		pollsChoiceImpl.resetOriginalValues();

		return pollsChoiceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		choiceId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		questionId = objectInput.readLong();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
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

		objectOutput.writeLong(choiceId);
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
		objectOutput.writeLong(questionId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}
	}

	public String uuid;
	public long choiceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long questionId;
	public String name;
	public String description;
}