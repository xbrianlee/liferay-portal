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

import java.io.Serializable;

import java.util.Properties;

/**
 * @author Brian Wing Shun Chan
 */
public interface ColorScheme extends Comparable<ColorScheme>, Serializable {

	public String getColorSchemeId();

	public String getColorSchemeImagesPath();

	public String getColorSchemeThumbnailPath();

	public String getCssClass();

	public boolean getDefaultCs();

	public String getName();

	public String getSetting(String key);

	public String getSettings();

	public Properties getSettingsProperties();

	public boolean isDefaultCs();

	public void setColorSchemeImagesPath(String colorSchemeImagesPath);

	public void setCssClass(String cssClass);

	public void setDefaultCs(boolean defaultCs);

	public void setName(String name);

	public void setSettings(String settings);

	public void setSettingsProperties(Properties settingsProperties);

}