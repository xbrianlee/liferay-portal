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
public interface GUserManager {

	public void addGUser(
			long userId, String password, String firstName, String lastName)
		throws GoogleAppsException;

	public void deleteGUser(long userId) throws GoogleAppsException;

	public GUser getGUser(long userId) throws GoogleAppsException;

	public GUser getGUser(String emailAddress) throws GoogleAppsException;

	public List<GUser> getGUsers() throws GoogleAppsException;

	public void updateActive(long userId, boolean active)
		throws GoogleAppsException;

	public void updatePassword(long userId, String password)
		throws GoogleAppsException;

}