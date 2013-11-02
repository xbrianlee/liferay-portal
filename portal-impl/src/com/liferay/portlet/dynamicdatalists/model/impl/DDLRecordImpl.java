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

package com.liferay.portlet.dynamicdatalists.model.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordSet;
import com.liferay.portlet.dynamicdatalists.model.DDLRecordVersion;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordLocalServiceUtil;
import com.liferay.portlet.dynamicdatalists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;
import com.liferay.portlet.dynamicdatamapping.storage.StorageEngineUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Locale;

/**
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DDLRecordImpl extends DDLRecordBaseImpl {

	public DDLRecordImpl() {
	}

	@Override
	public Field getField(String fieldName) throws PortalException {
		Fields fields = getFields();

		return fields.get(fieldName);
	}

	@Override
	public Serializable getFieldDataType(String fieldName)
		throws PortalException, SystemException {

		DDLRecordSet recordSet = getRecordSet();

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		return ddmStructure.getFieldDataType(fieldName);
	}

	@Override
	public Fields getFields() throws PortalException {
		return StorageEngineUtil.getFields(getDDMStorageId());
	}

	@Override
	public Serializable getFieldType(String fieldName) throws Exception {
		DDLRecordSet recordSet = getRecordSet();

		DDMStructure ddmStructure = recordSet.getDDMStructure();

		return ddmStructure.getFieldType(fieldName);
	}

	@Override
	public Serializable getFieldValue(String fieldName) throws PortalException {
		Field field = getField(fieldName);

		if (field == null) {
			return null;
		}

		return field.getValue();
	}

	@Override
	public Serializable getFieldValue(String fieldName, Locale locale)
		throws PortalException {

		Field field = getField(fieldName);

		if (field == null) {
			return null;
		}

		return field.getValue(locale);
	}

	@Override
	public List<Serializable> getFieldValues(String fieldName, Locale locale)
		throws PortalException {

		Field field = getField(fieldName);

		if (field == null) {
			return null;
		}

		return field.getValues(locale);
	}

	@Override
	public DDLRecordVersion getLatestRecordVersion()
		throws PortalException, SystemException {

		return DDLRecordLocalServiceUtil.getLatestRecordVersion(getRecordId());
	}

	@Override
	public DDLRecordSet getRecordSet() throws PortalException, SystemException {
		return DDLRecordSetLocalServiceUtil.getRecordSet(getRecordSetId());
	}

	@Override
	public DDLRecordVersion getRecordVersion()
		throws PortalException, SystemException {

		return getRecordVersion(getVersion());
	}

	@Override
	public DDLRecordVersion getRecordVersion(String version)
		throws PortalException, SystemException {

		return DDLRecordLocalServiceUtil.getRecordVersion(
			getRecordId(), version);
	}

	@Override
	public int getStatus() throws PortalException, SystemException {
		DDLRecordVersion recordVersion = getRecordVersion();

		return recordVersion.getStatus();
	}

}