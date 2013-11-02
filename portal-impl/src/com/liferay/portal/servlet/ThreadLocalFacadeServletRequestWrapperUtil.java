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

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.util.ObjectValuePair;

import java.io.Closeable;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Shuyang Zhou
 */
public class ThreadLocalFacadeServletRequestWrapperUtil {

	public static <T extends ServletRequest> ObjectValuePair<T, Closeable>
		inject(T servletRequest) {

		ServletRequestWrapper previousServletRequestWrapper = null;
		ServletRequest currentServletRequest = servletRequest;

		while (currentServletRequest != null) {
			if (!(currentServletRequest instanceof ServletRequestWrapper)) {
				break;
			}

			Class<?> clazz = currentServletRequest.getClass();

			if (_stopperClassNames.contains(clazz.getName())) {
				break;
			}

			previousServletRequestWrapper =
				(ServletRequestWrapper)currentServletRequest;

			ServletRequestWrapper servletRequestWrapper =
				(ServletRequestWrapper)currentServletRequest;

			currentServletRequest = servletRequestWrapper.getRequest();
		}

		ServletRequestWrapper servletRequestWrapper = null;

		if (currentServletRequest instanceof HttpServletRequest) {
			servletRequestWrapper =
				new ThreadLocalFacadeHttpServletRequestWrapper(
					previousServletRequestWrapper,
					(HttpServletRequest)currentServletRequest);
		}
		else {
			servletRequestWrapper = new ThreadLocalFacadeServletRequestWrapper(
				previousServletRequestWrapper, currentServletRequest);
		}

		if (previousServletRequestWrapper != null) {
			previousServletRequestWrapper.setRequest(servletRequestWrapper);
		}
		else {
			servletRequest = (T)servletRequestWrapper;
		}

		Closeable closeable = (Closeable)servletRequestWrapper;

		return new ObjectValuePair<T, Closeable>(servletRequest, closeable);
	}

	public void setStopperClassNames(Set<String> stopperClassNames) {
		_stopperClassNames.clear();

		_stopperClassNames.addAll(stopperClassNames);
	}

	private static Set<String> _stopperClassNames = new HashSet<String>();

}