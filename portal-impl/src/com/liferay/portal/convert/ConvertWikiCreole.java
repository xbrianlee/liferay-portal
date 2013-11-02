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

package com.liferay.portal.convert;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.MaintenanceUtil;
import com.liferay.portlet.wiki.model.WikiPage;
import com.liferay.portlet.wiki.service.WikiPageLocalServiceUtil;
import com.liferay.portlet.wiki.translators.ClassicToCreoleTranslator;

import java.util.List;

/**
 * @author Jorge Ferrer
 */
public class ConvertWikiCreole extends ConvertProcess {

	@Override
	public String getDescription() {
		return "convert-wiki-pages-from-classic-wiki-to-creole-format";
	}

	@Override
	public boolean isEnabled() {
		boolean enabled = false;

		try {
			int pagesCount = WikiPageLocalServiceUtil.getPagesCount(
				"classic_wiki");

			if (pagesCount > 0) {
				enabled = true;
			}
		}
		catch (Exception e) {
			_log.error(e, e);
		}

		return enabled;
	}

	@Override
	protected void doConvert() throws Exception {
		List<WikiPage> pages = WikiPageLocalServiceUtil.getPages(
			"classic_wiki");

		ClassicToCreoleTranslator translator = new ClassicToCreoleTranslator();

		MaintenanceUtil.appendStatus(
			"Converting " + pages.size() +
				" Wiki pages from Classic Wiki to Creole format");

		for (int i = 0; i < pages.size(); i++) {
			if ((i > 0) && (i % (pages.size() / 4) == 0)) {
				MaintenanceUtil.appendStatus((i * 100. / pages.size()) + "%");
			}

			WikiPage page = pages.get(i);

			page.setFormat("creole");

			page.setContent(translator.translate(page.getContent()));

			WikiPageLocalServiceUtil.updateWikiPage(page);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ConvertWikiCreole.class);

}