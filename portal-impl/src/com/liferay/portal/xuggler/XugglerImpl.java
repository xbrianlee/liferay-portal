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

package com.liferay.portal.xuggler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ProgressTracker;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.xuggler.Xuggler;
import com.liferay.portal.util.JarUtil;
import com.liferay.portal.util.PrefsPropsUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.util.log4j.Log4JUtil;

import com.xuggle.ferry.JNILibraryLoader;
import com.xuggle.xuggler.IContainer;

/**
 * @author Alexander Chow
 */
public class XugglerImpl implements Xuggler {

	@Override
	public void installNativeLibraries(
			String name, ProgressTracker progressTracker)
		throws Exception {

		try {
			String url = PropsValues.XUGGLER_JAR_URL + name;

			JarUtil.downloadAndInstallJar(false, url, name, progressTracker);
		}
		catch (Exception e) {
			_log.error("Unable to install jar " + name, e);

			throw e;
		}
	}

	@Override
	public boolean isEnabled() {
		return isEnabled(true);
	}

	@Override
	public boolean isEnabled(boolean checkNativeLibraries) {
		boolean enabled = false;

		try {
			enabled = PrefsPropsUtil.getBoolean(
				PropsKeys.XUGGLER_ENABLED, PropsValues.XUGGLER_ENABLED);
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		if (!checkNativeLibraries) {
			return enabled;
		}

		if (enabled) {
			return isNativeLibraryInstalled();
		}

		return false;
	}

	@Override
	public boolean isNativeLibraryInstalled() {
		if (_nativeLibraryInstalled) {
			return _nativeLibraryInstalled;
		}

		String originalLevel = Log4JUtil.getOriginalLevel(
			JNILibraryLoader.class.getName());

		try {
			Log4JUtil.setLevel(JNILibraryLoader.class.getName(), "OFF", false);

			IContainer.make();

			_nativeLibraryInstalled = true;
		}
		catch (NoClassDefFoundError ncdfe) {
			informAdministrator(ncdfe.getMessage());
		}
		catch (UnsatisfiedLinkError ule) {
			informAdministrator(ule.getMessage());
		}
		finally {
			Log4JUtil.setLevel(
				JNILibraryLoader.class.getName(), originalLevel.toString(),
				false);
		}

		return _nativeLibraryInstalled;
	}

	protected void informAdministrator(String errorMessage) {
		if (!_informAdministrator) {
			return;
		}

		_informAdministrator = false;

		StringBundler sb = new StringBundler(7);

		sb.append("Liferay does not have the Xuggler native libraries ");
		sb.append("installed. In order to generate video and audio previews, ");
		sb.append("please follow the instructions for Xuggler in the Server ");
		sb.append("Administration section of the Control Panel at: ");
		sb.append("http://<server>/group/control_panel/manage/-/server/");
		sb.append("external-services. Error message is: ");
		sb.append(errorMessage);

		_log.error(sb.toString());
	}

	private static Log _log = LogFactoryUtil.getLog(XugglerImpl.class);

	private static boolean _informAdministrator = true;
	private static boolean _nativeLibraryInstalled;

}