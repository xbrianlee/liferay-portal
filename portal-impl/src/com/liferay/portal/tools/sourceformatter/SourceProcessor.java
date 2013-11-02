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

package com.liferay.portal.tools.sourceformatter;

import java.util.List;

/**
 * @author Hugo Huijser
 */
public interface SourceProcessor {

	public void format(
			boolean useProperties, boolean printErrors, boolean autoFix,
			String mainReleaseVersion)
		throws Exception;

	public String format(
			String fileName, boolean useProperties, boolean printErrors,
			boolean autoFix, String mainReleaseVersion)
		throws Exception;

	public List<String> getErrorMessages();

}