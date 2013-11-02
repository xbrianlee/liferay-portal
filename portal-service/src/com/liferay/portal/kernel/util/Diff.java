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

import java.io.Reader;

import java.util.List;

/**
 * @author Bruno Farache
 * @see    com.liferay.portal.kernel.util.DiffUtil
 */
public interface Diff {

	public static final String CLOSE_DEL = "</del>";

	public static final String CLOSE_INS = "</ins>";

	public static final String CONTEXT_LINE = "#context#line#";

	public static final String OPEN_DEL = "<del>";

	public static final String OPEN_INS = "<ins>";

	public List<DiffResult>[] diff(Reader source, Reader target);

	public List<DiffResult>[] diff(
		Reader source, Reader target, String addedMarkerStart,
		String addedMarkerEnd, String deletedMarkerStart,
		String deletedMarkerEnd, int margin);

}