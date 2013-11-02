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

package com.liferay.portal.kernel.audit;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;

/**
 * @author Michael C. Han
 */
public interface AuditRouter {

	@MessagingProxy(mode = ProxyMode.SYNC)
	public boolean isDeployed();

	@MessagingProxy(mode = ProxyMode.ASYNC)
	public void route(AuditMessage auditMessage) throws AuditException;

}