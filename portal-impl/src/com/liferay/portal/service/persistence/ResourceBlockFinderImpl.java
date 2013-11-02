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

package com.liferay.portal.service.persistence;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ResourceBlock;
import com.liferay.portal.security.permission.ResourceBlockIdsBag;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import java.util.Iterator;

/**
 * @author Connor McKay
 */
public class ResourceBlockFinderImpl
	extends BasePersistenceImpl<ResourceBlock>
	implements ResourceBlockFinder {

	public static final String FIND_BY_C_G_N_R =
		ResourceBlockFinder.class.getName() + ".findByC_G_N_R";

	@Override
	public ResourceBlockIdsBag findByC_G_N_R(
			long companyId, long groupId, String name, long[] roleIds)
		throws SystemException {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(FIND_BY_C_G_N_R);

			sql = StringUtil.replace(
				sql, "[$ROLE_ID$]", StringUtil.merge(roleIds));

			SQLQuery q = session.createSQLQuery(sql);

			q.addScalar("resourceBlockId", Type.LONG);
			q.addScalar("actionIds", Type.LONG);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);
			qPos.add(groupId);
			qPos.add(name);

			ResourceBlockIdsBag resourceBlockIdsBag = new ResourceBlockIdsBag();

			Iterator<Object[]> itr = q.iterate();

			while (itr.hasNext()) {
				Object[] array = itr.next();

				Long resourceBlockId = (Long)array[0];
				Long actionIdsLong = (Long)array[1];

				resourceBlockIdsBag.addResourceBlockId(
					resourceBlockId, actionIdsLong);
			}

			return resourceBlockIdsBag;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}