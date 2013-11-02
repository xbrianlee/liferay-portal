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

package com.liferay.portlet.wiki.model;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;

/**
 * @author Alexander Chow
 */
public class WikiPageConstants {

	public static final String BASE_ATTACHMENTS_DIR = "wiki/";

	public static final String DEFAULT_FORMAT = PropsUtil.get(
		PropsKeys.WIKI_FORMATS_DEFAULT);

	public static final String[] FORMATS = PropsUtil.getArray(
		PropsKeys.WIKI_FORMATS);

	public static final String FRONT_PAGE = PropsUtil.get(
		PropsKeys.WIKI_FRONT_PAGE_NAME);

	public static final String MOVED = "Moved";

	public static final String NEW = "New";

	public static final String REVERTED = "Reverted";

	public static final double VERSION_DEFAULT = 1.0;

}