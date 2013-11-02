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

package com.liferay.portlet.usersadmin.search;

import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.portlet.PortletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Jorge Ferrer
 */
public class OrganizationDisplayTerms extends DisplayTerms {

	public static final String CITY = "city";

	public static final String COUNTRY_ID = "countryId";

	public static final String NAME = "name";

	public static final String PARENT_ORGANIZATION_ID = "parentOrganizationId";

	public static final String REGION_ID = "regionId";

	public static final String STREET = "street";

	public static final String TYPE = "type";

	public static final String ZIP = "zip";

	public OrganizationDisplayTerms(PortletRequest portletRequest) {
		super(portletRequest);

		city = ParamUtil.getString(portletRequest, CITY);
		countryId = ParamUtil.getLong(portletRequest, COUNTRY_ID);
		name = ParamUtil.getString(portletRequest, NAME);
		parentOrganizationId = ParamUtil.getLong(
			portletRequest, PARENT_ORGANIZATION_ID);
		regionId = ParamUtil.getLong(portletRequest, REGION_ID);
		street = ParamUtil.getString(portletRequest, STREET);
		type = ParamUtil.getString(portletRequest, TYPE);
		zip = ParamUtil.getString(portletRequest, ZIP);
	}

	public String getCity() {
		return city;
	}

	public long getCountryId() {
		return countryId;
	}

	public String getName() {
		return name;
	}

	public long getParentOrganizationId() {
		return parentOrganizationId;
	}

	public long getRegionId() {
		return regionId;
	}

	public String getStreet() {
		return street;
	}

	public String getType() {
		return type;
	}

	public String getZip() {
		return zip;
	}

	public void setType(String type) {
		this.type = type;
	}

	protected String city;
	protected long countryId;
	protected String name;
	protected long parentOrganizationId;
	protected long regionId;
	protected String street;
	protected String type;
	protected String zip;

}