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

package com.liferay.portal.dao.orm.hibernate;

import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionList;

/**
 * @author Brian Wing Shun Chan
 */
public class ProjectionListImpl
	extends ProjectionImpl implements ProjectionList {

	public ProjectionListImpl(
		org.hibernate.criterion.ProjectionList projectionList) {

		super(projectionList);

		_projectionList = projectionList;
	}

	@Override
	public ProjectionList add(Projection projection) {
		ProjectionImpl projectionImpl = (ProjectionImpl)projection;

		_projectionList.add(projectionImpl.getWrappedProjection());

		return this;
	}

	@Override
	public ProjectionList add(Projection projection, String alias) {
		ProjectionImpl projectionImpl = (ProjectionImpl)projection;

		_projectionList.add(projectionImpl.getWrappedProjection(), alias);

		return this;
	}

	public org.hibernate.criterion.ProjectionList getWrappedProjectionList() {
		return _projectionList;
	}

	private org.hibernate.criterion.ProjectionList _projectionList;

}