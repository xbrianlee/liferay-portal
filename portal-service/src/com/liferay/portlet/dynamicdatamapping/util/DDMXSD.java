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

package com.liferay.portlet.dynamicdatamapping.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.Field;
import com.liferay.portlet.dynamicdatamapping.storage.Fields;

import java.util.Locale;

import javax.servlet.jsp.PageContext;

/**
 * @author Eduardo Lundgren
 * @author Brian Wing Shun Chan
 */
public interface DDMXSD {

	public String getFieldHTML(
			PageContext pageContext, Element element, Fields fields,
			String portletNamespace, String namespace, String mode,
			boolean readOnly, Locale locale)
		throws Exception;

	public String getFieldHTMLByName(
			PageContext pageContext, long classNameId, long classPK,
			String fieldName, Fields fields, String portletNamespace,
			String namespace, String mode, boolean readOnly, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, DDMStructure ddmStructure, Fields fields,
			String portletNamespace, String namespace, boolean readOnly,
			Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, DDMTemplate ddmTemplate, Fields fields,
			String portletNamespace, String namespace, boolean readOnly,
			Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String portletNamespace, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String portletNamespace, String namespace, boolean readOnly,
			Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String portletNamespace, String namespace, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, Fields fields,
			String portletNamespace, String namespace, String mode,
			boolean readOnly, Locale locale)
		throws Exception;

	public String getHTML(
			PageContext pageContext, String xml, String portletNamespace,
			Locale locale)
		throws Exception;

	public JSONArray getJSONArray(DDMStructure structure, String xsd)
		throws PortalException, SystemException;

	public JSONArray getJSONArray(Document document) throws PortalException;

	public JSONArray getJSONArray(Element element) throws PortalException;

	public JSONArray getJSONArray(String xml)
		throws PortalException, SystemException;

	public String getSimpleFieldHTML(
			PageContext pageContext, Element element, Field field,
			String portletNamespace, String namespace, String mode,
			boolean readOnly, Locale locale)
		throws Exception;

	public String getSimpleFieldHTMLByName(
			PageContext pageContext, long classNameId, long classPK,
			Field field, String portletNamespace, String namespace, String mode,
			boolean readOnly, Locale locale)
		throws Exception;

	public String getXSD(long classNameId, long classPK)
		throws PortalException, SystemException;

}