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

package com.liferay.portal.layoutconfiguration.util.xml;

/**
 * @author Brian Wing Shun Chan
 */
public abstract class RuntimeLogic {

	public static final String CLOSE_2_TAG = "/>";

	public abstract String getClose1Tag();

	public String getClose2Tag() {
		return CLOSE_2_TAG;
	}

	public abstract String getOpenTag();

	public abstract String processXML(String xml) throws Exception;

}