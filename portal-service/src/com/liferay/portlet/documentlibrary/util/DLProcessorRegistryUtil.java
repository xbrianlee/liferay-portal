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

package com.liferay.portlet.documentlibrary.util;

import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.xml.Element;

/**
 * @author Mika Koivisto
 */
public class DLProcessorRegistryUtil {

	public static void cleanUp(FileEntry fileEntry) {
		getDLProcessorRegistry().cleanUp(fileEntry);
	}

	public static void cleanUp(FileVersion fileVersion) {
		getDLProcessorRegistry().cleanUp(fileVersion);
	}

	public static void exportGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			Element fileEntryElement)
		throws Exception {

		getDLProcessorRegistry().exportGeneratedFiles(
			portletDataContext, fileEntry, fileEntryElement);
	}

	public static DLProcessor getDLProcessor(String dlProcessorType) {
		return getDLProcessorRegistry().getDLProcessor(dlProcessorType);
	}

	public static DLProcessorRegistry getDLProcessorRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			DLProcessorRegistryUtil.class);

		return _dlProcessorRegistry;
	}

	public static void importGeneratedFiles(
			PortletDataContext portletDataContext, FileEntry fileEntry,
			FileEntry importedFileEntry, Element fileEntryElement)
		throws Exception {

		getDLProcessorRegistry().importGeneratedFiles(
			portletDataContext, fileEntry, importedFileEntry, fileEntryElement);
	}

	public static boolean isPreviewableSize(FileVersion fileVersion) {
		return getDLProcessorRegistry().isPreviewableSize(fileVersion);
	}

	public static void register(DLProcessor dlProcessor) {
		getDLProcessorRegistry().register(dlProcessor);
	}

	public static void trigger(FileEntry fileEntry, FileVersion fileVersion) {
		getDLProcessorRegistry().trigger(fileEntry, fileVersion);
	}

	public static void trigger(
		FileEntry fileEntry, FileVersion fileVersion, boolean trusted) {

		getDLProcessorRegistry().trigger(fileEntry, fileVersion, trusted);
	}

	public static void unregister(DLProcessor dlProcessor) {
		getDLProcessorRegistry().unregister(dlProcessor);
	}

	public void setDLProcessorRegistry(
		DLProcessorRegistry dlProcessorRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_dlProcessorRegistry = dlProcessorRegistry;
	}

	private static DLProcessorRegistry _dlProcessorRegistry;

}