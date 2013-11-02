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

package com.liferay.portlet.journalcontent;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletLayoutListener;
import com.liferay.portal.kernel.portlet.PortletLayoutListenerException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.UniqueList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.layoutconfiguration.util.xml.PortletLogic;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.PortletConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;
import com.liferay.portlet.journal.NoSuchArticleException;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portlet.journal.service.JournalContentSearchLocalServiceUtil;

import java.util.List;

import javax.portlet.PortletPreferences;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public class JournalContentPortletLayoutListener
	implements PortletLayoutListener {

	@Override
	public void onAddToLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Add " + portletId + " to layout " + plid);
		}

		try {
			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			PortletPreferences preferences =
				PortletPreferencesFactoryUtil.getPortletSetup(
					layout, portletId, StringPool.BLANK);

			String articleId = preferences.getValue("articleId", null);

			if (Validator.isNull(articleId)) {
				return;
			}

			JournalContentSearchLocalServiceUtil.updateContentSearch(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), portletId, articleId, true);
		}
		catch (Exception e) {
			throw new PortletLayoutListenerException(e);
		}
	}

	@Override
	public void onMoveInLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Move " + portletId + " from in " + plid);
		}
	}

	@Override
	public void onRemoveFromLayout(String portletId, long plid)
		throws PortletLayoutListenerException {

		if (_log.isDebugEnabled()) {
			_log.debug("Remove " + portletId + " from layout " + plid);
		}

		try {
			Layout layout = LayoutLocalServiceUtil.getLayout(plid);

			PortletPreferences preferences =
				PortletPreferencesFactoryUtil.getPortletSetup(
					layout, portletId, StringPool.BLANK);

			String articleId = preferences.getValue("articleId", null);

			if (Validator.isNull(articleId)) {
				return;
			}

			JournalContentSearchLocalServiceUtil.deleteArticleContentSearch(
				layout.getGroupId(), layout.isPrivateLayout(),
				layout.getLayoutId(), portletId, articleId);

			String[] runtimePortletIds = getRuntimePortletIds(
				layout.getCompanyId(), layout.getGroupId(), articleId);

			if (runtimePortletIds.length > 0) {
				PortletLocalServiceUtil.deletePortlets(
					layout.getCompanyId(), runtimePortletIds, layout.getPlid());
			}
		}
		catch (Exception e) {
			throw new PortletLayoutListenerException(e);
		}
	}

	protected String getRuntimePortletId(String xml) throws Exception {
		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		String instanceId = rootElement.attributeValue("instance");
		String portletId = rootElement.attributeValue("name");

		if (Validator.isNotNull(instanceId)) {
			portletId += PortletConstants.INSTANCE_SEPARATOR + instanceId;
		}

		return portletId;
	}

	protected String[] getRuntimePortletIds(
			long companyId, long scopeGroupId, String articleId)
		throws Exception {

		Group group = GroupLocalServiceUtil.getCompanyGroup(companyId);

		JournalArticle article = null;

		try {
			article = JournalArticleLocalServiceUtil.getDisplayArticle(
				scopeGroupId, articleId);
		}
		catch (NoSuchArticleException nsae) {
		}

		if (article == null) {
			try {
				article = JournalArticleLocalServiceUtil.getDisplayArticle(
					group.getGroupId(), articleId);
			}
			catch (NoSuchArticleException nsae) {
				return new String[0];
			}
		}

		List<String> portletIds = getRuntimePortletIds(article.getContent());

		if (Validator.isNotNull(article.getTemplateId())) {
			DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.getTemplate(
				scopeGroupId, PortalUtil.getClassNameId(DDMStructure.class),
				article.getTemplateId(), true);

			portletIds.addAll(getRuntimePortletIds(ddmTemplate.getScript()));
		}

		return portletIds.toArray(new String[portletIds.size()]);
	}

	protected List<String> getRuntimePortletIds(String content)
		throws Exception {

		List<String> portletIds = new UniqueList<String>();

		for (int index = 0;;) {
			index = content.indexOf(PortletLogic.OPEN_TAG, index);

			if (index == -1) {
				break;
			}

			int close1 = content.indexOf(PortletLogic.CLOSE_1_TAG, index);
			int close2 = content.indexOf(PortletLogic.CLOSE_2_TAG, index);

			int closeIndex = -1;

			if ((close2 == -1) || ((close1 != -1) && (close1 < close2))) {
				closeIndex = close1 + PortletLogic.CLOSE_1_TAG.length();
			}
			else {
				closeIndex = close2 + PortletLogic.CLOSE_2_TAG.length();
			}

			if (closeIndex == -1) {
				break;
			}

			portletIds.add(
				getRuntimePortletId(content.substring(index, closeIndex)));

			index = closeIndex;
		}

		return portletIds;
	}

	private static Log _log = LogFactoryUtil.getLog(
		JournalContentPortletLayoutListener.class);

}