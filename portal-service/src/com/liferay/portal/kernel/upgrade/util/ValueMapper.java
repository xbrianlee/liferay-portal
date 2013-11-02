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

package com.liferay.portal.kernel.upgrade.util;

import java.util.Iterator;

/**
 * @author Alexander Chow
 * @author Brian Wing Shun Chan
 */
public interface ValueMapper {

	public void appendException(Object exception);

	public Object getNewValue(Object oldValue) throws Exception;

	public Iterator<Object> iterator() throws Exception;

	public void mapValue(Object oldValue, Object newValue) throws Exception;

	public int size() throws Exception;

}