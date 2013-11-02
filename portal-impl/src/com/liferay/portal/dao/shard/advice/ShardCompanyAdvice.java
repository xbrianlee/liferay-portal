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

package com.liferay.portal.dao.shard.advice;

import com.liferay.portal.NoSuchCompanyException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Shard;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.ShardLocalServiceUtil;
import com.liferay.portal.util.PropsValues;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Michael Young
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
public class ShardCompanyAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		Method method = methodInvocation.getMethod();
		String methodName = method.getName();

		Object[] arguments = methodInvocation.getArguments();

		String shardName = PropsValues.SHARD_DEFAULT_NAME;

		if (methodName.equals("addCompany")) {
			String webId = (String)arguments[0];
			String virtualHostname = (String)arguments[1];
			String mx = (String)arguments[2];
			shardName = (String)arguments[3];

			shardName = _shardAdvice.getCompanyShardName(
				webId, virtualHostname, mx, shardName);

			arguments[3] = shardName;
		}
		else if (methodName.equals("checkCompany")) {
			String webId = (String)arguments[0];

			if (!webId.equals(PropsValues.COMPANY_DEFAULT_WEB_ID)) {
				if (arguments.length == 3) {
					String mx = (String)arguments[1];
					shardName = (String)arguments[2];

					shardName = _shardAdvice.getCompanyShardName(
						webId, null, mx, shardName);

					arguments[2] = shardName;
				}

				try {
					Company company = CompanyLocalServiceUtil.getCompanyByWebId(
						webId);

					shardName = company.getShardName();
				}
				catch (NoSuchCompanyException nsce) {
				}
			}
		}
		else if (methodName.startsWith("update")) {
			long companyId = (Long)arguments[0];

			Shard shard = ShardLocalServiceUtil.getShard(
				Company.class.getName(), companyId);

			shardName = shard.getName();
		}
		else {
			return methodInvocation.proceed();
		}

		if (_log.isInfoEnabled()) {
			_log.info(
				"Setting company service to shard " + shardName + " for " +
					methodInvocation.toString());
		}

		Object returnValue = null;

		_shardAdvice.pushCompanyService(shardName);

		try {
			returnValue = method.invoke(methodInvocation.getThis(), arguments);
		}
		finally {
			_shardAdvice.popCompanyService();
		}

		return returnValue;
	}

	public void setShardAdvice(ShardAdvice shardAdvice) {
		_shardAdvice = shardAdvice;
	}

	private static Log _log = LogFactoryUtil.getLog(ShardCompanyAdvice.class);

	private ShardAdvice _shardAdvice;

}