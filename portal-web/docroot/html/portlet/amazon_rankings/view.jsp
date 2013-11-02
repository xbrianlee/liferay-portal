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

<%@ include file="/html/portlet/amazon_rankings/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNull(AmazonRankingsUtil.getAmazonAccessKeyId()) %>">
		<liferay-ui:message key="please-contact-the-administrator-to-configure-an-amazon-license" />
	</c:when>
	<c:otherwise>
		<table class="lfr-table">

		<%
		Set<AmazonRankings> amazonRankingsSet = new TreeSet<AmazonRankings>();

		for (int i = 0; i < isbns.length; i++) {
			AmazonRankings rankings = AmazonRankingsUtil.getAmazonRankings(isbns[i]);

			if (rankings != null) {
				amazonRankingsSet.add(rankings);
			}
		}

		int i = 0;

		for (AmazonRankings amazonRankings : amazonRankingsSet) {
		%>

			<tr>
				<td>
					<aui:a href='<%= "http://www.amazon.com/exec/obidos/ASIN/" + amazonRankings.getISBN() %>' target="_blank">
						<img border="0" src="<%= amazonRankings.getSmallImageURL() %>" />
					</aui:a>
				</td>
				<td>
					<span style="font-size: xx-small;">
					<liferay-ui:message key="title" />: <%= StringUtil.shorten(amazonRankings.getProductName(), _DESCRIPTION_LENGTH) %><br />
					<liferay-ui:message key="author" />: <%= StringUtil.shorten(StringUtil.merge(amazonRankings.getAuthors(), ", "), _DESCRIPTION_LENGTH) %><br />
					<liferay-ui:message key="publisher" />: <%= StringUtil.shorten(amazonRankings.getManufacturer() + "; (" + amazonRankings.getReleaseDateAsString() + ")", _DESCRIPTION_LENGTH) %><br />
					<liferay-ui:message key="isbn" />: <%= amazonRankings.getISBN() %><br />
					<liferay-ui:message key="rank" />: <%= numberFormat.format(amazonRankings.getSalesRank()) %>
					</span>
				</td>
			</tr>

			<c:if test="<%= i < amazonRankingsSet.size() - 1 %>">
				<tr>
					<td>
						<br />
					</td>
				</tr>
			</c:if>

		<%
			i++;
		}
		%>

		</table>
	</c:otherwise>
</c:choose>

<%!
private static final int _DESCRIPTION_LENGTH = 16;
%>