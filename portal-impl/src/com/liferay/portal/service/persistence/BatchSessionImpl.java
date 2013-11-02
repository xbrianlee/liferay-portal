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

import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.util.InitialThreadLocal;
import com.liferay.portal.model.BaseModel;

/**
 * @author     Raymond Augé
 * @author     Brian Wing Shun Chan
 * @deprecated As of 6.2.0, see LPS-30598.
 */
public class BatchSessionImpl implements BatchSession {

	@Override
	public void delete(Session session, BaseModel<?> model)
		throws ORMException {

		if (!session.contains(model)) {
			model = (BaseModel<?>)session.get(
				model.getClass(), model.getPrimaryKeyObj());
		}

		if (model != null) {
			session.delete(model);
		}
	}

	@Override
	public boolean isEnabled() {
		return _enabled.get();
	}

	@Override
	public void setEnabled(boolean enabled) {
		_enabled.set(enabled);
	}

	@Override
	public void update(Session session, BaseModel<?> model, boolean merge)
		throws ORMException {

		if (model.isNew()) {
			session.save(model);

			model.setNew(false);
		}
		else {
			session.merge(model);
		}
	}

	private static ThreadLocal<Boolean> _enabled =
		new InitialThreadLocal<Boolean>(
			BatchSessionImpl.class + "._enabled", false);

}