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
import com.liferay.portal.model.UserTrackerPath;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing UserTrackerPath in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see UserTrackerPath
 * @generated
 */
public class UserTrackerPathCacheModel implements CacheModel<UserTrackerPath>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{userTrackerPathId=");
		sb.append(userTrackerPathId);
		sb.append(", userTrackerId=");
		sb.append(userTrackerId);
		sb.append(", path=");
		sb.append(path);
		sb.append(", pathDate=");
		sb.append(pathDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public UserTrackerPath toEntityModel() {
		UserTrackerPathImpl userTrackerPathImpl = new UserTrackerPathImpl();

		userTrackerPathImpl.setUserTrackerPathId(userTrackerPathId);
		userTrackerPathImpl.setUserTrackerId(userTrackerId);

		if (path == null) {
			userTrackerPathImpl.setPath(StringPool.BLANK);
		}
		else {
			userTrackerPathImpl.setPath(path);
		}

		if (pathDate == Long.MIN_VALUE) {
			userTrackerPathImpl.setPathDate(null);
		}
		else {
			userTrackerPathImpl.setPathDate(new Date(pathDate));
		}

		userTrackerPathImpl.resetOriginalValues();

		return userTrackerPathImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		userTrackerPathId = objectInput.readLong();
		userTrackerId = objectInput.readLong();
		path = objectInput.readUTF();
		pathDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(userTrackerPathId);
		objectOutput.writeLong(userTrackerId);

		if (path == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(path);
		}

		objectOutput.writeLong(pathDate);
	}

	public long userTrackerPathId;
	public long userTrackerId;
	public String path;
	public long pathDate;
}