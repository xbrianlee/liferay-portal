<%--
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
--%>

<%@ include file="/html/portlet/xsl_content/init.jsp" %>

<%
try {
	String variablePropertyKey = StringPool.BLANK;
	String variablePropertyValue = StringPool.BLANK;

	xmlUrl = StringUtil.replace(xmlUrl,"@portal_url@", themeDisplay.getPortalURL());
	xslUrl = StringUtil.replace(xslUrl,"@portal_url@", themeDisplay.getPortalURL());

	int bracketBegin = xmlUrl.indexOf("[");
	int bracketEnd = -1;

	if (bracketBegin > -1) {
		bracketEnd = xmlUrl.indexOf("]", bracketBegin);

		if ((bracketEnd > -1) && ((bracketEnd - bracketBegin) > 0)) {
			String[] compilerTagNames = ParamUtil.getParameterValues(request, "tags");

			if (compilerTagNames.length > 0) {
				String category = null;
				String propertyName = null;

				variablePropertyKey = xmlUrl.substring(bracketBegin + 1, bracketEnd);

				category = variablePropertyKey;

				int pos = variablePropertyKey.indexOf(StringPool.PERIOD);

				if (pos != -1) {
					category = variablePropertyKey.substring(0, pos);
					propertyName = variablePropertyKey.substring(pos + 1);
				}

				for (String tagName : compilerTagNames) {
					try {
						AssetTag assetTag = AssetTagLocalServiceUtil.getTag(scopeGroupId, tagName);

						AssetTagProperty assetTagProperty = AssetTagPropertyLocalServiceUtil.getTagProperty(assetTag.getTagId(), "category");

						variablePropertyValue = assetTagProperty.getValue();

						if (category.equals(variablePropertyValue)) {
							if (pos == -1) {
								variablePropertyValue = assetTag.getName();
							}
							else {
								assetTagProperty = AssetTagPropertyLocalServiceUtil.getTagProperty(assetTag.getTagId(), propertyName);

								variablePropertyValue = assetTagProperty.getValue();
							}

							xmlUrl = StringUtil.replace(xmlUrl, "[" + variablePropertyKey + "]", StringUtil.toUpperCase(variablePropertyValue));

							break;
						}
					}
					catch (NoSuchTagException nste) {
						_log.warn(nste);
					}
					catch (NoSuchTagPropertyException nstpe) {
						_log.warn(nstpe);
					}
				}
			}
		}
	}

	String content = XSLContentUtil.transform(new URL(xmlUrl), new URL(xslUrl));
%>

	<%= content %>

<%
}
catch (Exception e) {
	_log.error(e.getMessage());
%>

	<div class="alert alert-error">
		<liferay-ui:message key="an-error-occurred-while-processing-your-xml-and-xsl" />
	</div>

<%
}
%>

<%!
private static Log _log = LogFactoryUtil.getLog("portal-web.docroot.html.portlet.xsl_content.view_jsp");
%>