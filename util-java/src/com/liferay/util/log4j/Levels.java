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

package com.liferay.util.log4j;

import org.apache.log4j.Level;

/**
 * @author Brian Wing Shun Chan
 */
public class Levels {

	public static final Level[] ALL_LEVELS = new Level[] {
		Level.OFF, Level.FATAL, Level.ERROR, Level.WARN, Level.INFO,
		Level.DEBUG, Level.ALL
	};

}