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

package com.liferay.portal.kernel.googleapps;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public interface GGroupManager {

	public void addGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException;

	public void addGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException;

	public void deleteGGroup(String emailAddress) throws GoogleAppsException;

	public void deleteGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException;

	public void deleteGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException;

	public GGroup getGGroup(String emailAddress) throws GoogleAppsException;

	public GGroupMember getGGroupMember(
			String groupEmailAddress, String memberEmailAddress)
		throws GoogleAppsException;

	public List<GGroupMember> getGGroupMembers(String emailAddress)
		throws GoogleAppsException;

	public GGroupOwner getGGroupOwner(
			String groupEmailAddress, String ownerEmailAddress)
		throws GoogleAppsException;

	public List<GGroupOwner> getGGroupOwners(String emailAddress)
		throws GoogleAppsException;

	public List<GGroup> getGGroups() throws GoogleAppsException;

	public List<GGroup> getGGroups(long userId, boolean directOnly)
		throws GoogleAppsException;

	public void updateDescription(String emailAddress, String description)
		throws GoogleAppsException;

}