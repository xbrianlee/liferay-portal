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
import com.liferay.portal.model.Ticket;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Ticket in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Ticket
 * @generated
 */
public class TicketCacheModel implements CacheModel<Ticket>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{ticketId=");
		sb.append(ticketId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", key=");
		sb.append(key);
		sb.append(", type=");
		sb.append(type);
		sb.append(", extraInfo=");
		sb.append(extraInfo);
		sb.append(", expirationDate=");
		sb.append(expirationDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Ticket toEntityModel() {
		TicketImpl ticketImpl = new TicketImpl();

		ticketImpl.setTicketId(ticketId);
		ticketImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			ticketImpl.setCreateDate(null);
		}
		else {
			ticketImpl.setCreateDate(new Date(createDate));
		}

		ticketImpl.setClassNameId(classNameId);
		ticketImpl.setClassPK(classPK);

		if (key == null) {
			ticketImpl.setKey(StringPool.BLANK);
		}
		else {
			ticketImpl.setKey(key);
		}

		ticketImpl.setType(type);

		if (extraInfo == null) {
			ticketImpl.setExtraInfo(StringPool.BLANK);
		}
		else {
			ticketImpl.setExtraInfo(extraInfo);
		}

		if (expirationDate == Long.MIN_VALUE) {
			ticketImpl.setExpirationDate(null);
		}
		else {
			ticketImpl.setExpirationDate(new Date(expirationDate));
		}

		ticketImpl.resetOriginalValues();

		return ticketImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		ticketId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		key = objectInput.readUTF();
		type = objectInput.readInt();
		extraInfo = objectInput.readUTF();
		expirationDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(ticketId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);

		if (key == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(key);
		}

		objectOutput.writeInt(type);

		if (extraInfo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(extraInfo);
		}

		objectOutput.writeLong(expirationDate);
	}

	public long ticketId;
	public long companyId;
	public long createDate;
	public long classNameId;
	public long classPK;
	public String key;
	public int type;
	public String extraInfo;
	public long expirationDate;
}