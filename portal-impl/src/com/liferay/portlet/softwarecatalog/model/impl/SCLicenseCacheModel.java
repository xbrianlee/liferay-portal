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

package com.liferay.portlet.softwarecatalog.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.portlet.softwarecatalog.model.SCLicense;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SCLicense in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SCLicense
 * @generated
 */
public class SCLicenseCacheModel implements CacheModel<SCLicense>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(13);

		sb.append("{licenseId=");
		sb.append(licenseId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", url=");
		sb.append(url);
		sb.append(", openSource=");
		sb.append(openSource);
		sb.append(", active=");
		sb.append(active);
		sb.append(", recommended=");
		sb.append(recommended);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SCLicense toEntityModel() {
		SCLicenseImpl scLicenseImpl = new SCLicenseImpl();

		scLicenseImpl.setLicenseId(licenseId);

		if (name == null) {
			scLicenseImpl.setName(StringPool.BLANK);
		}
		else {
			scLicenseImpl.setName(name);
		}

		if (url == null) {
			scLicenseImpl.setUrl(StringPool.BLANK);
		}
		else {
			scLicenseImpl.setUrl(url);
		}

		scLicenseImpl.setOpenSource(openSource);
		scLicenseImpl.setActive(active);
		scLicenseImpl.setRecommended(recommended);

		scLicenseImpl.resetOriginalValues();

		return scLicenseImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		licenseId = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		openSource = objectInput.readBoolean();
		active = objectInput.readBoolean();
		recommended = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(licenseId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		objectOutput.writeBoolean(openSource);
		objectOutput.writeBoolean(active);
		objectOutput.writeBoolean(recommended);
	}

	public long licenseId;
	public String name;
	public String url;
	public boolean openSource;
	public boolean active;
	public boolean recommended;
}