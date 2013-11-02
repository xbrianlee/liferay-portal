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

package com.liferay.support.websphere;

import com.ibm.ws.security.policy.DynamicPolicy;
import com.ibm.ws.security.policy.DynamicPolicyFactory;

import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.ProtectionDomain;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public class DynamicPolicyHelper {

	protected void _start() {
		_originalDynamicPolicy = DynamicPolicyFactory.getInstance();

		final DynamicPolicy originalDynamicPolicy = _originalDynamicPolicy;

		DynamicPolicy dynamicPolicy = new DynamicPolicy() {

			@Override
			public ProtectionDomain getProtectionDomain(CodeSource codeSource) {
				if (originalDynamicPolicy == null) {
					return null;
				}

				return originalDynamicPolicy.getProtectionDomain(codeSource);
			}

			@Override
			public PermissionCollection getPermissions(
				CodeSource codeSource, Map map) {

				Policy policy = Policy.getPolicy();

				return policy.getPermissions(codeSource);
			}

			@Override
			public void getSecurityPolicy(Map map1, Map map2) {
				if (originalDynamicPolicy == null) {
					return;
				}

				originalDynamicPolicy.getSecurityPolicy(map1, map2);
			}

			@Override
			public void removePolicy(Map map) {
				if (originalDynamicPolicy == null) {
					return;
				}

				originalDynamicPolicy.removePolicy(map);
			}

			@Override
			public void setupPolicy(Map map) {
				if (originalDynamicPolicy == null) {
					return;
				}

				originalDynamicPolicy.setupPolicy(map);
			}

		};

		DynamicPolicyFactory.setInstance(dynamicPolicy);
	}

	private static DynamicPolicyHelper _instance = new DynamicPolicyHelper();

	static {
		_instance._start();
	}

	private DynamicPolicy _originalDynamicPolicy;

}