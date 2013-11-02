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

package com.liferay.portal.layoutconfiguration.util;

import com.liferay.portal.kernel.util.DefaultThreadLocalBinder;
import com.liferay.portal.kernel.util.ServerDetector;
import com.liferay.portal.util.ClassLoaderUtil;

/**
 * @author Shuyang Zhou
 */
public class JettyThreadLocalBinder extends DefaultThreadLocalBinder {

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!ServerDetector.isJetty()) {
			return;
		}

		ClassLoader classLoader = ClassLoaderUtil.getContextClassLoader();

		classLoader = classLoader.getParent();

		setClassLoader(classLoader);

		super.afterPropertiesSet();

		ParallelRenderThreadLocalBinderUtil.setThreadLocalBinder(this);
	}

}