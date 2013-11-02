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

package com.liferay.portal.spring.aop;

import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author Shuyang Zhou
 */
public class MethodInterceptorsBag {

	public MethodInterceptorsBag(
		List<MethodInterceptor> classLevelMethodInterceptors,
		List<MethodInterceptor> mergedMethodInterceptors) {

		_classLevelMethodInterceptors = classLevelMethodInterceptors;
		_mergedMethodInterceptors = mergedMethodInterceptors;
	}

	public List<MethodInterceptor> getClassLevelMethodInterceptors() {
		return _classLevelMethodInterceptors;
	}

	public List<MethodInterceptor> getMergedMethodInterceptors() {
		return _mergedMethodInterceptors;
	}

	public void setClassLevelMethodInterceptors(
		List<MethodInterceptor> classLevelMethodInterceptors) {

		_classLevelMethodInterceptors = classLevelMethodInterceptors;
	}

	public void setMergedMethodInterceptors(
		List<MethodInterceptor> mergedMethodInterceptors) {

		_mergedMethodInterceptors = mergedMethodInterceptors;
	}

	private List<MethodInterceptor> _classLevelMethodInterceptors;
	private List<MethodInterceptor> _mergedMethodInterceptors;

}