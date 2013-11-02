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

package com.liferay.portal.kernel.nio.intraband.welder;

import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOUtil;
import com.liferay.portal.kernel.nio.intraband.welder.fifo.FIFOWelder;
import com.liferay.portal.kernel.nio.intraband.welder.socket.SocketWelder;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OSDetector;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Shuyang Zhou
 */
public class WelderFactoryUtil {

	public static Welder createWelder() {
		Class<? extends Welder> welderClass = getWelderClass();

		try {
			return welderClass.newInstance();
		}
		catch (Exception e) {
			throw new RuntimeException(
				"Unable to create Welder instance for class " + welderClass, e);
		}
	}

	public static Class<? extends Welder> getWelderClass() {
		if (Validator.isNotNull(INTRABAND_WELDER_IMPL)) {
			try {
				return (Class<? extends Welder>)Class.forName(
					INTRABAND_WELDER_IMPL);
			}
			catch (ClassNotFoundException cnfe) {
				throw new RuntimeException(
					"Unable to load class with name " + INTRABAND_WELDER_IMPL,
					cnfe);
			}
		}
		else {
			if (!OSDetector.isWindows() && FIFOUtil.isFIFOSupported()) {
				return FIFOWelder.class;
			}
			else {
				return SocketWelder.class;
			}
		}
	}

	private static final String INTRABAND_WELDER_IMPL = GetterUtil.getString(
		System.getProperty(PropsKeys.INTRABAND_WELDER_IMPL));

}