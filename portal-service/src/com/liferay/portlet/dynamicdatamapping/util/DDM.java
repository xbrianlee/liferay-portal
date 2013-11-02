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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.Serializable;

/**
 * @author Eduardo Lundgren
 * @author Marcellus Tavares
 */
public interface DDM {

	public DDMDisplay getDDMDisplay(ServiceContext serviceContext);

	public Serializable getDisplayFieldValue(
			ThemeDisplay themeDisplay, Serializable fieldValue, String type)
		throws Exception;

	public Fields getFields(
			long ddmStructureId, long ddmTemplateId,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Fields getFields(
			long ddmStructureId, long ddmTemplateId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Fields getFields(long ddmStructureId, ServiceContext serviceContext)
		throws PortalException, SystemException;

	public Fields getFields(
			long ddmStructureId, String fieldNamespace,
			ServiceContext serviceContext)
		throws PortalException, SystemException;

	public String[] getFieldsDisplayValues(Field fieldsDisplayField)
		throws Exception;

	public Serializable getIndexedFieldValue(
			Serializable fieldValue, String type)
		throws Exception;

	public OrderByComparator getStructureOrderByComparator(
		String orderByCol, String orderByType);

	public OrderByComparator getTemplateOrderByComparator(
		String orderByCol, String orderByType);

	public Fields mergeFields(Fields newFields, Fields existingFields);

}