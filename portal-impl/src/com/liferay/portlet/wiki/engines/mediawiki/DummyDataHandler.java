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

package com.liferay.portlet.wiki.engines.mediawiki;

import java.sql.Connection;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jamwiki.DataHandler;
import org.jamwiki.model.Category;
import org.jamwiki.model.Interwiki;
import org.jamwiki.model.LogItem;
import org.jamwiki.model.Namespace;
import org.jamwiki.model.RecentChange;
import org.jamwiki.model.Role;
import org.jamwiki.model.RoleMap;
import org.jamwiki.model.Topic;
import org.jamwiki.model.TopicType;
import org.jamwiki.model.TopicVersion;
import org.jamwiki.model.VirtualWiki;
import org.jamwiki.model.Watchlist;
import org.jamwiki.model.WikiFile;
import org.jamwiki.model.WikiFileVersion;
import org.jamwiki.model.WikiGroup;
import org.jamwiki.model.WikiUser;
import org.jamwiki.utils.Pagination;

/**
 * @author Jonathan Potter
 */
public class DummyDataHandler implements DataHandler {

	@Override
	public boolean authenticate(String username, String password) {
		return false;
	}

	@Override
	public boolean canMoveTopic(Topic fromTopic, String destination) {
		return false;
	}

	@Override
	public void deleteInterwiki(Interwiki interwiki) {
	}

	@Override
	public void deleteTopic(Topic topic, TopicVersion topicVersion) {
	}

	@Override
	public void executeUpgradeQuery(String prop, Connection conn) {
	}

	@Override
	public void executeUpgradeUpdate(String prop, Connection conn) {
	}

	@Override
	public List<Category> getAllCategories(
		String virtualWiki, Pagination pagination) {

		return null;
	}

	@Override
	public List<Role> getAllRoles() {
		return null;
	}

	@Override
	public List<String> getAllTopicNames(
		String virtualWiki, boolean includeDeleted) {

		return null;
	}

	@Override
	public List<WikiFileVersion> getAllWikiFileVersions(
		String virtualWiki, String topicName, boolean descending) {

		return null;
	}

	@Override
	public List<LogItem> getLogItems(
		String virtualWiki, int logType, Pagination pagination,
		boolean descending) {

		return null;
	}

	@Override
	public List<RecentChange> getRecentChanges(
		String virtualWiki, Pagination pagination, boolean descending) {

		return null;
	}

	@Override
	public List<RoleMap> getRoleMapByLogin(String loginFragment) {
		return null;
	}

	@Override
	public List<RoleMap> getRoleMapByRole(String roleName) {
		return null;
	}

	@Override
	public List<Role> getRoleMapGroup(String groupName) {
		return null;
	}

	@Override
	public List<RoleMap> getRoleMapGroups() {
		return null;
	}

	@Override
	public List<Role> getRoleMapUser(String login) {
		return null;
	}

	@Override
	public List<RecentChange> getTopicHistory(
		String virtualWiki, String topicName, Pagination pagination,
		boolean descending) {

		return null;
	}

	@Override
	public List<String> getTopicsAdmin(
		String virtualWiki, Pagination pagination) {

		return null;
	}

	@Override
	public List<RecentChange> getUserContributions(
		String virtualWiki, String userString, Pagination pagination,
		boolean descending) {

		return null;
	}

	@Override
	public List<VirtualWiki> getVirtualWikiList() {
		return null;
	}

	@Override
	public Watchlist getWatchlist(String virtualWiki, int userId) {
		return null;
	}

	@Override
	public List<RecentChange> getWatchlist(
		String virtualWiki, int userId, Pagination pagination) {

		return null;
	}

	@Override
	public List<Category> lookupCategoryTopics(
		String virtualWiki, String categoryName) {

		return null;
	}

	@Override
	public Map<String, String> lookupConfiguration() {
		return null;
	}

	@Override
	public Interwiki lookupInterwiki(String interwikiPrefix) {
		return null;
	}

	@Override
	public List<Interwiki> lookupInterwikis() {
		return null;
	}

	@Override
	public Namespace lookupNamespace(
		String virtualWiki, String namespaceString) {

		return null;
	}

	@Override
	public Namespace lookupNamespaceById(int namespaceId) {
		return null;
	}

	@Override
	public List<Namespace> lookupNamespaces() {
		return null;
	}

	@Override
	public Topic lookupTopic(
		String virtualWiki, String topicName, boolean deleteOK,
		Connection conn) {

		return null;
	}

	@Override
	public Topic lookupTopicById(String virtualWiki, int topicId) {
		return null;
	}

	@Override
	public Map<Integer, String> lookupTopicByType(
		String virtualWiki, TopicType topicType1, TopicType topicType2,
		Integer namespaceId, Pagination pagination) {

		return null;
	}

	@Override
	public int lookupTopicCount(String virtualWiki, Integer namespaceId) {
		return 0;
	}

	@Override
	public List<String> lookupTopicLinkOrphans(
		String virtualWiki, int namespaceId) {

		return null;
	}

	@Override
	public List<String> lookupTopicLinks(String virtualWiki, String topicName) {
		return null;
	}

	@Override
	public String lookupTopicName(String virtualWiki, String topicName) {
		return null;
	}

	@Override
	public TopicVersion lookupTopicVersion(int topicVersionId) {
		return null;
	}

	@Override
	public Integer lookupTopicVersionNextId(int topicVersionId) {
		return null;
	}

	@Override
	public VirtualWiki lookupVirtualWiki(String virtualWikiName) {
		return null;
	}

	@Override
	public WikiFile lookupWikiFile(String virtualWiki, String topicName) {
		return null;
	}

	@Override
	public int lookupWikiFileCount(String virtualWiki) {
		return 0;
	}

	@Override
	public WikiGroup lookupWikiGroup(String groupName) {
		return null;
	}

	@Override
	public WikiUser lookupWikiUser(int userId) {
		return null;
	}

	@Override
	public WikiUser lookupWikiUser(String username) {
		return null;
	}

	@Override
	public int lookupWikiUserCount() {
		return 0;
	}

	@Override
	public String lookupWikiUserEncryptedPassword(String username) {
		return null;
	}

	@Override
	public List<String> lookupWikiUsers(Pagination pagination) {
		return null;
	}

	@Override
	public void moveTopic(
		Topic fromTopic, TopicVersion fromVersion, String destination) {
	}

	@Override
	public void orderTopicVersions(
		Topic topic, List<Integer> topicVersionIdList) {
	}

	@Override
	public void reloadLogItems() {
	}

	@Override
	public void reloadRecentChanges() {
	}

	@Override
	public void setup(
		Locale locale, WikiUser user, String username,
		String encryptedPassword) {
	}

	@Override
	public void setupSpecialPages(
		Locale locale, WikiUser user, VirtualWiki virtualWiki) {
	}

	@Override
	public void undeleteTopic(Topic topic, TopicVersion topicVersion) {
	}

	@Override
	public void updateSpecialPage(
		Locale locale, String virtualWiki, String topicName,
		String userDisplay) {
	}

	@Override
	public void writeConfiguration(Map<String, String> configuration) {
	}

	@Override
	public void writeFile(WikiFile wikiFile, WikiFileVersion wikiFileVersion) {
	}

	@Override
	public void writeInterwiki(Interwiki interwiki) {
	}

	@Override
	public void writeNamespace(
		Namespace mainNamespace, Namespace commentsNamespace) {
	}

	@Override
	public void writeNamespaceTranslations(
		List<Namespace> namespaces, String virtualWiki) {
	}

	@Override
	public void writeRole(Role role, boolean update) {
	}

	@Override
	public void writeRoleMapGroup(int groupId, List<String> roles) {
	}

	@Override
	public void writeRoleMapUser(String username, List<String> roles) {
	}

	@Override
	public void writeTopic(
		Topic topic, TopicVersion topicVersion,
		LinkedHashMap<String, String> categories, List<String> links) {
	}

	@Override
	public void writeTopicVersion(Topic topic, TopicVersion topicVersion) {
	}

	@Override
	public void writeVirtualWiki(VirtualWiki virtualWiki) {
	}

	@Override
	public void writeWatchlistEntry(
		Watchlist watchlist, String virtualWiki, String topicName, int userId) {
	}

	@Override
	public void writeWikiGroup(WikiGroup group) {
	}

	@Override
	public void writeWikiUser(
		WikiUser user, String username, String encryptedPassword) {
	}

}