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

package com.liferay.portlet.dynamicdatamapping.storage.query;

import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;

/**
 * @author Marcellus Tavares
 */
public class ConditionFactoryUtil {

	public static Junction conjunction() {
		return getConditionFactory().conjunction();
	}

	public static Junction disjunction() {
		return getConditionFactory().disjunction();
	}

	public static Condition eq(String name, Object value) {
		return getConditionFactory().eq(name, value);
	}

	public static ConditionFactory getConditionFactory() {
		PortalRuntimePermission.checkGetBeanProperty(
			ConditionFactoryUtil.class);

		return _conditionFactory;
	}

	public static Condition gt(String name, Object value) {
		return getConditionFactory().gt(name, value);
	}

	public static Condition gte(String name, Object value) {
		return getConditionFactory().gte(name, value);
	}

	public static Condition in(String name, Object value) {
		return getConditionFactory().in(name, value);
	}

	public static Condition like(String name, Object value) {
		return getConditionFactory().like(name, value);
	}

	public static Condition lt(String name, Object value) {
		return getConditionFactory().lt(name, value);
	}

	public static Condition lte(String name, Object value) {
		return getConditionFactory().lte(name, value);
	}

	public static Condition ne(String name, Object value) {
		return getConditionFactory().ne(name, value);
	}

	public static Condition notIn(String name, Object value) {
		return getConditionFactory().notIn(name, value);
	}

	public void setConditionFactory(ConditionFactory conditionFactory) {
		PortalRuntimePermission.checkSetBeanProperty(getClass());

		_conditionFactory = conditionFactory;
	}

	private static ConditionFactory _conditionFactory;

}