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

package com.liferay.portal.model.impl;

import com.liferay.portal.LayoutFriendlyURLException;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.LayoutConstants;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalCallbackAwareExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.TestPropsValues;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sergio González
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalCallbackAwareExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class LayoutFriendlyURLTest {

	@Test
	@Transactional
	public void testDifferentFriendlyURLDifferentLocaleDifferentGroup()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/casa");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}

		group = GroupTestUtil.addGroup();

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void testDifferentFriendlyURLDifferentLocaleDifferentLayoutSet()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/casa");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}

		group = GroupTestUtil.addGroup();

		try {
			addLayout(group.getGroupId(), true, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void testDifferentFriendlyURLDifferentLocaleSameLayout()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/casa");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void testSameFriendlyURLDifferentLocaleDifferentGroup()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/home");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}

		group = GroupTestUtil.addGroup();

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void testSameFriendlyURLDifferentLocaleDifferentLayout()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/casa");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}

		friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/home");
		friendlyURLMap.put(LocaleUtil.US, "/welcome");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);

			Assert.fail();
		}
		catch (LayoutFriendlyURLException lfurle) {
		}
	}

	@Test
	@Transactional
	public void testSameFriendlyURLDifferentLocaleDifferentLayoutSet()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/home");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}

		try {
			addLayout(group.getGroupId(), true, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void testSameFriendlyURLDifferentLocaleSameLayout()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/home");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}
	}

	@Test
	@Transactional
	public void testSameFriendlyURLSameLocaleDifferentLayout()
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/casa");
		friendlyURLMap.put(LocaleUtil.US, "/home");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);
		}
		catch (LayoutFriendlyURLException lfurle) {
			Assert.fail();
		}

		friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/casa");
		friendlyURLMap.put(LocaleUtil.US, "/house");

		try {
			addLayout(group.getGroupId(), false, friendlyURLMap);

			Assert.fail();
		}
		catch (LayoutFriendlyURLException lfurle) {
		}
	}

	protected void addLayout(
			long groupId, boolean privateLayout,
			Map<Locale, String> friendlyURLMap)
		throws Exception {

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			groupId);

		LayoutLocalServiceUtil.addLayout(
			TestPropsValues.getUserId(), groupId, privateLayout,
			LayoutConstants.DEFAULT_PARENT_LAYOUT_ID,
			ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(),
			ServiceTestUtil.randomLocaleStringMap(),
			LayoutConstants.TYPE_PORTLET, StringPool.BLANK, false,
			friendlyURLMap, serviceContext);
	}

}