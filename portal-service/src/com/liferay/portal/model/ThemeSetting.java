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

/**
 * @author Julio Camarero
 */
public interface ThemeSetting {

	public String[] getOptions();

	public String getScript();

	public String getType();

	public String getValue();

	public boolean isConfigurable();

	public void setConfigurable(boolean configurable);

	public void setOptions(String[] options);

	public void setScript(String script);

	public void setType(String type);

	public void setValue(String value);

}