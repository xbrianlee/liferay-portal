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

package com.liferay.portal.model;

import com.liferay.portal.ModelListenerException;

/**
 * @author Brian Wing Shun Chan
 */
public class BaseModelListener<T extends BaseModel<T>>
	implements ModelListener<T> {

	@Override
	@SuppressWarnings("unused")
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onAfterCreate(T model) throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onAfterRemove(T model) throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onAfterUpdate(T model) throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onBeforeCreate(T model) throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onBeforeRemove(T model) throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {
	}

	@Override
	@SuppressWarnings("unused")
	public void onBeforeUpdate(T model) throws ModelListenerException {
	}

}