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

package com.liferay.portal.sharepoint.dws;

import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.model.User;

/**
 * @author Bruno Farache
 */
public class MemberResponseElement implements ResponseElement {

	public MemberResponseElement(User user, boolean member) {
		_id = user.getScreenName();
		_name = user.getFullName();
		_loginName = user.getScreenName();
		_email = user.getEmailAddress();
		_domainGroup = false;
		_member = member;
		_siteAdmin = false;
	}

	@Override
	public void addElement(Element rootEl) {
		String user = "User";

		if (_member) {
			user = "Member";
		}

		Element el = rootEl.addElement(user);

		el.addElement("ID").setText(_id);
		el.addElement("Name").setText(_name);
		el.addElement("LoginName").setText(_loginName);
		el.addElement("Email").setText(_email);
		el.addElement("IsDomainGroup").setText(String.valueOf(_domainGroup));
		el.addElement("IsSiteAdmin").setText(String.valueOf(_siteAdmin));
	}

	private boolean _domainGroup;
	private String _email;
	private String _id;
	private String _loginName;
	private boolean _member;
	private String _name;
	private boolean _siteAdmin;

}