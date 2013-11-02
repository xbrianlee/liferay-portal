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

package com.liferay.portlet.dynamicdatalists.util;

import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Locale;

/**
 * @author Marcellus Tavares
 * @author Manuel de la Peña
 */
public interface DDLExporter {

	public byte[] export(long recordSetId) throws Exception;

	public byte[] export(long recordSetId, int status) throws Exception;

	public byte[] export(long recordSetId, int status, int start, int end)
		throws Exception;

	public byte[] export(
			long recordSetId, int status, int start, int end,
			OrderByComparator orderByComparator)
		throws Exception;

	public Locale getLocale();

	public void setLocale(Locale locale);

}