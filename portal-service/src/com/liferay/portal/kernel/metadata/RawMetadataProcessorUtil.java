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

package com.liferay.portal.kernel.metadata;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.io.File;
import java.io.InputStream;

import java.lang.reflect.Field;

import java.util.Map;

/**
 * @author Miguel Pastor
 */
public class RawMetadataProcessorUtil {

	public static Map<String, Field[]> getFields() {
		return getRawMetadataProcessor().getFields();
	}

	public static Map<String, Fields> getRawMetadataMap(
			String extension, String mimeType, File file)
		throws PortalException, SystemException {

		return getRawMetadataProcessor().getRawMetadataMap(
			extension, mimeType, file);
	}

	public static Map<String, Fields> getRawMetadataMap(
			String extension, String mimeType, InputStream inputStream)
		throws PortalException, SystemException {

		return getRawMetadataProcessor().getRawMetadataMap(
			extension, mimeType, inputStream);
	}

	public static RawMetadataProcessor getRawMetadataProcessor() {
		PortalRuntimePermission.checkGetBeanProperty(
			RawMetadataProcessorUtil.class);

		return _rawMetadataProcessor;
	}

	public void setRawMetadataProcessor(
		RawMetadataProcessor rawMetadataProcessor) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_rawMetadataProcessor = rawMetadataProcessor;
	}

	private static RawMetadataProcessor _rawMetadataProcessor;

}