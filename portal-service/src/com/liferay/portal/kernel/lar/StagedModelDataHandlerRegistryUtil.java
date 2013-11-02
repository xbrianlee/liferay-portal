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

package com.liferay.portal.kernel.lar;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * Provides a utility facade to the staged model data handler registry
 * framework.
 *
 * @author Mate Thurzo
 * @author Brian Wing Shun Chan
 * @since  6.2
 */
public class StagedModelDataHandlerRegistryUtil {

	/**
	 * Returns the registered staged model data handler with the class name.
	 *
	 * @param  className the name of the staged model class
	 * @return the registered staged model data handler with the class name, or
	 *         <code>null</code> if none are registered with the class name
	 */
	public static StagedModelDataHandler<?> getStagedModelDataHandler(
		String className) {

		return getStagedModelDataHandlerRegistry().getStagedModelDataHandler(
			className);
	}

	/**
	 * Returns the staged model data handler registry.
	 *
	 * @return the staged model data handler registry
	 */
	public static StagedModelDataHandlerRegistry
		getStagedModelDataHandlerRegistry() {

		PortalRuntimePermission.checkGetBeanProperty(
			StagedModelDataHandlerRegistryUtil.class);

		return _stagedModelDataHandlerRegistry;
	}

	/**
	 * Returns all the registered staged model data handlers.
	 *
	 * @return the registered staged model data handlers
	 */
	public static List<StagedModelDataHandler<?>> getStagedModelDataHandlers() {
		return getStagedModelDataHandlerRegistry().getStagedModelDataHandlers();
	}

	/**
	 * Registers the staged model data handler.
	 *
	 * @param stagedModelDataHandler the staged model data handler to register
	 */
	public static void register(
		StagedModelDataHandler<?> stagedModelDataHandler) {

		getStagedModelDataHandlerRegistry().register(stagedModelDataHandler);
	}

	/**
	 * Unregisters the staged model data handlers.
	 *
	 * @param stagedModelDataHandlers the staged model data handlers to
	 *        unregister
	 */
	public static void unregister(
		List<StagedModelDataHandler<?>> stagedModelDataHandlers) {

		for (StagedModelDataHandler<?> stagedModelDataHandler :
				stagedModelDataHandlers) {

			unregister(stagedModelDataHandler);
		}
	}

	/**
	 * Unregisters the staged model data handler.
	 *
	 * @param stagedModelDataHandler the staged model data handler to unregister
	 */
	public static void unregister(
		StagedModelDataHandler<?> stagedModelDataHandler) {

		getStagedModelDataHandlerRegistry().unregister(stagedModelDataHandler);
	}

	/**
	 * Sets the staged model data handler registry.
	 *
	 * @param stagedModelDataHandlerRegistry the staged model data handler
	 *        registry
	 */
	public void setStagedModelDataHandlerRegistry(
		StagedModelDataHandlerRegistry stagedModelDataHandlerRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_stagedModelDataHandlerRegistry = stagedModelDataHandlerRegistry;
	}

	private static StagedModelDataHandlerRegistry
		_stagedModelDataHandlerRegistry;

}