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

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface DynamicQuery {

	public DynamicQuery add(Criterion criterion);

	public DynamicQuery addOrder(Order order);

	public void compile(Session session);

	@SuppressWarnings("rawtypes")
	public List list();

	@SuppressWarnings("rawtypes")
	public List list(boolean unmodifiable);

	public void setLimit(int start, int end);

	public DynamicQuery setProjection(Projection projection);

	public DynamicQuery setProjection(
		Projection projection, boolean useColumnAlias);

}