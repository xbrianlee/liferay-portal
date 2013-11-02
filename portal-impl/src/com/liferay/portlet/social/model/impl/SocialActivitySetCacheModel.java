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

import com.liferay.portlet.social.model.SocialActivitySet;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SocialActivitySet in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivitySet
 * @generated
 */
public class SocialActivitySetCacheModel implements CacheModel<SocialActivitySet>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{activitySetId=");
		sb.append(activitySetId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", type=");
		sb.append(type);
		sb.append(", extraData=");
		sb.append(extraData);
		sb.append(", activityCount=");
		sb.append(activityCount);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SocialActivitySet toEntityModel() {
		SocialActivitySetImpl socialActivitySetImpl = new SocialActivitySetImpl();

		socialActivitySetImpl.setActivitySetId(activitySetId);
		socialActivitySetImpl.setGroupId(groupId);
		socialActivitySetImpl.setCompanyId(companyId);
		socialActivitySetImpl.setUserId(userId);
		socialActivitySetImpl.setCreateDate(createDate);
		socialActivitySetImpl.setModifiedDate(modifiedDate);
		socialActivitySetImpl.setClassNameId(classNameId);
		socialActivitySetImpl.setClassPK(classPK);
		socialActivitySetImpl.setType(type);

		if (extraData == null) {
			socialActivitySetImpl.setExtraData(StringPool.BLANK);
		}
		else {
			socialActivitySetImpl.setExtraData(extraData);
		}

		socialActivitySetImpl.setActivityCount(activityCount);

		socialActivitySetImpl.resetOriginalValues();

		return socialActivitySetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		activitySetId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		type = objectInput.readInt();
		extraData = objectInput.readUTF();
		activityCount = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(activitySetId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(userId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);
		objectOutput.writeInt(type);

		if (extraData == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(extraData);
		}

		objectOutput.writeInt(activityCount);
	}

	public long activitySetId;
	public long groupId;
	public long companyId;
	public long userId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public int type;
	public String extraData;
	public int activityCount;
}