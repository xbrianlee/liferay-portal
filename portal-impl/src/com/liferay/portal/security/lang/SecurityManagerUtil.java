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

package com.liferay.portal.security.lang;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ServiceLoader;
import com.liferay.portal.util.PropsValues;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 * @author Zsolt Berentey
 */
public class SecurityManagerUtil {

	public static final boolean ENABLED = (System.getSecurityManager() != null);

	public static void destroy() {
		if (_portalSecurityManager == null) {
			return;
		}

		_portalSecurityManager.destroy();

		_portalSecurityManager = null;
	}

	public static PortalSecurityManager getPortalSecurityManager() {
		return _portalSecurityManager;
	}

	public static void init() {
		if (_portalSecurityManagerStrategy != null) {
			return;
		}

		_portalSecurityManagerStrategy = PortalSecurityManagerStrategy.parse(
			PropsValues.PORTAL_SECURITY_MANAGER_STRATEGY);

		if (_portalSecurityManagerStrategy ==
				PortalSecurityManagerStrategy.LIFERAY) {

			if (!ENABLED) {
				_log.error(
					"Plugin security management is not enabled. Enable a " +
						"security manager, then restart.");

				_portalSecurityManagerStrategy =
					PortalSecurityManagerStrategy.DEFAULT;

				return;
			}

			loadPortalSecurityManager();

			if (_portalSecurityManager == null) {
				_portalSecurityManagerStrategy =
					PortalSecurityManagerStrategy.DEFAULT;

				if (_log.isInfoEnabled()) {
					_log.info(
						"No portal security manager implementation was " +
							"located. Continuing with the default security " +
								"strategy.");
				}

				return;
			}
		}

		if (_portalSecurityManagerStrategy ==
				PortalSecurityManagerStrategy.LIFERAY) {

			System.setSecurityManager((SecurityManager)_portalSecurityManager);
		}
	}

	public static boolean isDefault() {
		init();

		if (_portalSecurityManagerStrategy ==
				PortalSecurityManagerStrategy.DEFAULT) {

			return true;
		}

		return false;
	}

	public static boolean isLiferay() {
		init();

		if (_portalSecurityManagerStrategy ==
				PortalSecurityManagerStrategy.LIFERAY) {

			return true;
		}

		return false;
	}

	private static void loadPortalSecurityManager() {
		try {
			List<PortalSecurityManager> portalSecurityManagers =
				ServiceLoader.load(PortalSecurityManager.class);

			if (portalSecurityManagers.isEmpty()) {
				return;
			}

			_portalSecurityManager = portalSecurityManagers.get(0);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(SecurityManagerUtil.class);

	private static PortalSecurityManager _portalSecurityManager;
	private static PortalSecurityManagerStrategy _portalSecurityManagerStrategy;

	private enum PortalSecurityManagerStrategy {

		DEFAULT, LIFERAY;

		public static PortalSecurityManagerStrategy parse(String value) {
			if (PropsValues.TCK_URL) {
				return DEFAULT;
			}
			else if (value.equals("liferay")) {
				return LIFERAY;
			}

			return DEFAULT;
		}

	}

}