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

package com.liferay.portal.audit;

import com.liferay.portal.kernel.audit.AuditMessage;
import com.liferay.portal.kernel.audit.AuditRouter;
import com.liferay.portal.kernel.messaging.proxy.BaseProxyBean;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;

/**
 * @author Michael C. Han
 */
@DoPrivileged
public class AuditRouterProxyBean extends BaseProxyBean implements AuditRouter {

	@Override
	public boolean isDeployed() {
		return false;
	}

	@Override
	public void route(AuditMessage auditMessage) {
		throw new UnsupportedOperationException();
	}

}