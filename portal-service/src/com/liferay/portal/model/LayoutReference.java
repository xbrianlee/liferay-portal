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

package com.liferay.portal.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class LayoutReference implements Serializable {

	public LayoutReference() {
	}

	public LayoutReference(LayoutSoap layoutSoap, String portletId) {
		_layoutSoap = layoutSoap;
		_portletId = portletId;
	}

	public LayoutSoap getLayoutSoap() {
		return _layoutSoap;
	}

	public String getPortletId() {
		return _portletId;
	}

	public void setLayoutSoap(LayoutSoap layoutSoap) {
		_layoutSoap = layoutSoap;
	}

	public void setPortletId(String portletId) {
		_portletId = portletId;
	}

	private LayoutSoap _layoutSoap;
	private String _portletId;

}