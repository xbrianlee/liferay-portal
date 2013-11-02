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

package com.liferay.portal.kernel.image;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * The ImageMagick utility class.
 *
 * @author Alexander Chow
 */
public class ImageMagickUtil {

	/**
	 * Executes the <code>convert</code> command in ImageMagick.
	 *
	 * @param  arguments the command arguments being passed to <code>convert
	 *         </code>
	 * @return the converted command arguments
	 * @throws Exception if an unexpected error occurred while executing command
	 * @see    <a href="http://www.imagemagick.org/script/convert.php">Convert
	 *         documentation</a>
	 */
	public static Future<?> convert(List<String> arguments) throws Exception {
		return getImageMagick().convert(arguments);
	}

	public static void destroy() {
		getImageMagick().destroy();
	}

	/**
	 * Returns the global search path configured for ImageMagick.
	 *
	 * @return the global search path
	 * @throws Exception if an unexpected error occurred
	 */
	public static String getGlobalSearchPath() throws Exception {
		return getImageMagick().getGlobalSearchPath();
	}

	public static ImageMagick getImageMagick() {
		PortalRuntimePermission.checkGetBeanProperty(ImageMagickUtil.class);

		return _imageMagick;
	}

	/**
	 * Returns the cache and resource usage limits configured for ImageMagick.
	 *
	 * @return the cache and resource usage limits
	 * @throws Exception if an unexpected error occurred
	 */
	public static Properties getResourceLimitsProperties() throws Exception {
		return getImageMagick().getResourceLimitsProperties();
	}

	/**
	 * Executes the <code>identify</code> command in ImageMagick.
	 *
	 * @param  arguments the command arguments being passed to <code>identify
	 *         </code>
	 * @return the results of the <code>identify</code> call
	 * @throws Exception if an unexpected error occurred while executing command
	 * @see    <a href="http://www.imagemagick.org/script/identify.php">Identify
	 *         documentation</a>
	 */
	public static String[] identify(List<String> arguments) throws Exception {
		return getImageMagick().identify(arguments);
	}

	/**
	 * Returns <code>true</code> if ImageMagick is enabled.
	 *
	 * @return <code>true</code> if ImageMagick is enabled; <code>false</code>
	 *         otherwise
	 */
	public static boolean isEnabled() {
		return getImageMagick().isEnabled();
	}

	/**
	 * Resets the global search path and resource limits for ImageMagick.
	 */
	public static void reset() {
		getImageMagick().reset();
	}

	public void setImageMagick(ImageMagick imageMagick) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_imageMagick = imageMagick;
	}

	private static ImageMagick _imageMagick;

}