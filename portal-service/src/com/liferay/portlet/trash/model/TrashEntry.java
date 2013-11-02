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

package com.liferay.portlet.trash.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the TrashEntry service. Represents a row in the &quot;TrashEntry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see TrashEntryModel
 * @see com.liferay.portlet.trash.model.impl.TrashEntryImpl
 * @see com.liferay.portlet.trash.model.impl.TrashEntryModelImpl
 * @generated
 */
@ProviderType
public interface TrashEntry extends TrashEntryModel, PersistedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to {@link com.liferay.portlet.trash.model.impl.TrashEntryImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public com.liferay.portlet.trash.model.TrashEntry getRootEntry();

	public com.liferay.portal.kernel.util.UnicodeProperties getTypeSettingsProperties();

	public java.lang.String getTypeSettingsProperty(java.lang.String key);

	public java.lang.String getTypeSettingsProperty(java.lang.String key,
		java.lang.String defaultValue);

	public boolean isTrashEntry(java.lang.Class<?> clazz, long classPK);

	public boolean isTrashEntry(java.lang.String className, long classPK);

	public boolean isTrashEntry(
		com.liferay.portal.model.TrashedModel trashedModel);

	public void setRootEntry(
		com.liferay.portlet.trash.model.TrashEntry rootEntry);

	public void setTypeSettingsProperties(
		com.liferay.portal.kernel.util.UnicodeProperties typeSettingsProperties);
}