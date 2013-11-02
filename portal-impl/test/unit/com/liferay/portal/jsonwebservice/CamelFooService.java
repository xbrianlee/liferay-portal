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

package com.liferay.portal.jsonwebservice;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.servlet.HttpMethods;

/**
 * @author Igor Spasic
 */
public class CamelFooService {

	@JSONWebService("cool-new-world")
	public static void braveNewWorld() {
	}

	public static void hello() {
	}

	public static void helloWorld() {
	}

	@JSONWebService(method = HttpMethods.POST)
	public static String post(String value) {
		return "post " + value;
	}

}