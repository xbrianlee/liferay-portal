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

import com.liferay.portal.kernel.util.UnicodeProperties;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public interface LayoutType extends Serializable {

	public Layout getLayout();

	public UnicodeProperties getTypeSettingsProperties();

	public String getTypeSettingsProperty(String key);

	public String getTypeSettingsProperty(String key, String defaultValue);

	public void setLayout(Layout layout);

	public void setTypeSettingsProperty(String key, String value);

}