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

package com.liferay.portlet.social.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.social.model.SocialActivityAchievement;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SocialActivityAchievement in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityAchievement
 * @generated
 */
public class SocialActivityAchievementCacheModel implements CacheModel<SocialActivityAchievement>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{activityAchievementId=");
		sb.append(activityAchievementId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", firstInGroup=");
		sb.append(firstInGroup);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SocialActivityAchievement toEntityModel() {
		SocialActivityAchievementImpl socialActivityAchievementImpl = new SocialActivityAchievementImpl();

		socialActivityAchievementImpl.setActivityAchievementId(activityAchievementId);
		socialActivityAchievementImpl.setGroupId(groupId);
		socialActivityAchievementImpl.setCompanyId(companyId);
		socialActivityAchievementImpl.setUserId(userId);
		socialActivityAchievementImpl.setCreateDate(createDate);

		if (name == null) {
			socialActivityAchievementImpl.setName(StringPool.BLANK);
		}
		else {
			socialActivityAchievementImpl.setName(name);
		}

		socialActivityAchievementImpl.setFirstInGroup(firstInGroup);

		socialActivityAchievementImpl.resetOriginalValues();

		return socialActivityAchievementImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		activityAchievementId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		name = objectInput.readUTF();
		firstInGroup = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(activityAchievementId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeBoolean(firstInGroup);
	}

	public long activityAchievementId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public String name;
	public boolean firstInGroup;
}