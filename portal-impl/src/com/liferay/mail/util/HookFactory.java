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

package com.liferay.mail.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.util.ClassLoaderUtil;
import com.liferay.portal.util.PropsValues;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class HookFactory {

	public static Hook getInstance() {
		return _hook;
	}

	public static void setInstance(Hook hook) {
		if (_log.isDebugEnabled()) {
			_log.debug("Set " + ClassUtil.getClassName(hook));
		}

		if (hook == null) {
			_hook = _originalHook;
		}
		else {
			_hook = hook;
		}
	}

	public void afterPropertiesSet() throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug("Instantiate " + PropsValues.MAIL_HOOK_IMPL);
		}

		ClassLoader classLoader = ClassLoaderUtil.getPortalClassLoader();

		_originalHook = (Hook)InstanceFactory.newInstance(
			classLoader, PropsValues.MAIL_HOOK_IMPL);

		_hook = _originalHook;
	}

	private static Log _log = LogFactoryUtil.getLog(HookFactory.class);

	private static volatile Hook _hook;
	private static Hook _originalHook;

}