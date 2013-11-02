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

package com.liferay.portlet.journalcontentsearch.util;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portlet.journal.service.JournalContentSearchLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Chow
 * @author Raymond Augé
 */
public class ContentHits {

	public boolean isShowListed() {
		return _showListed;
	}

	public void recordHits(
			Hits hits, long groupId, boolean privateLayout, int start, int end)
		throws Exception {

		// This can later be optimized according to LEP-915.

		List<Document> docs = new ArrayList<Document>();
		List<Float> scores = new ArrayList<Float>();

		for (int i = 0; i < hits.getLength(); i++) {
			Document doc = hits.doc(i);

			long articleGroupId = GetterUtil.getLong(doc.get(Field.GROUP_ID));
			String articleId = doc.get("articleId");

			int layoutIdsCount =
				JournalContentSearchLocalServiceUtil.getLayoutIdsCount(
					groupId, privateLayout, articleId);

			if ((layoutIdsCount > 0) ||
				(!isShowListed() && (articleGroupId == groupId))) {

				docs.add(hits.doc(i));
				scores.add(hits.score(i));
			}
		}

		int length = docs.size();

		hits.setLength(length);

		if (end > length) {
			end = length;
		}

		docs = docs.subList(start, end);
		scores = scores.subList(start, end);

		hits.setDocs(docs.toArray(new Document[docs.size()]));
		hits.setScores(scores.toArray(new Float[docs.size()]));

		hits.setSearchTime(
			(float)(System.currentTimeMillis() - hits.getStart()) /
				Time.SECOND);
	}

	public void setShowListed(boolean showListed) {
		_showListed = showListed;
	}

	private boolean _showListed = true;

}