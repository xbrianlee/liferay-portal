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

package com.liferay.portlet.dynamicdatalists.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatalists.service.base.DDLRecordServiceBaseImpl;
import com.liferay.portlet.dynamicdatalists.service.permission.DDLRecordSetPermission;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

/**
 * Provides the remote service for accessing, adding, deleting, and updating
 * dynamic data list (DDL) records. Its methods include permission checks.
 *
 * @author Brian Wing Shun Chan
 * @author Eduardo Lundgren
 */
public class DDLRecordServiceImpl extends DDLRecordServiceBaseImpl {

	@Override
	public DDLRecord addRecord(
			long groupId, long recordSetId, int displayIndex, Fields fields,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), recordSetId, ActionKeys.ADD_RECORD);

		return ddlRecordLocalService.addRecord(
			getGuestOrUserId(), groupId, recordSetId, displayIndex, fields,
			serviceContext);
	}

	@Override
	public DDLRecord addRecord(
			long groupId, long recordSetId, int displayIndex,
			Map<String, Serializable> fieldsMap, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecordSetPermission.check(
			getPermissionChecker(), recordSetId, ActionKeys.ADD_RECORD);

		return ddlRecordLocalService.addRecord(
			getGuestOrUserId(), groupId, recordSetId, displayIndex, fieldsMap,
			serviceContext);
	}

	@Override
	public DDLRecord deleteRecordLocale(
			long recordId, Locale locale, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecord record = ddlRecordLocalService.getDDLRecord(recordId);

		DDLRecordSetPermission.check(
			getPermissionChecker(), record.getRecordSetId(), ActionKeys.UPDATE);

		return ddlRecordLocalService.deleteRecordLocale(
			recordId, locale, serviceContext);
	}

	@Override
	public DDLRecord getRecord(long recordId)
		throws PortalException, SystemException {

		DDLRecord record = ddlRecordLocalService.getDDLRecord(recordId);

		DDLRecordSetPermission.check(
			getPermissionChecker(), record.getRecordSetId(), ActionKeys.VIEW);

		return record;
	}

	@Override
	public DDLRecord updateRecord(
			long recordId, boolean majorVersion, int displayIndex,
			Fields fields, boolean mergeFields, ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecord record = ddlRecordLocalService.getDDLRecord(recordId);

		DDLRecordSetPermission.check(
			getPermissionChecker(), record.getRecordSetId(), ActionKeys.UPDATE);

		return ddlRecordLocalService.updateRecord(
			getUserId(), recordId, majorVersion, displayIndex, fields,
			mergeFields, serviceContext);
	}

	@Override
	public DDLRecord updateRecord(
			long recordId, int displayIndex,
			Map<String, Serializable> fieldsMap, boolean mergeFields,
			ServiceContext serviceContext)
		throws PortalException, SystemException {

		DDLRecord record = ddlRecordLocalService.getDDLRecord(recordId);

		DDLRecordSetPermission.check(
			getPermissionChecker(), record.getRecordSetId(), ActionKeys.UPDATE);

		return ddlRecordLocalService.updateRecord(
			getUserId(), recordId, displayIndex, fieldsMap, mergeFields,
			serviceContext);
	}

}