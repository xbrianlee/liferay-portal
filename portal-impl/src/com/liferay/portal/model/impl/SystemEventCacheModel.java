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
import com.liferay.portal.model.SystemEvent;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing SystemEvent in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SystemEvent
 * @generated
 */
public class SystemEventCacheModel implements CacheModel<SystemEvent>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{systemEventId=");
		sb.append(systemEventId);
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
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", classUuid=");
		sb.append(classUuid);
		sb.append(", referrerClassNameId=");
		sb.append(referrerClassNameId);
		sb.append(", parentSystemEventId=");
		sb.append(parentSystemEventId);
		sb.append(", systemEventSetKey=");
		sb.append(systemEventSetKey);
		sb.append(", type=");
		sb.append(type);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SystemEvent toEntityModel() {
		SystemEventImpl systemEventImpl = new SystemEventImpl();

		systemEventImpl.setSystemEventId(systemEventId);
		systemEventImpl.setGroupId(groupId);
		systemEventImpl.setCompanyId(companyId);
		systemEventImpl.setUserId(userId);

		if (userName == null) {
			systemEventImpl.setUserName(StringPool.BLANK);
		}
		else {
			systemEventImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			systemEventImpl.setCreateDate(null);
		}
		else {
			systemEventImpl.setCreateDate(new Date(createDate));
		}

		systemEventImpl.setClassNameId(classNameId);
		systemEventImpl.setClassPK(classPK);

		if (classUuid == null) {
			systemEventImpl.setClassUuid(StringPool.BLANK);
		}
		else {
			systemEventImpl.setClassUuid(classUuid);
		}

		systemEventImpl.setReferrerClassNameId(referrerClassNameId);
		systemEventImpl.setParentSystemEventId(parentSystemEventId);
		systemEventImpl.setSystemEventSetKey(systemEventSetKey);
		systemEventImpl.setType(type);

		if (extraData == null) {
			systemEventImpl.setExtraData(StringPool.BLANK);
		}
		else {
			systemEventImpl.setExtraData(extraData);
		}

		systemEventImpl.resetOriginalValues();

		return systemEventImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		systemEventId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		classUuid = objectInput.readUTF();
		referrerClassNameId = objectInput.readLong();
		parentSystemEventId = objectInput.readLong();
		systemEventSetKey = objectInput.readLong();
		type = objectInput.readInt();
		extraData = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(systemEventId);
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
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (classUuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(classUuid);
		}

		objectOutput.writeLong(referrerClassNameId);
		objectOutput.writeLong(parentSystemEventId);
		objectOutput.writeLong(systemEventSetKey);
		objectOutput.writeInt(type);

		if (extraData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(extraData);
		}
	}

	public long systemEventId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long classNameId;
	public long classPK;
	public String classUuid;
	public long referrerClassNameId;
	public long parentSystemEventId;
	public long systemEventSetKey;
	public int type;
	public String extraData;
}