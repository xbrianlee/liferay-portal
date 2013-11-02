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

package com.liferay.util;

import java.util.Collection;

/**
 * @author     Brian Wing Shun Chan
 * @author     Shuyang Zhou
 * @deprecated As of 6.2.0, replaced by {@link
 *             com.liferay.portal.kernel.util.UniqueList}
 */
public class UniqueList<E>
	extends com.liferay.portal.kernel.util.UniqueList<E> {

	public UniqueList() {
		super();
	}

	public UniqueList(Collection<E> c) {
		super(c);
	}

	public UniqueList(int initialCapacity) {
		super(initialCapacity);
	}

}