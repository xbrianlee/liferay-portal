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

package com.liferay.portal.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutCloneFactory {

	public static LayoutClone getInstance() {
		if (_layoutClone == null) {
			if (Validator.isNotNull(PropsValues.LAYOUT_CLONE_IMPL)) {
				if (_log.isDebugEnabled()) {
					_log.debug("Instantiate " + PropsValues.LAYOUT_CLONE_IMPL);
				}

				ClassLoader classLoader =
					ClassLoaderUtil.getPortalClassLoader();

				try {
					Class<?> clazz = classLoader.loadClass(
						PropsValues.LAYOUT_CLONE_IMPL);

					_layoutClone = (LayoutClone)clazz.newInstance();
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
		}
		else {
			if (_log.isDebugEnabled()) {
				_log.debug("Return " + _layoutClone.getClass().getName());
			}
		}

		return _layoutClone;
	}

	private static Log _log = LogFactoryUtil.getLog(LayoutCloneFactory.class);

	private static LayoutClone _layoutClone;

}