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

package com.liferay.portlet.trash.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.trash.model.TrashVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TrashVersion in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TrashVersion
 * @generated
 */
public class TrashVersionCacheModel implements CacheModel<TrashVersion>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{versionId=");
		sb.append(versionId);
		sb.append(", entryId=");
		sb.append(entryId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", typeSettings=");
		sb.append(typeSettings);
		sb.append(", status=");
		sb.append(status);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public TrashVersion toEntityModel() {
		TrashVersionImpl trashVersionImpl = new TrashVersionImpl();

		trashVersionImpl.setVersionId(versionId);
		trashVersionImpl.setEntryId(entryId);
		trashVersionImpl.setClassNameId(classNameId);
		trashVersionImpl.setClassPK(classPK);

		if (typeSettings == null) {
			trashVersionImpl.setTypeSettings(StringPool.BLANK);
		}
		else {
			trashVersionImpl.setTypeSettings(typeSettings);
		}

		trashVersionImpl.setStatus(status);

		trashVersionImpl.resetOriginalValues();

		return trashVersionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		versionId = objectInput.readLong();
		entryId = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		typeSettings = objectInput.readUTF();
		status = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(versionId);
		objectOutput.writeLong(entryId);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (typeSettings == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(typeSettings);
		}

		objectOutput.writeInt(status);
	}

	public long versionId;
	public long entryId;
	public long classNameId;
	public long classPK;
	public String typeSettings;
	public int status;
}