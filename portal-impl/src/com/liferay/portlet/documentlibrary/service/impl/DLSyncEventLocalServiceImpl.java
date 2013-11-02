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

package com.liferay.portlet.documentlibrary.service.impl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.documentlibrary.model.DLSyncEvent;
import com.liferay.portlet.documentlibrary.service.base.DLSyncEventLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Dennis Ju
 */
public class DLSyncEventLocalServiceImpl
	extends DLSyncEventLocalServiceBaseImpl {

	@Override
	public DLSyncEvent addDLSyncEvent(String event, String type, long typePK)
		throws SystemException {

		DLSyncEvent dlSyncEvent = dlSyncEventPersistence.fetchByTypePK(typePK);

		if (dlSyncEvent == null) {
			long dlSyncEventId = counterLocalService.increment();

			dlSyncEvent = dlSyncEventPersistence.create(dlSyncEventId);

			dlSyncEvent.setType(type);
			dlSyncEvent.setTypePK(typePK);
		}

		dlSyncEvent.setModifiedTime(System.currentTimeMillis());
		dlSyncEvent.setEvent(event);

		return dlSyncEventPersistence.update(dlSyncEvent);
	}

	@Override
	public void deleteDLSyncEvents() throws SystemException {
		dlSyncEventPersistence.removeAll();
	}

	@Override
	public List<DLSyncEvent> getDLSyncEvents(long modifiedTime)
		throws SystemException {

		return dlSyncEventPersistence.findByModifiedTime(modifiedTime);
	}

	@Override
	public List<DLSyncEvent> getLatestDLSyncEvents() throws SystemException {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			DLSyncEvent.class);

		Property property = PropertyFactoryUtil.forName("modifiedTime");

		DynamicQuery modifiedTimeDynamicQuery =
			DynamicQueryFactoryUtil.forClass(DLSyncEvent.class);

		Projection projection = ProjectionFactoryUtil.max("modifiedTime");

		modifiedTimeDynamicQuery.setProjection(projection);

		dynamicQuery.add(property.eq(modifiedTimeDynamicQuery));

		return dlSyncEventPersistence.findWithDynamicQuery(dynamicQuery);
	}

}