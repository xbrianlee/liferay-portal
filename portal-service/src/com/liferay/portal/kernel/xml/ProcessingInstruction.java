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

package com.liferay.portal.kernel.xml;

import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public interface ProcessingInstruction extends Node {

	public String getTarget();

	@Override
	public String getText();

	public String getValue(String name);

	public Map<String, String> getValues();

	public boolean removeValue(String name);

	public void setTarget(String target);

	public void setValue(String name, String value);

	public void setValues(Map<String, String> data);

}