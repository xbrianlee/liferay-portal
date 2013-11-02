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

package com.liferay.portlet.social.service;

import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.GroupTestUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.UserTestUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;
import com.liferay.portlet.social.util.SocialActivityHierarchyEntryThreadLocal;
import com.liferay.portlet.social.util.SocialActivityTestUtil;
import com.liferay.portlet.social.util.SocialConfigurationUtil;

import java.io.InputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * @author Zsolt Berentey
 */
public class BaseSocialActivityTestCase {

	@BeforeClass
	public static void setUpClass() throws Exception {
		_userClassNameId = PortalUtil.getClassNameId(User.class.getName());

		Class<?> clazz = SocialActivitySettingLocalServiceTest.class;

		InputStream inputStream = clazz.getResourceAsStream(
			"dependencies/liferay-social.xml");

		String xml = new String(FileUtil.getBytes(inputStream));

		SocialConfigurationUtil.read(
			clazz.getClassLoader(), new String[] {xml});
	}

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_actorUser = UserTestUtil.addUser("actor", _group.getGroupId());
		_creatorUser = UserTestUtil.addUser("creator", _group.getGroupId());

		_assetEntry = SocialActivityTestUtil.addAssetEntry(
			_creatorUser, _group, null);

		SocialActivityHierarchyEntryThreadLocal.clear();
	}

	@After
	public void tearDown() throws Exception {
		SocialActivityHierarchyEntryThreadLocal.clear();

		if (_actorUser != null) {
			UserLocalServiceUtil.deleteUser(_actorUser);

			_actorUser = null;
		}

		if (_assetEntry != null) {
			AssetEntryLocalServiceUtil.deleteEntry(_assetEntry);

			_assetEntry = null;
		}

		if (_creatorUser != null) {
			UserLocalServiceUtil.deleteUser(_creatorUser);

			_creatorUser = null;
		}

		if (_group != null) {
			GroupLocalServiceUtil.deleteGroup(_group);

			_group = null;
		}
	}

	protected static final String TEST_MODEL = "test-model";

	protected static User _actorUser;
	protected static AssetEntry _assetEntry;
	protected static User _creatorUser;
	protected static Group _group;
	protected static long _userClassNameId;

}