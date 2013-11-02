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

package com.liferay.portal.kernel.util;

/**
 * @author Jonathan Potter
 * @author Brian Wing Shun Chan
 */
public class HtmlEscapableObject<T> extends EscapableObject<T> {

	public HtmlEscapableObject(T originalValue) {
		super(originalValue);
	}

	public HtmlEscapableObject(T originalValue, boolean escape) {
		super(originalValue, escape);
	}

	@Override
	protected String escape(T t) {
		return HtmlUtil.escape(String.valueOf(getOriginalValue()));
	}

}