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

package com.liferay.portal.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.BaseModelListener;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutSet;
import com.liferay.portal.model.LayoutSetPrototype;
import com.liferay.portal.service.LayoutSetPrototypeLocalServiceUtil;
import com.liferay.portal.service.persistence.LayoutSetPrototypeUtil;

import java.util.Date;

/**
 * @author Raymond Augé
 */
public class LayoutSetPrototypeLayoutSetListener
	extends BaseModelListener<LayoutSet> {

	@Override
	public void onAfterCreate(LayoutSet layoutSet) {
		updateLayoutSetPrototype(layoutSet, layoutSet.getModifiedDate());
	}

	@Override
	public void onAfterRemove(LayoutSet layoutSet) {
		updateLayoutSetPrototype(layoutSet, new Date());
	}

	@Override
	public void onAfterUpdate(LayoutSet layoutSet) {
		updateLayoutSetPrototype(layoutSet, layoutSet.getModifiedDate());
	}

	protected void updateLayoutSetPrototype(
		LayoutSet layoutSet, Date modifiedDate) {

		try {
			Group group = layoutSet.getGroup();

			if (!group.isLayoutSetPrototype()) {
				return;
			}

			LayoutSetPrototype layoutSetPrototype =
				LayoutSetPrototypeLocalServiceUtil.getLayoutSetPrototype(
					group.getClassPK());

			layoutSetPrototype.setModifiedDate(modifiedDate);

			UnicodeProperties settingsProperties =
				layoutSet.getSettingsProperties();

			settingsProperties.remove("merge-fail-count");

			LayoutSetPrototypeUtil.update(layoutSetPrototype);
		}
		catch (Exception e) {
			_log.error(e, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LayoutSetPrototypeLayoutSetListener.class);

}