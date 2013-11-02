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
import com.liferay.portal.model.Company;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Company in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Company
 * @generated
 */
public class CompanyCacheModel implements CacheModel<Company>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{companyId=");
		sb.append(companyId);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", webId=");
		sb.append(webId);
		sb.append(", key=");
		sb.append(key);
		sb.append(", mx=");
		sb.append(mx);
		sb.append(", homeURL=");
		sb.append(homeURL);
		sb.append(", logoId=");
		sb.append(logoId);
		sb.append(", system=");
		sb.append(system);
		sb.append(", maxUsers=");
		sb.append(maxUsers);
		sb.append(", active=");
		sb.append(active);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Company toEntityModel() {
		CompanyImpl companyImpl = new CompanyImpl();

		companyImpl.setCompanyId(companyId);
		companyImpl.setAccountId(accountId);

		if (webId == null) {
			companyImpl.setWebId(StringPool.BLANK);
		}
		else {
			companyImpl.setWebId(webId);
		}

		if (key == null) {
			companyImpl.setKey(StringPool.BLANK);
		}
		else {
			companyImpl.setKey(key);
		}

		if (mx == null) {
			companyImpl.setMx(StringPool.BLANK);
		}
		else {
			companyImpl.setMx(mx);
		}

		if (homeURL == null) {
			companyImpl.setHomeURL(StringPool.BLANK);
		}
		else {
			companyImpl.setHomeURL(homeURL);
		}

		companyImpl.setLogoId(logoId);
		companyImpl.setSystem(system);
		companyImpl.setMaxUsers(maxUsers);
		companyImpl.setActive(active);

		companyImpl.resetOriginalValues();

		companyImpl.setKeyObj(_keyObj);

		companyImpl.setVirtualHostname(_virtualHostname);

		return companyImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {
		companyId = objectInput.readLong();
		accountId = objectInput.readLong();
		webId = objectInput.readUTF();
		key = objectInput.readUTF();
		mx = objectInput.readUTF();
		homeURL = objectInput.readUTF();
		logoId = objectInput.readLong();
		system = objectInput.readBoolean();
		maxUsers = objectInput.readInt();
		active = objectInput.readBoolean();

		_keyObj = (java.security.Key)objectInput.readObject();
		_virtualHostname = (java.lang.String)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(accountId);

		if (webId == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(webId);
		}

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		if (mx == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mx);
		}

		if (homeURL == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(homeURL);
		}

		objectOutput.writeLong(logoId);
		objectOutput.writeBoolean(system);
		objectOutput.writeInt(maxUsers);
		objectOutput.writeBoolean(active);

		objectOutput.writeObject(_keyObj);
		objectOutput.writeObject(_virtualHostname);
	}

	public long companyId;
	public long accountId;
	public String webId;
	public String key;
	public String mx;
	public String homeURL;
	public long logoId;
	public boolean system;
	public int maxUsers;
	public boolean active;
	public java.security.Key _keyObj;
	public java.lang.String _virtualHostname;
}