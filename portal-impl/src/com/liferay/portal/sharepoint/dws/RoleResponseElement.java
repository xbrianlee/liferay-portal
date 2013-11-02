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
import com.liferay.portal.model.Role;

/**
 * @author Bruno Farache
 */
public class RoleResponseElement implements ResponseElement {

	public RoleResponseElement(Role role) {
		_name = role.getName();
		_description = role.getDescription();
		_type = role.getTypeLabel();
	}

	@Override
	public void addElement(Element rootEl) {
		Element el = rootEl.addElement("Role");

		el.addAttribute("Name", _name);
		el.addAttribute("Description", _description);
		el.addAttribute("Type", _type);
	}

	private String _description;
	private String _name;
	private String _type;

}