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

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQL;
import com.liferay.portal.kernel.dao.orm.PortalCustomSQLUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.pacl.permission.PortalRuntimePermission;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.security.pacl.PACLExecutionTestListener;
import com.liferay.portal.security.pacl.PACLIntegrationJUnitTestRunner;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.journalcontent.util.JournalContentUtil;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Raymond Augé
 */
@ExecutionTestListeners(listeners = {PACLExecutionTestListener.class})
@RunWith(PACLIntegrationJUnitTestRunner.class)
public class BeanPropertyTest {

	@Test
	public void testGet1() throws Exception {
		try {
			PortalRuntimePermission.checkGetBeanProperty(HttpUtil.class);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testGet2() throws Exception {
		try {
			JournalContentUtil.getJournalContent();

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testGet3() throws Exception {
		try {
			LanguageUtil.getLanguage();
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testGet4() throws Exception {
		try {
			PortalRuntimePermission.checkGetBeanProperty(PortalUtil.class);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testGet5() throws Exception {
		try {
			LanguageUtil.getLocale("en_US");
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

	@Test
	public void testSet1() throws Exception {
		try {
			EntityCache entityCache = EntityCacheUtil.getEntityCache();

			EntityCacheUtil entityCacheUtil = new EntityCacheUtil();

			entityCacheUtil.setEntityCache(entityCache);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testSet2() throws Exception {
		try {
			FinderCacheUtil finderCacheUtil = new FinderCacheUtil();

			FinderCache finderCache = FinderCacheUtil.getFinderCache();

			finderCacheUtil.setFinderCache(finderCache);

			Assert.fail();
		}
		catch (SecurityException se) {
		}
	}

	@Test
	public void testSet3() throws Exception {
		try {
			PortalCustomSQLUtil portalCustomSQLUtil = new PortalCustomSQLUtil();

			PortalCustomSQL portalCustomSQL =
				PortalCustomSQLUtil.getPortalCustomSQL();

			portalCustomSQLUtil.setPortalCustomSQL(portalCustomSQL);
		}
		catch (SecurityException se) {
			Assert.fail();
		}
	}

}