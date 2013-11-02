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

package com.liferay.portal.kernel.jndi;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.util.StringUtil;

import javax.naming.Context;
import javax.naming.NamingException;

/**
 * @author Brian Wing Shun Chan
 * @author Sandeep Soni
 */
public class JNDIUtil {

	public static Object lookup(Context context, String location)
		throws NamingException {

		return _lookup(context, location);
	}

	/**
	 * @deprecated As of 6.2.0, replaced by {@link #lookup(Context, String)}
	 */
	public static Object lookup(Context context, String location, boolean cache)
		throws NamingException {

		return _lookup(context, location);
	}

	private static Object _lookup(Context context, String location)
		throws NamingException {

		PortalRuntimePermission.checkGetBeanProperty(JNDIUtil.class);

		if (_log.isDebugEnabled()) {
			_log.debug("Lookup " + location);
		}

		Object obj = null;

		try {
			obj = context.lookup(location);
		}
		catch (NamingException ne1) {

			// java:comp/env/ObjectName to ObjectName

			if (location.contains("java:comp/env/")) {
				try {
					String newLocation = StringUtil.replace(
						location, "java:comp/env/", "");

					if (_log.isDebugEnabled()) {
						_log.debug(ne1.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
				catch (NamingException ne2) {

					// java:comp/env/ObjectName to java:ObjectName

					String newLocation = StringUtil.replace(
						location, "comp/env/", "");

					if (_log.isDebugEnabled()) {
						_log.debug(ne2.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
			}

			// java:ObjectName to ObjectName

			else if (location.contains("java:")) {
				try {
					String newLocation = StringUtil.replace(
						location, "java:", "");

					if (_log.isDebugEnabled()) {
						_log.debug(ne1.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
				catch (NamingException ne2) {

					// java:ObjectName to java:comp/env/ObjectName

					String newLocation = StringUtil.replace(
						location, "java:", "java:comp/env/");

					if (_log.isDebugEnabled()) {
						_log.debug(ne2.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
			}

			// ObjectName to java:ObjectName

			else if (!location.contains("java:")) {
				try {
					String newLocation = "java:" + location;

					if (_log.isDebugEnabled()) {
						_log.debug(ne1.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
				catch (NamingException ne2) {

					// ObjectName to java:comp/env/ObjectName

					String newLocation = "java:comp/env/" + location;

					if (_log.isDebugEnabled()) {
						_log.debug(ne2.getMessage());
						_log.debug("Attempt " + newLocation);
					}

					obj = context.lookup(newLocation);
				}
			}
			else {
				throw new NamingException();
			}
		}

		return obj;
	}

	private static Log _log = LogFactoryUtil.getLog(JNDIUtil.class);

}