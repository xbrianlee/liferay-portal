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

package com.liferay.portal.cluster;

import com.liferay.portal.kernel.resiliency.spi.SPIUtil;
import com.liferay.portal.spring.aop.ChainableMethodAdviceInjector;
import com.liferay.portal.util.PropsValues;

/**
 * @author Shuyang Zhou
 */
public class ClusterableChainableMethodAdviceInjector
	extends ChainableMethodAdviceInjector {

	@Override
	public void inject() {
		setInjectCondition(PropsValues.CLUSTER_LINK_ENABLED);
		setNewChainableMethodAdvice(new ClusterableAdvice());

		super.inject();

		if (SPIUtil.isSPI()) {
			setInjectCondition(true);
			setNewChainableMethodAdvice(new SPIClusterableAdvice());

			super.inject();
		}
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	public void setServletContextName(String servletContextName) {
	}

}