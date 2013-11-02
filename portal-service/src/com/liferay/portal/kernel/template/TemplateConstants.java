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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Tina Tian
 */
public interface TemplateConstants {

	public static final String DEFAUT_ENCODING = StringPool.UTF8;

	/**
	 * @deprecated As of 6.2.0
	 */
	public static final String JOURNAL_SEPARATOR = "_JOURNAL_CONTEXT_";

	public static final String LANG_TYPE_CSS = "css";

	public static final String LANG_TYPE_FTL = "ftl";

	public static final String LANG_TYPE_VM = "vm";

	public static final String LANG_TYPE_XSL = "xsl";

	public static final String SERVLET_SEPARATOR = "_SERVLET_CONTEXT_";

	public static final String TEMPLATE_ID = "template_id";

	public static final String TEMPLATE_RESOURCE_UUID_PREFIX =
		"TEMPLATE_RESOURCE_UUID";

	public static final String TEMPLATE_SEPARATOR = "_TEMPLATE_CONTEXT_";

	public static final String THEME_LOADER_SEPARATOR =
		"_THEME_LOADER_CONTEXT_";

	public static final String WRITER = "writer";

}