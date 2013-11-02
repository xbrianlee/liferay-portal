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

/**
 * @author Marcellus Tavares
 */
public interface ConditionFactory {

	public Junction conjunction();

	public Junction disjunction();

	public Condition eq(String name, Object value);

	public Condition gt(String name, Object value);

	public Condition gte(String name, Object value);

	public Condition in(String name, Object value);

	public Condition like(String name, Object value);

	public Condition lt(String name, Object value);

	public Condition lte(String name, Object value);

	public Condition ne(String name, Object value);

	public Condition notIn(String name, Object value);

}