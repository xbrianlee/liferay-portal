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

package com.liferay.portal.kernel.trash;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Alexander Chow
 */
public class TrashHandlerRegistryUtil {

	public static TrashHandler getTrashHandler(String className) {
		return getTrashHandlerRegistry().getTrashHandler(className);
	}

	public static TrashHandlerRegistry getTrashHandlerRegistry() {
		PortalRuntimePermission.checkGetBeanProperty(
			TrashHandlerRegistryUtil.class);

		return _trashHandlerRegistry;
	}

	public static List<TrashHandler> getTrashHandlers() {
		return getTrashHandlerRegistry().getTrashHandlers();
	}

	public static void register(List<TrashHandler> trashHandlers) {
		for (TrashHandler trashHandler : trashHandlers) {
			register(trashHandler);
		}
	}

	public static void register(TrashHandler trashHandler) {
		getTrashHandlerRegistry().register(trashHandler);
	}

	public static void unregister(List<TrashHandler> trashHandlers) {
		for (TrashHandler trashHandler : trashHandlers) {
			unregister(trashHandler);
		}
	}

	public static void unregister(TrashHandler trashHandler) {
		getTrashHandlerRegistry().unregister(trashHandler);
	}

	public void setTrashHandlerRegistry(
		TrashHandlerRegistry trashHandlerRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_trashHandlerRegistry = trashHandlerRegistry;
	}

	private static TrashHandlerRegistry _trashHandlerRegistry;

}