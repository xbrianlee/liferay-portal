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

package com.liferay.portal.kernel.nio.intraband;

import com.liferay.portal.kernel.concurrent.ThreadPoolExecutor;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Shuyang Zhou
 */
@Aspect
public class PortalExecutorManagerUtilAdvice {

	@Around(
		"execution(* com.liferay.portal.kernel.executor." +
			"PortalExecutorManagerUtil.getPortalExecutor(String))")
	public ThreadPoolExecutor getPortalExecutor() {
		return new ThreadPoolExecutor(0, 1) {

			@Override
			public void execute(Runnable runnable) {
				runnable.run();
			}

		};
	}

}