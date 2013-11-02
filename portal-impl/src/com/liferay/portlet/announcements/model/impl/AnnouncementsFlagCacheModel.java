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

package com.liferay.portlet.announcements.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.announcements.model.AnnouncementsFlag;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing AnnouncementsFlag in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see AnnouncementsFlag
 * @generated
 */
public class AnnouncementsFlagCacheModel implements CacheModel<AnnouncementsFlag>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{flagId=");
		sb.append(flagId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", value=");
		sb.append(value);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public AnnouncementsFlag toEntityModel() {
		AnnouncementsFlagImpl announcementsFlagImpl = new AnnouncementsFlagImpl();

		announcementsFlagImpl.setFlagId(flagId);
		announcementsFlagImpl.setUserId(userId);

		if (createDate == Long.MIN_VALUE) {
			announcementsFlagImpl.setCreateDate(null);
		}
		else {
			announcementsFlagImpl.setCreateDate(new Date(createDate));
		}

		announcementsFlagImpl.setEntryId(entryId);
		announcementsFlagImpl.setValue(value);

		announcementsFlagImpl.resetOriginalValues();

		return announcementsFlagImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		flagId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		entryId = objectInput.readLong();
		value = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(flagId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(entryId);
		objectOutput.writeInt(value);
	}

	public long flagId;
	public long userId;
	public long createDate;
	public long entryId;
	public int value;
}