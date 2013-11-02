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

package com.liferay.portlet.asset.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portlet.asset.model.AssetTag;
import com.liferay.portlet.asset.model.AssetTagStats;
import com.liferay.portlet.asset.service.base.AssetTagStatsLocalServiceBaseImpl;

import java.util.List;

/**
 * Provides the local service for accessing, adding, deleting, and updating
 * asset tag statistics.
 *
 * @author Jorge Ferrer
 */
public class AssetTagStatsLocalServiceImpl
	extends AssetTagStatsLocalServiceBaseImpl {

	/**
	 * Adds an asset tag statistics instance.
	 *
	 * @param  tagId the primary key of the tag
	 * @param  classNameId the asset entry's class name ID
	 * @return the asset tag statistics instance
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetTagStats addTagStats(long tagId, long classNameId)
		throws SystemException {

		long tagStatsId = counterLocalService.increment();

		AssetTagStats tagStats = assetTagStatsPersistence.create(tagStatsId);

		tagStats.setTagId(tagId);
		tagStats.setClassNameId(classNameId);

		try {
			assetTagStatsPersistence.update(tagStats);
		}
		catch (SystemException se) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Add failed, fetch {tagId=" + tagId + ", classNameId=" +
						classNameId + "}");
			}

			tagStats = assetTagStatsPersistence.fetchByT_C(
				tagId, classNameId, false);

			if (tagStats == null) {
				throw se;
			}
		}

		return tagStats;
	}

	/**
	 * Deletes the asset tag statistics instance.
	 *
	 * @param  tagStats the asset tag statistics instance
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteTagStats(AssetTagStats tagStats) throws SystemException {
		assetTagStatsPersistence.remove(tagStats);
	}

	/**
	 * Deletes the asset tag statistics instance matching the tag statistics ID.
	 *
	 * @param  tagStatsId the primary key of the asset tag statistics instance
	 * @throws PortalException if the assetTagStats with the primary key could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteTagStats(long tagStatsId)
		throws PortalException, SystemException {

		AssetTagStats tagStats = assetTagStatsPersistence.findByPrimaryKey(
			tagStatsId);

		deleteTagStats(tagStats);
	}

	/**
	 * Deletes all asset tag statistics instances associated with the asset
	 * entry matching the class name ID.
	 *
	 * @param  classNameId the asset entry's class name ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteTagStatsByClassNameId(long classNameId)
		throws SystemException {

		List<AssetTagStats> tagStatsList =
			assetTagStatsPersistence.findByClassNameId(classNameId);

		for (AssetTagStats tagStats : tagStatsList) {
			deleteTagStats(tagStats);
		}
	}

	/**
	 * Deletes all asset tag statistics instances associated with the tag.
	 *
	 * @param  tagId the primary key of the tag
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void deleteTagStatsByTagId(long tagId) throws SystemException {
		List<AssetTagStats> tagStatsList = assetTagStatsPersistence.findByTagId(
			tagId);

		for (AssetTagStats tagStats : tagStatsList) {
			deleteTagStats(tagStats);
		}
	}

	/**
	 * Returns a range of all the asset tag statistics instances associated with
	 * the asset entry matching the class name ID.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end -
	 * start</code> instances. <code>start</code> and <code>end</code> are not
	 * primary keys, they are indexes in the result set. Thus, <code>0</code>
	 * refers to the first result in the set. Setting both <code>start</code>
	 * and <code>end</code> to {@link
	 * com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full
	 * result set.
	 * </p>
	 *
	 * @param  classNameId the asset entry's class name ID
	 * @param  start the lower bound of the range of results
	 * @param  end the upper bound of the range of results (not inclusive)
	 * @return the range of asset tag statistics associated with the asset entry
	 *         matching the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<AssetTagStats> getTagStats(long classNameId, int start, int end)
		throws SystemException {

		return assetTagStatsPersistence.findByClassNameId(
			classNameId, start, end);
	}

	/**
	 * Returns the asset tag statistics instance with the tag and asset entry
	 * matching the class name ID
	 *
	 * @param  tagId the primary key of the tag
	 * @param  classNameId the asset entry's class name ID
	 * @return Returns the asset tag statistics instance with the tag and asset
	 *         entry  matching the class name ID
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetTagStats getTagStats(long tagId, long classNameId)
		throws SystemException {

		AssetTagStats tagStats = assetTagStatsPersistence.fetchByT_C(
			tagId, classNameId);

		if (tagStats == null) {
			tagStats = assetTagStatsLocalService.addTagStats(
				tagId, classNameId);
		}

		return tagStats;
	}

	/**
	 * Updates the asset tag statistics instance.
	 *
	 * @param  tagId the primary key of the tag
	 * @param  classNameId the asset entry's class name ID
	 * @return the updated asset tag statistics instance
	 * @throws PortalException if an asset tag with the tag ID could not be
	 *         found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public AssetTagStats updateTagStats(long tagId, long classNameId)
		throws PortalException, SystemException {

		AssetTag tag = assetTagPersistence.findByPrimaryKey(tagId);

		int assetCount = assetTagFinder.countByG_C_N(
			tag.getGroupId(), classNameId, tag.getName());

		AssetTagStats tagStats = getTagStats(tagId, classNameId);

		tagStats.setAssetCount(assetCount);

		assetTagStatsPersistence.update(tagStats);

		return tagStats;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AssetTagStatsLocalServiceImpl.class);

}