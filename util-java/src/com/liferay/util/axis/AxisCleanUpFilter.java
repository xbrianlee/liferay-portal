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

package com.liferay.util.axis;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.ReflectionUtil;

import java.lang.reflect.Field;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.axis.utils.cache.MethodCache;

/**
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 */
public class AxisCleanUpFilter extends BaseFilter {

	@Override
	protected Log getLog() {
		return _log;
	}

	@Override
	protected void processFilter(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
		throws Exception {

		try {
			processFilter(
				AxisCleanUpFilter.class, request, response, filterChain);
		}
		finally {
			try {
				ThreadLocal<?> cacheThreadLocal =
					(ThreadLocal<?>)_cacheField.get(null);

				if (cacheThreadLocal != null) {
					cacheThreadLocal.remove();
				}
			}
			catch (Exception e) {
				_log.error(e, e);
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AxisCleanUpFilter.class);

	private static Field _cacheField;

	static {
		try {
			_cacheField = ReflectionUtil.getDeclaredField(
				MethodCache.class, "cache");
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

}