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

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Michael Young
 */
public interface MultiVMPool {

	public void clear();

	public PortalCache<? extends Serializable, ? extends Serializable> getCache(
		String name);

	public PortalCache<? extends Serializable, ? extends Serializable> getCache(
		String name, boolean blocking);

	public void removeCache(String name);

}