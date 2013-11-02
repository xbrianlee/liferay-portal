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
 * @author Raymond Augé
 */
public interface ModelListener<T> {

	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onAfterCreate(T model) throws ModelListenerException;

	public void onAfterRemove(T model) throws ModelListenerException;

	public void onAfterRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onAfterUpdate(T model) throws ModelListenerException;

	public void onBeforeAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onBeforeCreate(T model) throws ModelListenerException;

	public void onBeforeRemove(T model) throws ModelListenerException;

	public void onBeforeRemoveAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException;

	public void onBeforeUpdate(T model) throws ModelListenerException;

}