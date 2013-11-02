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

package com.liferay.portlet.trash.model.impl;

import com.liferay.portal.kernel.trash.TrashHandler;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.model.ClassedModel;
import com.liferay.portal.model.TrashedModel;
import com.liferay.portlet.trash.model.TrashEntry;

/**
 * @author Zsolt Berentey
 */
public class TrashEntryImpl extends TrashEntryBaseImpl {

	@Override
	public TrashEntry getRootEntry() {
		return _rootEntry;
	}

	@Override
	public String getTypeSettings() {
		if (_typeSettingsProperties == null) {
			return super.getTypeSettings();
		}
		else {
			return _typeSettingsProperties.toString();
		}
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties() {
		if (_typeSettingsProperties == null) {
			_typeSettingsProperties = new UnicodeProperties(true);

			_typeSettingsProperties.fastLoad(super.getTypeSettings());
		}

		return _typeSettingsProperties;
	}

	@Override
	public String getTypeSettingsProperty(String key) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key);
	}

	@Override
	public String getTypeSettingsProperty(String key, String defaultValue) {
		UnicodeProperties typeSettingsProperties = getTypeSettingsProperties();

		return typeSettingsProperties.getProperty(key, defaultValue);
	}

	@Override
	public boolean isTrashEntry(Class<?> clazz, long classPK) {
		if (clazz == null) {
			return false;
		}

		return isTrashEntry(clazz.getName(), classPK);
	}

	@Override
	public boolean isTrashEntry(String className, long classPK) {
		if (className.equals(getClassName()) && (classPK == getClassPK())) {
			return true;
		}

		return false;
	}

	@Override
	public boolean isTrashEntry(TrashedModel trashedModel) {
		TrashHandler trashHandler = trashedModel.getTrashHandler();

		if (trashHandler == null) {
			return false;
		}

		if (!(trashedModel instanceof ClassedModel)) {
			return false;
		}

		return trashHandler.isTrashEntry(this, (ClassedModel)trashedModel);
	}

	@Override
	public void setRootEntry(TrashEntry rootEntry) {
		_rootEntry = rootEntry;
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettingsProperties = null;

		super.setTypeSettings(typeSettings);
	}

	@Override
	public void setTypeSettingsProperties(
		UnicodeProperties typeSettingsProperties) {

		_typeSettingsProperties = typeSettingsProperties;

		super.setTypeSettings(_typeSettingsProperties.toString());
	}

	private TrashEntry _rootEntry;
	private UnicodeProperties _typeSettingsProperties;

}