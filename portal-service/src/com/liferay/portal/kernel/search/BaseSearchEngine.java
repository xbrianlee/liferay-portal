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

package com.liferay.portal.kernel.search;

import com.liferay.portal.kernel.cluster.Priority;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;

/**
 * @author Bruno Farache
 */
@DoPrivileged
public class BaseSearchEngine implements SearchEngine {

	@Override
	public BooleanClauseFactory getBooleanClauseFactory() {
		if (_booleanClauseFactory == null) {
			ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

			String className =
				"com.liferay.portal.search.generic.BooleanClauseFactoryImpl";

			try {
				_booleanClauseFactory =
					(BooleanClauseFactory)InstanceFactory.newInstance(
						classLoader, className);
			}
			catch (Exception e) {
				_log.fatal(
					"Unable to locate appropriate BooleanClauseFactory", e);
			}
		}

		return _booleanClauseFactory;
	}

	@Override
	public BooleanQueryFactory getBooleanQueryFactory() {
		if (_booleanQueryFactory != null) {
			return _booleanQueryFactory;
		}

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		String className =
			"com.liferay.portal.search.lucene.BooleanQueryFactoryImpl";

		if (!isLuceneBased()) {
			className =
				"com.liferay.portal.search.generic.BooleanQueryFactoryImpl";
		}

		try {
			_booleanQueryFactory =
				(BooleanQueryFactory)InstanceFactory.newInstance(
					classLoader, className);
		}
		catch (Exception e) {
			_log.fatal("Unable to locate appropriate BooleanQueryFactory", e);
		}

		return _booleanQueryFactory;
	}

	@Override
	public Priority getClusteredWritePriority() {
		return _clusteredWritePriority;
	}

	@Override
	public IndexSearcher getIndexSearcher() {
		return _indexSearcher;
	}

	@Override
	public IndexWriter getIndexWriter() {
		return _indexWriter;
	}

	@Override
	public TermQueryFactory getTermQueryFactory() {
		if (_termQueryFactory != null) {
			return _termQueryFactory;
		}

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		String className =
			"com.liferay.portal.search.lucene.TermQueryFactoryImpl";

		if (!isLuceneBased()) {
			className =
				"com.liferay.portal.search.generic.TermQueryFactoryImpl";
		}

		try {
			_termQueryFactory =
				(TermQueryFactory)InstanceFactory.newInstance(
					classLoader, className);
		}
		catch (Exception e) {
			_log.fatal("Unable to locate appropriate BooleanQueryFactory", e);
		}

		return _termQueryFactory;
	}

	@Override
	public TermRangeQueryFactory getTermRangeQueryFactory() {
		if (_termRangeQueryFactory != null) {
			return _termRangeQueryFactory;
		}

		ClassLoader classLoader = PortalClassLoaderUtil.getClassLoader();

		String className =
			"com.liferay.portal.search.lucene.TermRangeQueryFactoryImpl";

		if (!isLuceneBased()) {
			className =
				"com.liferay.portal.search.generic." +
					"TermRangeQueryFactoryImpl";
		}

		try {
			_termRangeQueryFactory =
				(TermRangeQueryFactory)InstanceFactory.newInstance(
					classLoader, className);
		}
		catch (Exception e) {
			_log.fatal("Unable to locate appropriate BooleanQueryFactory", e);
		}

		return _termRangeQueryFactory;
	}

	@Override
	public String getVendor() {
		return _vendor;
	}

	@Override
	public boolean isClusteredWrite() {
		return _clusteredWrite;
	}

	@Override
	public boolean isLuceneBased() {
		return _luceneBased;
	}

	public void setBooleanClauseFactory(
		BooleanClauseFactory booleanClauseFactory) {

		_booleanClauseFactory = booleanClauseFactory;
	}

	public void setBooleanQueryFactory(
		BooleanQueryFactory booleanQueryFactory) {

		_booleanQueryFactory = booleanQueryFactory;
	}

	public void setClusteredWrite(boolean clusteredWrite) {
		_clusteredWrite = clusteredWrite;
	}

	public void setClusteredWritePriority(Priority clusteredWritePriority) {
		_clusteredWritePriority = clusteredWritePriority;
	}

	public void setIndexSearcher(IndexSearcher indexSearcher) {
		_indexSearcher = indexSearcher;
	}

	public void setIndexWriter(IndexWriter indexWriter) {
		_indexWriter = indexWriter;
	}

	public void setLuceneBased(boolean luceneBased) {
		_luceneBased = luceneBased;
	}

	public void setTermQueryFactory(TermQueryFactory termQueryFactory) {
		_termQueryFactory = termQueryFactory;
	}

	public void setTermRangeQueryFactory(
		TermRangeQueryFactory termRangeQueryFactory) {

		_termRangeQueryFactory = termRangeQueryFactory;
	}

	public void setVendor(String vendor) {
		_vendor = vendor;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseSearchEngine.class);

	private BooleanClauseFactory _booleanClauseFactory;
	private BooleanQueryFactory _booleanQueryFactory;
	private boolean _clusteredWrite;
	private Priority _clusteredWritePriority;
	private IndexSearcher _indexSearcher;
	private IndexWriter _indexWriter;
	private boolean _luceneBased;
	private TermQueryFactory _termQueryFactory;
	private TermRangeQueryFactory _termRangeQueryFactory;
	private String _vendor;

}