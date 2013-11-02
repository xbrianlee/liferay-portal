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

package com.liferay.portal.kernel.cache;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public class MultiVMPoolUtil {

	public static void clear() {
		getMultiVMPool().clear();
	}

	public static <K extends Serializable, V extends Serializable>
		PortalCache<K, V> getCache(String name) {

		return (PortalCache<K, V>)getMultiVMPool().getCache(name);
	}

	public static <K extends Serializable, V extends Serializable>
		PortalCache<K, V> getCache(String name, boolean blocking) {

		return (PortalCache<K, V>)getMultiVMPool().getCache(name, blocking);
	}

	public static MultiVMPool getMultiVMPool() {
		PortalRuntimePermission.checkGetBeanProperty(MultiVMPoolUtil.class);

		return _multiVMPool;
	}

	public static void removeCache(String name) {
		getMultiVMPool().removeCache(name);
	}

	public void setMultiVMPool(MultiVMPool multiVMPool) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_multiVMPool = multiVMPool;
	}

	private static MultiVMPool _multiVMPool;

}