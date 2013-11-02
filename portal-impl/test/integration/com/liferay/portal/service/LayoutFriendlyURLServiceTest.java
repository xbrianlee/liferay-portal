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

package com.liferay.portal.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutFriendlyURL;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalCallbackAwareExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.LayoutTestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.testng.Assert;

/**
 * @author Sergio González
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalCallbackAwareExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class LayoutFriendlyURLServiceTest {

	@Test
	public void testLocalizedSiteAddLayoutFriendlyURLs() throws Exception {
		Group group = GroupTestUtil.addGroup();

		Locale[] availableLocales =
			new Locale[] {LocaleUtil.US, LocaleUtil.SPAIN};

		group = GroupTestUtil.updateDisplaySettings(
			group.getGroupId(), availableLocales, LocaleUtil.SPAIN);

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		String name = ServiceTestUtil.randomString();

		nameMap.put(LocaleUtil.GERMANY, name);
		nameMap.put(LocaleUtil.US, name);
		nameMap.put(LocaleUtil.SPAIN, name);

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.GERMANY, "/germanurl");
		friendlyURLMap.put(LocaleUtil.SPAIN, "/spanishurl");
		friendlyURLMap.put(LocaleUtil.US, "/englishurl");

		Layout layout = LayoutTestUtil.addLayout(
			group.getGroupId(), false, nameMap, friendlyURLMap);

		List<LayoutFriendlyURL> layoutFriendlyURLs =
			LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
				layout.getPlid());

		Assert.assertEquals(availableLocales.length, layoutFriendlyURLs.size());

		String[] availableLanguageIds = LocaleUtil.toLanguageIds(
			availableLocales);

		for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
			if (!ArrayUtil.contains(
					availableLanguageIds, layoutFriendlyURL.getLanguageId())) {

				Assert.fail();
			}
		}
	}

	@Test
	public void testLocalizedSiteFetchLayoutFriendlyURL() throws Exception {
		Group group = GroupTestUtil.addGroup();

		Locale[] availableLocales =
			new Locale[] {LocaleUtil.US, LocaleUtil.SPAIN};

		Locale defaultLocale = LocaleUtil.SPAIN;

		group = GroupTestUtil.updateDisplaySettings(
			group.getGroupId(), availableLocales, defaultLocale);

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		String name = ServiceTestUtil.randomString();

		nameMap.put(LocaleUtil.SPAIN, name);
		nameMap.put(LocaleUtil.US, name);

		Map<Locale, String> friendlyURLMap = new HashMap<Locale, String>();

		friendlyURLMap.put(LocaleUtil.SPAIN, "/spanishurl");
		friendlyURLMap.put(LocaleUtil.US, "/englishurl");

		Layout layout = LayoutTestUtil.addLayout(
			group.getGroupId(), false, nameMap, friendlyURLMap);

		Locale locale = LocaleThreadLocal.getSiteDefaultLocale();

		try {
			LocaleThreadLocal.setSiteDefaultLocale(defaultLocale);

			LayoutFriendlyURL layoutFriendlyURL =
				LayoutFriendlyURLLocalServiceUtil.fetchLayoutFriendlyURL(
					layout.getPlid(),
					LocaleUtil.toLanguageId(LocaleUtil.GERMANY), true);

			Assert.assertEquals(
				"/spanishurl", layoutFriendlyURL.getFriendlyURL());
			Assert.assertEquals(
				LocaleUtil.toLanguageId(defaultLocale),
				layoutFriendlyURL.getLanguageId());
		}
		finally {
			LocaleThreadLocal.setSiteDefaultLocale(locale);
		}
	}

}