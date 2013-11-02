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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;

/**
 * @author Shuyang Zhou
 */
public class SynchronizedIndexAccessorImpl implements IndexAccessor {

	public SynchronizedIndexAccessorImpl(IndexAccessor indexAccessor) {
		_indexAccessor = indexAccessor;

		ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

		_readLock = readWriteLock.readLock();
		_writeLock = readWriteLock.writeLock();
	}

	@Override
	public void addDocument(Document document) throws IOException {
		_readLock.lock();

		try {
			_indexAccessor.addDocument(document);
		}
		finally {
			_readLock.unlock();
		}
	}

	@Override
	public void addDocuments(Collection<Document> documents)
		throws IOException {

		_readLock.lock();

		try {
			_indexAccessor.addDocuments(documents);
		}
		finally {
			_readLock.unlock();
		}
	}

	@Override
	public void close() {
		_readLock.lock();

		try {
			_indexAccessor.close();
		}
		finally {
			_readLock.unlock();
		}
	}

	@Override
	public void delete() {
		_writeLock.lock();

		try {
			_indexAccessor.delete();
		}
		finally {
			_writeLock.unlock();
		}
	}

	@Override
	public void deleteDocuments(Term term) throws IOException {
		_readLock.lock();

		try {
			_indexAccessor.deleteDocuments(term);
		}
		finally {
			_readLock.unlock();
		}
	}

	@Override
	public void dumpIndex(OutputStream outputStream) throws IOException {
		_readLock.lock();

		try {
			_indexAccessor.dumpIndex(outputStream);
		}
		finally {
			_readLock.unlock();
		}
	}

	@Override
	public long getCompanyId() {
		return _indexAccessor.getCompanyId();
	}

	@Override
	public long getLastGeneration() {
		return _indexAccessor.getLastGeneration();
	}

	@Override
	public Directory getLuceneDir() {
		return _indexAccessor.getLuceneDir();
	}

	@Override
	public void loadIndex(InputStream inputStream) throws IOException {
		_writeLock.lock();

		try {
			_indexAccessor.loadIndex(inputStream);
		}
		finally {
			_writeLock.unlock();
		}
	}

	@Override
	public void updateDocument(Term term, Document document)
		throws IOException {

		_readLock.lock();

		try {
			_indexAccessor.updateDocument(term, document);
		}
		finally {
			_readLock.unlock();
		}
	}

	private IndexAccessor _indexAccessor;
	private Lock _readLock;
	private Lock _writeLock;

}