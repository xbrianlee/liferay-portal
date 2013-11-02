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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexWriter;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import org.apache.lucene.index.Term;

/**
 * @author Bruno Farache
 * @author Brian Wing Shun Chan
 * @author Allen Chiang
 * @author Alex Wallace
 */
public class LuceneIndexWriter extends BaseIndexWriter {

	@Override
	public void addDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			LuceneHelperUtil.addDocument(
				searchContext.getCompanyId(), _getLuceneDocument(document));

			if (_log.isDebugEnabled()) {
				_log.debug("Added document " + document.get(Field.UID));
			}
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	@Override
	public void addDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		for (Document document : documents) {
			addDocument(searchContext, document);
		}
	}

	@Override
	public void deleteDocument(SearchContext searchContext, String uid)
		throws SearchException {

		try {
			LuceneHelperUtil.deleteDocuments(
				searchContext.getCompanyId(), new Term(Field.UID, uid));

			if (_log.isDebugEnabled()) {
				_log.debug("Deleted document " + uid);
			}
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	@Override
	public void deleteDocuments(
			SearchContext searchContext, Collection<String> uids)
		throws SearchException {

		for (String uid : uids) {
			deleteDocument(searchContext, uid);
		}
	}

	@Override
	public void deletePortletDocuments(
			SearchContext searchContext, String portletId)
		throws SearchException {

		try {
			LuceneHelperUtil.deleteDocuments(
				searchContext.getCompanyId(), new Term(Field.PORTLET_ID,
				portletId));
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	@Override
	public void updateDocument(SearchContext searchContext, Document document)
		throws SearchException {

		try {
			LuceneHelperUtil.updateDocument(
				searchContext.getCompanyId(),
				new Term(Field.UID, document.getUID()),
				_getLuceneDocument(document));

			if (_log.isDebugEnabled()) {
				_log.debug("Updated document " + document.get(Field.UID));
			}
		}
		catch (IOException ioe) {
			throw new SearchException(ioe);
		}
	}

	@Override
	public void updateDocuments(
			SearchContext searchContext, Collection<Document> documents)
		throws SearchException {

		for (Document document : documents) {
			updateDocument(searchContext, document);
		}
	}

	private void _addLuceneFieldable(
		org.apache.lucene.document.Document luceneDocument, String name,
		boolean numeric, Class<? extends Number> numericClass,
		boolean tokenized, float boost, String value) {

		org.apache.lucene.document.Fieldable luceneFieldable = null;

		if (numeric) {
			luceneFieldable = LuceneFields.getNumber(name, value, numericClass);
		}
		else {
			if (tokenized) {
				luceneFieldable = LuceneFields.getText(name, value);
			}
			else {
				luceneFieldable = LuceneFields.getKeyword(name, value);
			}
		}

		luceneFieldable.setBoost(boost);

		luceneDocument.add(luceneFieldable);
	}

	private org.apache.lucene.document.Document _getLuceneDocument(
		Document document) {

		org.apache.lucene.document.Document luceneDocument =
			new org.apache.lucene.document.Document();

		Collection<Field> fields = document.getFields().values();

		for (Field field : fields) {
			String name = field.getName();
			boolean numeric = field.isNumeric();
			Class<? extends Number> numericClass = field.getNumericClass();
			boolean tokenized = field.isTokenized();
			float boost = field.getBoost();

			if (!field.isLocalized()) {
				for (String value : field.getValues()) {
					if (Validator.isNull(value)) {
						continue;
					}

					_addLuceneFieldable(
						luceneDocument, name, numeric, numericClass, tokenized,
						boost, value);
				}
			}
			else {
				Map<Locale, String> localizedValues =
					field.getLocalizedValues();

				for (Map.Entry<Locale, String> entry :
						localizedValues.entrySet()) {

					String value = entry.getValue();

					if (Validator.isNull(value)) {
						continue;
					}

					Locale locale = entry.getKey();

					String languageId = LocaleUtil.toLanguageId(locale);

					String defaultLanguageId = LocaleUtil.toLanguageId(
						LocaleUtil.getDefault());

					if (languageId.equals(defaultLanguageId)) {
						_addLuceneFieldable(
							luceneDocument, name, numeric, numericClass,
							tokenized, boost, value);
					}

					String localizedName = DocumentImpl.getLocalizedName(
						locale, name);

					_addLuceneFieldable(
						luceneDocument, localizedName, numeric, numericClass,
						tokenized, boost, value);
				}
			}
		}

		return luceneDocument;
	}

	private static Log _log = LogFactoryUtil.getLog(LuceneIndexWriter.class);

}