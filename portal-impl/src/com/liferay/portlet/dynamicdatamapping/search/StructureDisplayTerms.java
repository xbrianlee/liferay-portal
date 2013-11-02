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

package com.liferay.portlet.dynamicdatamapping.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Eduardo Lundgren
 */
public class StructureDisplayTerms extends DisplayTerms {

	public static final String CLASS_NAME_ID = "classNameId";

	public static final String DESCRIPTION = "description";

	public static final String NAME = "name";

	public static final String STORAGE_TYPE = "storageType";

	public StructureDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		classNameId = ParamUtil.getLong(portletRequest, CLASS_NAME_ID);
		description = ParamUtil.getString(portletRequest, DESCRIPTION);
		name = ParamUtil.getString(portletRequest, NAME);
		storageType = ParamUtil.getString(portletRequest, STORAGE_TYPE);
	}

	public long getClassNameId() {
		return classNameId;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getStorageType() {
		return storageType;
	}

	protected long classNameId;
	protected String description;
	protected String name;
	protected String storageType;

}