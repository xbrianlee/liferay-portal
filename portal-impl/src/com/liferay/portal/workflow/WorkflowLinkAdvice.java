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

package com.liferay.portal.workflow;

import com.liferay.portal.kernel.workflow.RequiredWorkflowDefinitionException;
import com.liferay.portal.service.WorkflowDefinitionLinkLocalServiceUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

/**
 * @author Brian Wing Shun Chan
 */
public class WorkflowLinkAdvice {

	public Object invoke(ProceedingJoinPoint proceedingJoinPoint)
		throws Throwable {

		Signature signature = proceedingJoinPoint.getSignature();

		String methodName = signature.getName();

		Object[] arguments = proceedingJoinPoint.getArgs();

		if (methodName.equals(_UPDATE_ACTIVE)) {
			long companyId = (Long)arguments[0];
			String name = (String)arguments[2];
			int version = (Integer)arguments[3];
			boolean active = (Boolean)arguments[4];

			if (!active) {
				int workflowDefinitionLinksCount =
					WorkflowDefinitionLinkLocalServiceUtil.
						getWorkflowDefinitionLinksCount(
							companyId, name, version);

				if (workflowDefinitionLinksCount >= 1) {
					throw new RequiredWorkflowDefinitionException();
				}
			}
		}

		return proceedingJoinPoint.proceed();
	}

	private static final String _UPDATE_ACTIVE = "updateActive";

}