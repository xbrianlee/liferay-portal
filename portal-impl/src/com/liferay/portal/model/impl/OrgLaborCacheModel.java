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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.OrgLabor;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing OrgLabor in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see OrgLabor
 * @generated
 */
public class OrgLaborCacheModel implements CacheModel<OrgLabor>, Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{orgLaborId=");
		sb.append(orgLaborId);
		sb.append(", organizationId=");
		sb.append(organizationId);
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", sunOpen=");
		sb.append(sunOpen);
		sb.append(", sunClose=");
		sb.append(sunClose);
		sb.append(", monOpen=");
		sb.append(monOpen);
		sb.append(", monClose=");
		sb.append(monClose);
		sb.append(", tueOpen=");
		sb.append(tueOpen);
		sb.append(", tueClose=");
		sb.append(tueClose);
		sb.append(", wedOpen=");
		sb.append(wedOpen);
		sb.append(", wedClose=");
		sb.append(wedClose);
		sb.append(", thuOpen=");
		sb.append(thuOpen);
		sb.append(", thuClose=");
		sb.append(thuClose);
		sb.append(", friOpen=");
		sb.append(friOpen);
		sb.append(", friClose=");
		sb.append(friClose);
		sb.append(", satOpen=");
		sb.append(satOpen);
		sb.append(", satClose=");
		sb.append(satClose);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public OrgLabor toEntityModel() {
		OrgLaborImpl orgLaborImpl = new OrgLaborImpl();

		orgLaborImpl.setOrgLaborId(orgLaborId);
		orgLaborImpl.setOrganizationId(organizationId);
		orgLaborImpl.setTypeId(typeId);
		orgLaborImpl.setSunOpen(sunOpen);
		orgLaborImpl.setSunClose(sunClose);
		orgLaborImpl.setMonOpen(monOpen);
		orgLaborImpl.setMonClose(monClose);
		orgLaborImpl.setTueOpen(tueOpen);
		orgLaborImpl.setTueClose(tueClose);
		orgLaborImpl.setWedOpen(wedOpen);
		orgLaborImpl.setWedClose(wedClose);
		orgLaborImpl.setThuOpen(thuOpen);
		orgLaborImpl.setThuClose(thuClose);
		orgLaborImpl.setFriOpen(friOpen);
		orgLaborImpl.setFriClose(friClose);
		orgLaborImpl.setSatOpen(satOpen);
		orgLaborImpl.setSatClose(satClose);

		orgLaborImpl.resetOriginalValues();

		return orgLaborImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		orgLaborId = objectInput.readLong();
		organizationId = objectInput.readLong();
		typeId = objectInput.readInt();
		sunOpen = objectInput.readInt();
		sunClose = objectInput.readInt();
		monOpen = objectInput.readInt();
		monClose = objectInput.readInt();
		tueOpen = objectInput.readInt();
		tueClose = objectInput.readInt();
		wedOpen = objectInput.readInt();
		wedClose = objectInput.readInt();
		thuOpen = objectInput.readInt();
		thuClose = objectInput.readInt();
		friOpen = objectInput.readInt();
		friClose = objectInput.readInt();
		satOpen = objectInput.readInt();
		satClose = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(orgLaborId);
		objectOutput.writeLong(organizationId);
		objectOutput.writeInt(typeId);
		objectOutput.writeInt(sunOpen);
		objectOutput.writeInt(sunClose);
		objectOutput.writeInt(monOpen);
		objectOutput.writeInt(monClose);
		objectOutput.writeInt(tueOpen);
		objectOutput.writeInt(tueClose);
		objectOutput.writeInt(wedOpen);
		objectOutput.writeInt(wedClose);
		objectOutput.writeInt(thuOpen);
		objectOutput.writeInt(thuClose);
		objectOutput.writeInt(friOpen);
		objectOutput.writeInt(friClose);
		objectOutput.writeInt(satOpen);
		objectOutput.writeInt(satClose);
	}

	public long orgLaborId;
	public long organizationId;
	public int typeId;
	public int sunOpen;
	public int sunClose;
	public int monOpen;
	public int monClose;
	public int tueOpen;
	public int tueClose;
	public int wedOpen;
	public int wedClose;
	public int thuOpen;
	public int thuClose;
	public int friOpen;
	public int friClose;
	public int satOpen;
	public int satClose;
}