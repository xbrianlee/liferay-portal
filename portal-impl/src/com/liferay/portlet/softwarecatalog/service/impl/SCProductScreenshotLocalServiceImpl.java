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

package com.liferay.portlet.softwarecatalog.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.softwarecatalog.model.SCProductScreenshot;
import com.liferay.portlet.softwarecatalog.service.base.SCProductScreenshotLocalServiceBaseImpl;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class SCProductScreenshotLocalServiceImpl
	extends SCProductScreenshotLocalServiceBaseImpl {

	@Override
	public void deleteProductScreenshot(SCProductScreenshot productScreenshot)
		throws PortalException, SystemException {

		// Product screenshot

		scProductScreenshotPersistence.remove(productScreenshot);

		// Images

		imageLocalService.deleteImage(productScreenshot.getThumbnailId());
		imageLocalService.deleteImage(productScreenshot.getFullImageId());
	}

	@Override
	public void deleteProductScreenshots(long productEntryId)
		throws PortalException, SystemException {

		List<SCProductScreenshot> productScreenshots =
			scProductScreenshotPersistence.findByProductEntryId(productEntryId);

		for (SCProductScreenshot productScreenshot : productScreenshots) {
			deleteProductScreenshot(productScreenshot);
		}
	}

	@Override
	public SCProductScreenshot getProductScreenshot(
			long productEntryId, int priority)
		throws PortalException, SystemException {

		return scProductScreenshotPersistence.findByP_P(
			productEntryId, priority);
	}

	@Override
	public SCProductScreenshot getProductScreenshotByFullImageId(
			long fullImageId)
		throws PortalException, SystemException {

		return scProductScreenshotPersistence.findByFullImageId(fullImageId);
	}

	@Override
	public SCProductScreenshot getProductScreenshotByThumbnailId(
			long thumbnailId)
		throws PortalException, SystemException {

		return scProductScreenshotPersistence.findByThumbnailId(thumbnailId);
	}

	@Override
	public List<SCProductScreenshot> getProductScreenshots(long productEntryId)
		throws SystemException {

		return scProductScreenshotPersistence.findByProductEntryId(
			productEntryId);
	}

}