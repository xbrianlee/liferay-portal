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

package com.liferay.portlet.asset.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalCallbackAwareExecutionTestListener;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.asset.model.AssetVocabulary;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
@Transactional
public class AssetVocabularyServiceTest {

	@Before
	public void setUp() {
		_locale = LocaleThreadLocal.getSiteDefaultLocale();
	}

	@After
	public void tearDown() {
		LocaleThreadLocal.setSiteDefaultLocale(_locale);
	}

	@Test
	public void testLocalizedSiteAddDefaultVocabulary() throws Exception {
		Group group = GroupTestUtil.addGroup();

		LocaleThreadLocal.setSiteDefaultLocale(LocaleUtil.SPAIN);

		AssetVocabulary assetVocabulary =
			AssetVocabularyLocalServiceUtil.addDefaultVocabulary(
				group.getGroupId());

		Assert.assertEquals(
			PropsValues.ASSET_VOCABULARY_DEFAULT,
			assetVocabulary.getTitle(LocaleUtil.US, true));
	}

	@Test
	public void testLocalizedSiteAddLocalizedVocabulary() throws Exception {
		LocaleThreadLocal.setSiteDefaultLocale(LocaleUtil.SPAIN);

		String title = ServiceTestUtil.randomString();

		Map<Locale, String> titleMap = new HashMap<Locale, String>();

		titleMap.put(LocaleUtil.US, title + "_US");
		titleMap.put(LocaleUtil.SPAIN, title + "_ES");

		String description = ServiceTestUtil.randomString();

		Map<Locale, String> descriptionMap = new HashMap<Locale, String>();

		descriptionMap.put(LocaleUtil.SPAIN, description + "_ES");
		descriptionMap.put(LocaleUtil.US, description + "_US");

		Group group = GroupTestUtil.addGroup();

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		AssetVocabulary assetVocabulary =
			AssetVocabularyLocalServiceUtil.addVocabulary(
				TestPropsValues.getUserId(), StringPool.BLANK, titleMap,
				descriptionMap, StringPool.BLANK, serviceContext);

		Assert.assertEquals(
			titleMap.get(LocaleUtil.SPAIN), assetVocabulary.getName());
		Assert.assertEquals(
			titleMap.get(LocaleUtil.SPAIN),
			assetVocabulary.getTitle(LocaleUtil.GERMANY, true));
		Assert.assertEquals(
			titleMap.get(LocaleUtil.SPAIN),
			assetVocabulary.getTitle(LocaleUtil.SPAIN, true));
		Assert.assertEquals(
			titleMap.get(LocaleUtil.US),
			assetVocabulary.getTitle(LocaleUtil.US, true));
		Assert.assertEquals(
			descriptionMap.get(LocaleUtil.SPAIN),
			assetVocabulary.getDescription(LocaleUtil.GERMANY, true));
		Assert.assertEquals(
			descriptionMap.get(LocaleUtil.SPAIN),
			assetVocabulary.getDescription(LocaleUtil.SPAIN, true));
		Assert.assertEquals(
			descriptionMap.get(LocaleUtil.US),
			assetVocabulary.getDescription(LocaleUtil.US, true));
	}

	@Test
	public void testLocalizedSiteAddVocabulary() throws Exception {
		LocaleThreadLocal.setSiteDefaultLocale(LocaleUtil.SPAIN);

		String title = ServiceTestUtil.randomString();

		Group group = GroupTestUtil.addGroup();

		ServiceContext serviceContext = ServiceTestUtil.getServiceContext(
			group.getGroupId());

		AssetVocabulary assetVocabulary =
			AssetVocabularyLocalServiceUtil.addVocabulary(
				TestPropsValues.getUserId(), title, serviceContext);

		Assert.assertEquals(
			title, assetVocabulary.getTitle(LocaleUtil.US, true));
		Assert.assertEquals(title, assetVocabulary.getName());
	}

	private Locale _locale;

}