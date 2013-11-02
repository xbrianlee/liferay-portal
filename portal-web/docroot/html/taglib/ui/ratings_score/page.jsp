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

<%@ include file="/html/taglib/init.jsp" %>

<%
String randomNamespace = PortalUtil.generateRandomKey(request, "taglib_ui_ratings_score_page") + StringPool.UNDERLINE;

double score = GetterUtil.getDouble((String)request.getAttribute("liferay-ui:ratings-score:score"));

NumberFormat numberFormat = NumberFormat.getInstance();

numberFormat.setMaximumFractionDigits(1);
numberFormat.setMinimumFractionDigits(0);

String scoreString = numberFormat.format(score);
%>

<c:choose>
	<c:when test="<%= themeDisplay.isFacebook() %>">
		<%= scoreString %> Stars
	</c:when>
	<c:otherwise>
		<div class="taglib-ratings score" id="<%= randomNamespace %>averageRating">
			<div class="helper-clearfix" id="<%= randomNamespace %>averageRatingContent">

				<%
				for (int i = 1; i <= 5; i++) {
				%>

					<a class="rating-element <%= (i <= score) ? "rating-element-on" : StringPool.BLANK %>" href="javascript:;"></a>

				<%
				}
				%>

			</div>
		</div>

		<aui:script use="aui-rating">
			var ratingScore = new A.Rating(
				{
					boundingBox: '#<%= randomNamespace %>averageRating',
					defaultSelected: <%= MathUtil.format(score, 1, 1) %>,
					disabled: true,
					srcNode: '#<%= randomNamespace %>averageRatingContent'
				}
			).render();

			ratingScore.get('boundingBox').on(
				'mouseenter',
				function(event) {
					var el = A.Node.getDOMNode(event.currentTarget);

					Liferay.Portal.ToolTip.show(el, '<%= scoreString %> Stars');
				}
			);
		</aui:script>
	</c:otherwise>
</c:choose>