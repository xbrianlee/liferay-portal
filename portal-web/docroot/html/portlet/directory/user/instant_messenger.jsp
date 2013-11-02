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

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
Contact selContact = (Contact)request.getAttribute("user.selContact");

String aim = selContact.getAimSn();
String icq = selContact.getIcqSn();
String jabber = selContact.getJabberSn();
String msn = selContact.getMsnSn();
String skype = selContact.getSkypeSn();
String ym = selContact.getYmSn();
%>

<c:if test="<%= Validator.isNotNull(aim) || Validator.isNotNull(icq) || Validator.isNotNull(jabber) || Validator.isNotNull(msn) || Validator.isNotNull(skype) || Validator.isNotNull(ym) %>">
	<h3><liferay-ui:message key="instant-messenger" /></h3>

	<dl class="property-list">
		<c:if test="<%= Validator.isNotNull(aim) %>">
			<dt>
				<liferay-ui:message key="aim" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(aim) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(icq) %>">
			<dt>
				<liferay-ui:message key="icq" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(icq) %>

				<img alt="" class="instant-messenger-logo" src="http://web.icq.com/whitepages/online?icq=<%= HtmlUtil.escapeAttribute(icq) %>&img=5" />
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(jabber) %>">
			<dt>
				<liferay-ui:message key="jabber" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(jabber) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(skype) %>">
			<dt>
				<liferay-ui:message key="skype" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(skype) %>
				<a href="callto://<%= HtmlUtil.escapeAttribute(skype) %>"><img alt="<liferay-ui:message key="call-this-user" />" class="instant-messenger-logo" src="http://mystatus.skype.com/smallicon/<%= HtmlUtil.escapeAttribute(skype) %>" /></a>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(msn) %>">
			<dt>
				<liferay-ui:message key="windows-live-messenger" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(msn) %>
			</dd>
		</c:if>

		<c:if test="<%= Validator.isNotNull(ym) %>">
			<dt>
				<liferay-ui:message key="yim" />
			</dt>
			<dd>
				<%= HtmlUtil.escape(ym) %>
				<img alt="" class="instant-messenger-logo" src="http://opi.yahoo.com/online?u=<%= HtmlUtil.escapeAttribute(ym) %>&m=g&t=0" />
			</dd>
		</c:if>
	</dl>
</c:if>