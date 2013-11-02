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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Collection;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;

/**
 * @author Bruno Farache
 * @author Shuyang Zhou
 */
public interface IndexAccessor {

	public static final long DEFAULT_LAST_GENERATION = -1;

	public void addDocument(Document document) throws IOException;

	public void addDocuments(Collection<Document> documents) throws IOException;

	public void close();

	public void delete();

	public void deleteDocuments(Term term) throws IOException;

	public void dumpIndex(OutputStream outputStream) throws IOException;

	public long getCompanyId();

	public long getLastGeneration();

	public Directory getLuceneDir();

	public void loadIndex(InputStream inputStream) throws IOException;

	public void updateDocument(Term term, Document document) throws IOException;

}