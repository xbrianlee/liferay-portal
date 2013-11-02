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

package com.liferay.portal.action;

import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portlet.messageboards.service.MBMessageServiceUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import junit.framework.TestCase;

import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Igor Spasic
 */
public class JSONServiceActionTest extends TestCase {

	@Override
	public void setUp() throws Exception {
		JSONFactoryUtil jsonFactoryUtil = new JSONFactoryUtil();

		jsonFactoryUtil.setJSONFactory(new JSONFactoryImpl());
	}

	@Test
	public void testGetArgumentValue() throws Exception {
		JSONServiceAction jsonServiceAction = new JSONServiceAction();

		String[] parameters = {
			"groupId", "categoryId", "subject", "body", "format",
			"inputStreamOVPs", "anonymous", "priority", "allowPingbacks",
			"serviceContext"
		};

		Object[] methodAndParameterTypes =
			jsonServiceAction.getMethodAndParameterTypes(
				MBMessageServiceUtil.class, "addMessage", parameters,
				new String[0]);

		Method method = (Method)methodAndParameterTypes[0];
		Type[] parameterTypes = (Type[])methodAndParameterTypes[1];

		MockHttpServletRequest mockHttpServletRequest =
			new MockHttpServletRequest();

		mockHttpServletRequest.setParameter("inputStreamOVPs", "[]");

		Object value = jsonServiceAction.getArgValue(
			mockHttpServletRequest, MBMessageServiceUtil.class,
			method.getName(), parameters[5], parameterTypes[5]);

		assertEquals("[]", value.toString());

		mockHttpServletRequest.setParameter(
			"inputStreamOVPs",
			"{'class' : 'com.liferay.portal.kernel.dao.orm.EntityCacheUtil'}");

		value = jsonServiceAction.getArgValue(
			mockHttpServletRequest, MBMessageServiceUtil.class,
			method.getName(), parameters[5], parameterTypes[5]);

		assertEquals(
			"{class=com.liferay.portal.kernel.dao.orm.EntityCacheUtil}",
			value.toString());
	}

}