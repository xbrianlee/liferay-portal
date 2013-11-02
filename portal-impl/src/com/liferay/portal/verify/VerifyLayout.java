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

package com.liferay.portal.verify;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Layout;
import com.liferay.portal.model.LayoutFriendlyURL;
import com.liferay.portal.service.LayoutFriendlyURLLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Kenneth Chang
 */
public class VerifyLayout extends VerifyProcess {

	@Override
	protected void doVerify() throws Exception {
		verifyFriendlyURL();
		verifyUuid();
	}

	protected void verifyFriendlyURL() throws Exception {
		List<Layout> layouts =
			LayoutLocalServiceUtil.getNullFriendlyURLLayouts();

		for (Layout layout : layouts) {
			List<LayoutFriendlyURL> layoutFriendlyURLs =
				LayoutFriendlyURLLocalServiceUtil.getLayoutFriendlyURLs(
					layout.getPlid());

			for (LayoutFriendlyURL layoutFriendlyURL : layoutFriendlyURLs) {
				String friendlyURL = StringPool.SLASH + layout.getLayoutId();

				LayoutLocalServiceUtil.updateFriendlyURL(
					layout.getPlid(), friendlyURL,
					layoutFriendlyURL.getLanguageId());
			}
		}
	}

	protected void verifyUuid() throws Exception {
		verifyUuid("AssetEntry");
		verifyUuid("JournalArticle");

		StringBundler sb = new StringBundler(3);

		sb.append("update Layout set uuid_ = sourcePrototypeLayoutUuid where ");
		sb.append("sourcePrototypeLayoutUuid != '' and ");
		sb.append("uuid_ != sourcePrototypeLayoutUuid");

		runSQL(sb.toString());
	}

	protected void verifyUuid(String tableName) throws Exception {
		StringBundler sb = new StringBundler(11);

		sb.append("update ");
		sb.append(tableName);
		sb.append(" set layoutUuid = (select sourcePrototypeLayoutUuid from ");
		sb.append("Layout where Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid) where exists (select 1 from Layout where ");
		sb.append("Layout.uuid_ = ");
		sb.append(tableName);
		sb.append(".layoutUuid and Layout.uuid_ != ");
		sb.append("Layout.sourcePrototypeLayoutUuid and ");
		sb.append("Layout.sourcePrototypeLayoutUuid != '')");

		runSQL(sb.toString());
	}

}