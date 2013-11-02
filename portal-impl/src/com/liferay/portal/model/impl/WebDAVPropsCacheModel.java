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
import com.liferay.portal.model.WebDAVProps;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WebDAVProps in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WebDAVProps
 * @generated
 */
public class WebDAVPropsCacheModel implements CacheModel<WebDAVProps>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(15);

		sb.append("{webDavPropsId=");
		sb.append(webDavPropsId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", props=");
		sb.append(props);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WebDAVProps toEntityModel() {
		WebDAVPropsImpl webDAVPropsImpl = new WebDAVPropsImpl();

		webDAVPropsImpl.setWebDavPropsId(webDavPropsId);
		webDAVPropsImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			webDAVPropsImpl.setCreateDate(null);
		}
		else {
			webDAVPropsImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			webDAVPropsImpl.setModifiedDate(null);
		}
		else {
			webDAVPropsImpl.setModifiedDate(new Date(modifiedDate));
		}

		webDAVPropsImpl.setClassNameId(classNameId);
		webDAVPropsImpl.setClassPK(classPK);

		if (props == null) {
			webDAVPropsImpl.setProps(StringPool.BLANK);
		}
		else {
			webDAVPropsImpl.setProps(props);
		}

		webDAVPropsImpl.resetOriginalValues();

		return webDAVPropsImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		webDavPropsId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		props = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(webDavPropsId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (props == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(props);
		}
	}

	public long webDavPropsId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long classNameId;
	public long classPK;
	public String props;
}