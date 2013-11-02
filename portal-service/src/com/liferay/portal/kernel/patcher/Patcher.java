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

package com.liferay.portal.kernel.patcher;

import java.io.File;

import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public interface Patcher {

	public static final String PATCHER_PROPERTIES = "patcher.properties";

	public static final String PROPERTY_FIXED_ISSUES = "fixed.issues";

	public static final String PROPERTY_INSTALLED_PATCHES = "installed.patches";

	public static final String PROPERTY_PATCH_DIRECTORY = "patch.directory";

	public static final String PROPERTY_PATCH_LEVELS = "patch.levels";

	public boolean applyPatch(File patchFile);

	public String[] getFixedIssues();

	public String[] getInstalledPatches();

	public File getPatchDirectory();

	public String[] getPatchLevels();

	public Properties getProperties();

	public boolean isConfigured();

}