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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.model.UserTrackerPath;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class UserTrackerImpl extends UserTrackerBaseImpl {

	public UserTrackerImpl() {
	}

	@Override
	public void addPath(UserTrackerPath path) {
		try {
			_paths.add(path);
		}
		catch (ArrayIndexOutOfBoundsException aioobe) {
			if (_log.isWarnEnabled()) {
				_log.warn(aioobe);
			}
		}

		setModifiedDate(path.getPathDate());
	}

	@Override
	public int compareTo(UserTracker userTracker) {
		String userName1 = StringUtil.toLowerCase(getFullName());
		String userName2 = StringUtil.toLowerCase(userTracker.getFullName());

		int value = userName1.compareTo(userName2);

		if (value == 0) {
			value = getModifiedDate().compareTo(userTracker.getModifiedDate());
		}

		return value;
	}

	@Override
	public String getEmailAddress() {
		if (_emailAddress == null) {
			try {
				if (_user == null) {
					_user = UserLocalServiceUtil.getUserById(getUserId());
				}

				_emailAddress = _user.getEmailAddress();
			}
			catch (Exception e) {
			}
		}

		if (_emailAddress == null) {
			_emailAddress = StringPool.BLANK;
		}

		return _emailAddress;
	}

	@Override
	public String getFullName() {
		if (_fullName == null) {
			try {
				if (_user == null) {
					_user = UserLocalServiceUtil.getUserById(getUserId());
				}

				_fullName = _user.getFullName();
			}
			catch (Exception e) {
			}
		}

		if (_fullName == null) {
			_fullName = StringPool.BLANK;
		}

		return _fullName;
	}

	@Override
	public int getHits() {
		return _paths.size();
	}

	@Override
	public List<UserTrackerPath> getPaths() {
		return _paths;
	}

	private static Log _log = LogFactoryUtil.getLog(UserTrackerImpl.class);

	private String _emailAddress;
	private String _fullName;
	private List<UserTrackerPath> _paths = new ArrayList<UserTrackerPath>();
	private User _user;

}