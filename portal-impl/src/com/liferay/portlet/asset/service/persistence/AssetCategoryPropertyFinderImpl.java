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

package com.liferay.portlet.asset.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.asset.model.AssetCategoryProperty;
import com.liferay.portlet.asset.model.impl.AssetCategoryPropertyImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class AssetCategoryPropertyFinderImpl
	extends BasePersistenceImpl<AssetCategoryProperty>
	implements AssetCategoryPropertyFinder {

	public static final String COUNT_BY_G_K =
		AssetCategoryPropertyFinder.class.getName() + ".countByG_K";

	public static final String FIND_BY_G_K =
		AssetCategoryPropertyFinder.class.getName() + ".findByG_K";

	@Override
	public int countByG_K(long groupId, String key) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(COUNT_BY_G_K);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar(COUNT_COLUMN_NAME, Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(key);

			Iterator<Long> itr = q.iterate();

			if (itr.hasNext()) {
				Long count = itr.next();

				if (count != null) {
					return count.intValue();
				}
			}

			return 0;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<AssetCategoryProperty> findByG_K(long groupId, String key)
		throws SystemException {

		return findByG_K(groupId, key, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	@Override
	public List<AssetCategoryProperty> findByG_K(
			long groupId, String key, int start, int end)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_G_K);

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("categoryPropertyValue", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(groupId);
			qPos.add(key);

			List<AssetCategoryProperty> categoryProperties =
				new ArrayList<AssetCategoryProperty>();

			Iterator<String> itr = (Iterator<String>)QueryUtil.iterate(
				q, getDialect(), start, end);

			while (itr.hasNext()) {
				String value = itr.next();

				AssetCategoryProperty categoryProperty =
					new AssetCategoryPropertyImpl();

				categoryProperty.setKey(key);
				categoryProperty.setValue(value);

				categoryProperties.add(categoryProperty);
			}

			return categoryProperties;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}