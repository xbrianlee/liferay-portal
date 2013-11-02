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

package com.liferay.portal.spring.jndi;

import com.liferay.portal.kernel.jndi.JNDIUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @author Brian Wing Shun Chan
 */
public class JndiObjectFactoryBean
	extends org.springframework.jndi.JndiObjectFactoryBean {

	@Override
	protected Object lookup() {
		try {
			Properties properties = PropsUtil.getProperties(
				PropsKeys.JNDI_ENVIRONMENT, true);

			Context context = new InitialContext(properties);

			return JNDIUtil.lookup(context, getJndiName());
		}
		catch (Exception e) {
			_log.error("Unable to lookup " + getJndiName());

			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		JndiObjectFactoryBean.class);

}