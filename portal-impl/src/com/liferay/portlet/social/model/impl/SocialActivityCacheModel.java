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

import com.liferay.portlet.social.model.SocialActivity;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SocialActivity in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivity
 * @generated
 */
public class SocialActivityCacheModel implements CacheModel<SocialActivity>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{activityId=");
		sb.append(activityId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", activitySetId=");
		sb.append(activitySetId);
		sb.append(", mirrorActivityId=");
		sb.append(mirrorActivityId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", parentClassNameId=");
		sb.append(parentClassNameId);
		sb.append(", parentClassPK=");
		sb.append(parentClassPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append(", receiverUserId=");
		sb.append(receiverUserId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SocialActivity toEntityModel() {
		SocialActivityImpl socialActivityImpl = new SocialActivityImpl();

		socialActivityImpl.setActivityId(activityId);
		socialActivityImpl.setGroupId(groupId);
		socialActivityImpl.setCompanyId(companyId);
		socialActivityImpl.setUserId(userId);
		socialActivityImpl.setCreateDate(createDate);
		socialActivityImpl.setActivitySetId(activitySetId);
		socialActivityImpl.setMirrorActivityId(mirrorActivityId);
		socialActivityImpl.setClassNameId(classNameId);
		socialActivityImpl.setClassPK(classPK);
		socialActivityImpl.setParentClassNameId(parentClassNameId);
		socialActivityImpl.setParentClassPK(parentClassPK);
		socialActivityImpl.setType(type);

		if (extraData == null) {
			socialActivityImpl.setExtraData(StringPool.BLANK);
		}
		else {
			socialActivityImpl.setExtraData(extraData);
		}

		socialActivityImpl.setReceiverUserId(receiverUserId);

		socialActivityImpl.resetOriginalValues();

		return socialActivityImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		activityId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		activitySetId = objectInput.readLong();
		mirrorActivityId = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		parentClassNameId = objectInput.readLong();
		parentClassPK = objectInput.readLong();
		type = objectInput.readInt();
		extraData = objectInput.readUTF();
		receiverUserId = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(activityId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(activitySetId);
		objectOutput.writeLong(mirrorActivityId);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);
		objectOutput.writeLong(parentClassNameId);
		objectOutput.writeLong(parentClassPK);
		objectOutput.writeInt(type);

		if (extraData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(extraData);
		}

		objectOutput.writeLong(receiverUserId);
	}

	public long activityId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long activitySetId;
	public long mirrorActivityId;
	public long classNameId;
	public long classPK;
	public long parentClassNameId;
	public long parentClassPK;
	public int type;
	public String extraData;
	public long receiverUserId;
}