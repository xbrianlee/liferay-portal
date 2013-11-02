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

package com.liferay.portal.security.pacl.test;

import com.liferay.portal.kernel.format.PhoneNumberFormatUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portal.security.pacl.test.hook.action.FailureStrutsAction;
import com.liferay.portal.security.pacl.test.hook.action.SuccessStrutsAction;
import com.liferay.portal.security.pacl.test.hook.indexer.OrganizationIndexerPostProcessor;
import com.liferay.portal.security.pacl.test.hook.indexer.UserIndexerPostProcessor;
import com.liferay.portlet.blogs.service.BlogsEntryLocalServiceUtil;
import com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class HookTest {

	@Test
	public void testIndexer1() throws Exception {
		Assert.assertFalse(OrganizationIndexerPostProcessor.isInstantiated());
	}

	@Test
	public void testIndexer2() throws Exception {
		Assert.assertTrue(UserIndexerPostProcessor.isInstantiated());
	}

	@Test
	public void testLanguage1() throws Exception {
		Assert.assertEquals(
			LanguageUtil.get(LocaleUtil.SPAIN, "stars"), "Estrellas");
	}

	@Test
	public void testLanguage2() throws Exception {
		Assert.assertEquals(
			LanguageUtil.get(LocaleUtil.GERMAN, "stars"), "Sterne");
	}

	@Test
	public void testLanguage3() throws Exception {
		Assert.assertEquals(
			LanguageUtil.get(LocaleUtil.BRAZIL, "stars"), "Ricardo Kaka");
	}

	@Test
	public void testLanguage4() throws Exception {
		Assert.assertEquals(
			LanguageUtil.get(LocaleUtil.PORTUGAL, "stars"),
			"Cristiano Ronaldo");
	}

	@Test
	public void testLanguage5() throws Exception {
		Assert.assertEquals(
			LanguageUtil.get(LocaleUtil.UK, "stars"), "David Beckham");
	}

	@Test
	public void testLanguage6() throws Exception {
		Assert.assertEquals(LanguageUtil.get(LocaleUtil.US, "stars"), "Stars");
	}

	@Test
	public void testPortalProperties1() throws Exception {
		Assert.assertFalse(LanguageUtil.isBetaLocale(LocaleUtil.US));
	}

	@Test
	public void testPortalProperties2() throws Exception {
		String phoneNumber = PhoneNumberFormatUtil.format("123");

		Assert.assertTrue(phoneNumber.startsWith("(TEST"));
	}

	@Test
	public void testServices1() throws Exception {
		Assert.assertTrue(
			BlogsEntryLocalServiceUtil.getBlogsEntriesCount() < 0);
	}

	@Test
	public void testServices2() throws Exception {
		Assert.assertTrue(
			BlogsStatsUserLocalServiceUtil.getBlogsStatsUsersCount() >= 0);
	}

	@Test
	public void testStruts1() throws Exception {
		Assert.assertFalse(FailureStrutsAction.isInstantiated());
	}

	@Test
	public void testStruts2() throws Exception {
		Assert.assertTrue(SuccessStrutsAction.isInstantiated());
	}

}