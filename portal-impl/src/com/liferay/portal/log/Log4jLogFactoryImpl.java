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

package com.liferay.portal.log;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.util.log4j.Log4JUtil;

import org.apache.log4j.LogManager;

/**
 * @author Brian Wing Shun Chan
 */
@DoPrivileged
public class Log4jLogFactoryImpl implements LogFactory {

	@Override
	public Log getLog(Class<?> c) {
		return getLog(c.getName());
	}

	@Override
	public Log getLog(String name) {
		return new Log4jLogImpl(LogManager.getLogger(name));
	}

	@Override
	public void setLevel(String name, String priority, boolean custom) {
		Log4JUtil.setLevel(name, priority, custom);
	}

}