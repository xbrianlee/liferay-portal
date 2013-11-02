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

package com.liferay.portal.kernel.dao.orm;

/**
 * @author Brian Wing Shun Chan
 */
public interface ProjectionFactory {

	public Projection alias(Projection projection, String alias);

	public Projection avg(String propertyName);

	public Projection count(String propertyName);

	public Projection countDistinct(String propertyName);

	public Projection distinct(Projection projection);

	public Projection groupProperty(String propertyName);

	public Projection max(String propertyName);

	public Projection min(String propertyName);

	public ProjectionList projectionList();

	public Projection property(String propertyName);

	public Projection rowCount();

	public Projection sqlProjection(
		String sql, String[] columnAliases, Type[] types);

	public Projection sum(String propertyName);

}