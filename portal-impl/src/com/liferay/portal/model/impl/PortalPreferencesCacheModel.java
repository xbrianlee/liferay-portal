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
import com.liferay.portal.model.PortalPreferences;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing PortalPreferences in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see PortalPreferences
 * @generated
 */
public class PortalPreferencesCacheModel implements CacheModel<PortalPreferences>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{portalPreferencesId=");
		sb.append(portalPreferencesId);
		sb.append(", ownerId=");
		sb.append(ownerId);
		sb.append(", ownerType=");
		sb.append(ownerType);
		sb.append(", preferences=");
		sb.append(preferences);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public PortalPreferences toEntityModel() {
		PortalPreferencesImpl portalPreferencesImpl = new PortalPreferencesImpl();

		portalPreferencesImpl.setPortalPreferencesId(portalPreferencesId);
		portalPreferencesImpl.setOwnerId(ownerId);
		portalPreferencesImpl.setOwnerType(ownerType);

		if (preferences == null) {
			portalPreferencesImpl.setPreferences(StringPool.BLANK);
		}
		else {
			portalPreferencesImpl.setPreferences(preferences);
		}

		portalPreferencesImpl.resetOriginalValues();

		return portalPreferencesImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		portalPreferencesId = objectInput.readLong();
		ownerId = objectInput.readLong();
		ownerType = objectInput.readInt();
		preferences = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(portalPreferencesId);
		objectOutput.writeLong(ownerId);
		objectOutput.writeInt(ownerType);

		if (preferences == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(preferences);
		}
	}

	public long portalPreferencesId;
	public long ownerId;
	public int ownerType;
	public String preferences;
}