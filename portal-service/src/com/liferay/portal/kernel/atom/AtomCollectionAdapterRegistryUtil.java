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

package com.liferay.portal.kernel.atom;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;

/**
 * @author Igor Spasic
 */
public class AtomCollectionAdapterRegistryUtil {

	public static AtomCollectionAdapter<?> getAtomCollectionAdapter(
		String collectionName) {

		return getAtomCollectionAdapterRegistry().getAtomCollectionAdapter(
			collectionName);
	}

	public static AtomCollectionAdapterRegistry
		getAtomCollectionAdapterRegistry() {

		PortalRuntimePermission.checkGetBeanProperty(
			AtomCollectionAdapterRegistryUtil.class);

		return _atomCollectionAdapterRegistry;
	}

	public static List<AtomCollectionAdapter<?>> getAtomCollectionAdapters() {
		return getAtomCollectionAdapterRegistry().getAtomCollectionAdapters();
	}

	public static void register(
		AtomCollectionAdapter<?> atomCollectionAdapter) {

		getAtomCollectionAdapterRegistry().register(atomCollectionAdapter);
	}

	public static void register(
		List<AtomCollectionAdapter<?>> atomCollectionAdapters) {

		for (AtomCollectionAdapter<?> atomCollectionAdapter :
				atomCollectionAdapters) {

			register(atomCollectionAdapter);
		}
	}

	public static void unregister(
		AtomCollectionAdapter<?> atomCollectionAdapter) {

		getAtomCollectionAdapterRegistry().unregister(atomCollectionAdapter);
	}

	public static void unregister(
		List<AtomCollectionAdapter<?>> atomCollectionAdapters) {

		for (AtomCollectionAdapter<?> atomCollectionAdapter :
				atomCollectionAdapters) {

			unregister(atomCollectionAdapter);
		}
	}

	public void setAtomCollectionAdapterRegistry(
		AtomCollectionAdapterRegistry atomCollectionAdapterRegistry) {

		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_atomCollectionAdapterRegistry = atomCollectionAdapterRegistry;
	}

	private static AtomCollectionAdapterRegistry _atomCollectionAdapterRegistry;

}