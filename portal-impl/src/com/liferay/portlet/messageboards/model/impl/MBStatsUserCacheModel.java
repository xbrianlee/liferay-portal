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
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.messageboards.model.MBStatsUser;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing MBStatsUser in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see MBStatsUser
 * @generated
 */
public class MBStatsUserCacheModel implements CacheModel<MBStatsUser>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{statsUserId=");
		sb.append(statsUserId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", messageCount=");
		sb.append(messageCount);
		sb.append(", lastPostDate=");
		sb.append(lastPostDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public MBStatsUser toEntityModel() {
		MBStatsUserImpl mbStatsUserImpl = new MBStatsUserImpl();

		mbStatsUserImpl.setStatsUserId(statsUserId);
		mbStatsUserImpl.setGroupId(groupId);
		mbStatsUserImpl.setUserId(userId);
		mbStatsUserImpl.setMessageCount(messageCount);

		if (lastPostDate == Long.MIN_VALUE) {
			mbStatsUserImpl.setLastPostDate(null);
		}
		else {
			mbStatsUserImpl.setLastPostDate(new Date(lastPostDate));
		}

		mbStatsUserImpl.resetOriginalValues();

		return mbStatsUserImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		statsUserId = objectInput.readLong();
		groupId = objectInput.readLong();
		userId = objectInput.readLong();
		messageCount = objectInput.readInt();
		lastPostDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(statsUserId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(userId);
		objectOutput.writeInt(messageCount);
		objectOutput.writeLong(lastPostDate);
	}

	public long statsUserId;
	public long groupId;
	public long userId;
	public int messageCount;
	public long lastPostDate;
}