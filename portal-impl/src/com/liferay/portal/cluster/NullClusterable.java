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

import com.liferay.portal.kernel.cluster.ClusterInvokeAcceptor;
import com.liferay.portal.kernel.cluster.Clusterable;

import java.lang.annotation.Annotation;

/**
 * @author Shuyang Zhou
 */
@SuppressWarnings("all")
public class NullClusterable implements Clusterable {

	public static final Clusterable NULL_CLUSTERABLE = new NullClusterable();

	@Override
	public Class<? extends ClusterInvokeAcceptor> acceptor() {
		return null;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return Clusterable.class;
	}

	@Override
	public boolean onMaster() {
		return false;
	}

	private NullClusterable() {
	}

}