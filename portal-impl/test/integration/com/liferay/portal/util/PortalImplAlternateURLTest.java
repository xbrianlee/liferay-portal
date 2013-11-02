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

package com.liferay.portal.util;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.theme.ThemeDisplay;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Sergio González
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class PortalImplAlternateURLTest {

	@Test
	public void testCustomPortalLocaleAlternateURL() throws Exception {
		testAlternateURL(null, null, LocaleUtil.SPAIN, "/es");
	}

	@Test
	public void testDefaultPortalLocaleAlternateURL() throws Exception {
		testAlternateURL(null, null, LocaleUtil.US, StringPool.BLANK);
	}

	@Test
	public void testLocalizedSiteCustomSiteLocaleAlternateURL()
		throws Exception {

		testAlternateURL(
			new Locale[] {LocaleUtil.US, LocaleUtil.SPAIN, LocaleUtil.GERMANY},
			LocaleUtil.SPAIN, LocaleUtil.US, "/en");
	}

	@Test
	public void testLocalizedSiteDefaultSiteLocaleAlternateURL()
		throws Exception {

		testAlternateURL(
			new Locale[] {LocaleUtil.US, LocaleUtil.SPAIN, LocaleUtil.GERMANY},
			LocaleUtil.SPAIN, LocaleUtil.SPAIN, StringPool.BLANK);
	}

	protected String generateURL(
		String languageId, String groupFriendlyURL, String layoutFriendlyURL) {

		StringBundler sb = new StringBundler(5);

		sb.append("http://localhost");
		sb.append(languageId);
		sb.append(PropsValues.LAYOUT_FRIENDLY_URL_PUBLIC_SERVLET_MAPPING);
		sb.append(groupFriendlyURL);
		sb.append(layoutFriendlyURL);

		return sb.toString();
	}

	protected ThemeDisplay getThemeDisplay(Group group) throws Exception {
		ThemeDisplay themeDisplay = new ThemeDisplay();

		Company company = CompanyLocalServiceUtil.getCompany(
			TestPropsValues.getCompanyId());

		themeDisplay.setCompany(company);

		themeDisplay.setLayoutSet(group.getPublicLayoutSet());

		return themeDisplay;
	}

	protected void testAlternateURL(
			Locale[] groupAvailableLocales, Locale groupDefaultLocale,
			Locale alternateLocale, String expectedI18nPath)
		throws Exception {

		Group group = GroupTestUtil.addGroup();

		group = GroupTestUtil.updateDisplaySettings(
			group.getGroupId(), groupAvailableLocales, groupDefaultLocale);

		Layout layout = LayoutTestUtil.addLayout(
			group.getGroupId(), "welcome", false);

		String canonicalURL = generateURL(
			StringPool.BLANK, group.getFriendlyURL(), layout.getFriendlyURL());

		String actualAlternateURL = PortalUtil.getAlternateURL(
			canonicalURL, getThemeDisplay(group), alternateLocale, layout);

		String expectedAlternateURL = generateURL(
			expectedI18nPath, group.getFriendlyURL(), layout.getFriendlyURL());

		Assert.assertEquals(expectedAlternateURL, actualAlternateURL);
	}

}