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

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.io.File;

import java.util.Properties;

/**
 * @author Zsolt Balogh
 * @author Brian Wing Shun Chan
 */
public class PatcherUtil {

	public static boolean applyPatch(File patchFile) {
		return getPatcher().applyPatch(patchFile);
	}

	public static String[] getFixedIssues() {
		return getPatcher().getFixedIssues();
	}

	public static String[] getInstalledPatches() {
		return getPatcher().getInstalledPatches();
	}

	public static File getPatchDirectory() {
		return getPatcher().getPatchDirectory();
	}

	public static Patcher getPatcher() {
		PortalRuntimePermission.checkGetBeanProperty(Patcher.class);

		return _patcher;
	}

	public static String[] getPatchLevels() {
		return getPatcher().getPatchLevels();
	}

	public static Properties getProperties() {
		return getPatcher().getProperties();
	}

	public static boolean isConfigured() {
		return getPatcher().isConfigured();
	}

	public void setPatcher(Patcher patcher) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_patcher = patcher;
	}

	private static Patcher _patcher;

}