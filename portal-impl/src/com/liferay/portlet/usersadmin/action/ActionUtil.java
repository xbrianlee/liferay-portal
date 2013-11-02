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

package com.liferay.portlet.usersadmin.action;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.Address;
import com.liferay.portal.model.EmailAddress;
import com.liferay.portal.model.OrgLabor;
import com.liferay.portal.model.Organization;
import com.liferay.portal.model.Phone;
import com.liferay.portal.model.Website;
import com.liferay.portal.service.AddressServiceUtil;
import com.liferay.portal.service.EmailAddressServiceUtil;
import com.liferay.portal.service.OrgLaborServiceUtil;
import com.liferay.portal.service.OrganizationServiceUtil;
import com.liferay.portal.service.PhoneServiceUtil;
import com.liferay.portal.service.WebsiteServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;

import javax.portlet.PortletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class ActionUtil {

	public static void getAddress(HttpServletRequest request) throws Exception {
		long addressId = ParamUtil.getLong(request, "addressId");

		Address address = null;

		if (addressId > 0) {
			address = AddressServiceUtil.getAddress(addressId);
		}

		request.setAttribute(WebKeys.ADDRESS, address);
	}

	public static void getAddress(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getAddress(request);
	}

	public static void getEmailAddress(HttpServletRequest request)
		throws Exception {

		long emailAddressId = ParamUtil.getLong(request, "emailAddressId");

		EmailAddress emailAddress = null;

		if (emailAddressId > 0) {
			emailAddress = EmailAddressServiceUtil.getEmailAddress(
				emailAddressId);
		}

		request.setAttribute(WebKeys.EMAIL_ADDRESS, emailAddress);
	}

	public static void getEmailAddress(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getEmailAddress(request);
	}

	public static void getOrganization(HttpServletRequest request)
		throws Exception {

		long organizationId = ParamUtil.getLong(request, "organizationId");

		Organization organization = null;

		if (organizationId > 0) {
			organization = OrganizationServiceUtil.getOrganization(
				organizationId);
		}

		request.setAttribute(WebKeys.ORGANIZATION, organization);
	}

	public static void getOrganization(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getOrganization(request);
	}

	public static void getOrgLabor(HttpServletRequest request)
		throws Exception {

		long orgLaborId = ParamUtil.getLong(request, "orgLaborId");

		OrgLabor orgLabor = null;

		if (orgLaborId > 0) {
			orgLabor = OrgLaborServiceUtil.getOrgLabor(orgLaborId);
		}

		request.setAttribute(WebKeys.ORG_LABOR, orgLabor);
	}

	public static void getOrgLabor(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getOrgLabor(request);
	}

	public static void getPhone(HttpServletRequest request) throws Exception {
		long phoneId = ParamUtil.getLong(request, "phoneId");

		Phone phone = null;

		if (phoneId > 0) {
			phone = PhoneServiceUtil.getPhone(phoneId);
		}

		request.setAttribute(WebKeys.PHONE, phone);
	}

	public static void getPhone(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getPhone(request);
	}

	public static void getWebsite(HttpServletRequest request) throws Exception {
		long websiteId = ParamUtil.getLong(request, "websiteId");

		Website website = null;

		if (websiteId > 0) {
			website = WebsiteServiceUtil.getWebsite(websiteId);
		}

		request.setAttribute(WebKeys.WEBSITE, website);
	}

	public static void getWebsite(PortletRequest portletRequest)
		throws Exception {

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			portletRequest);

		getWebsite(request);
	}

}