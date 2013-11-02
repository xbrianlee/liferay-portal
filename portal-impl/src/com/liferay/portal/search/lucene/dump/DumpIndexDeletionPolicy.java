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

package com.liferay.portal.search.lucene.dump;

import java.io.IOException;
import java.io.OutputStream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;

import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.index.IndexDeletionPolicy;
import org.apache.lucene.index.IndexWriter;

/**
 * @author Shuyang Zhou
 */
public class DumpIndexDeletionPolicy implements IndexDeletionPolicy {

	public void dump(
			OutputStream outputStream, IndexWriter indexWriter, Lock commitLock)
		throws IOException {

		IndexCommit indexCommit = null;

		String segmentsFileName = null;

		commitLock.lock();

		try {
			indexWriter.commit();

			indexCommit = _lastIndexCommit;

			segmentsFileName = indexCommit.getSegmentsFileName();

			_segmentsFileNames.add(segmentsFileName);
		}
		finally {
			commitLock.unlock();
		}

		try {
			IndexCommitSerializationUtil.serializeIndex(
				indexCommit, outputStream);
		}
		finally {
			_segmentsFileNames.remove(segmentsFileName);
		}
	}

	public long getLastGeneration() {
		return _lastIndexCommit.getGeneration();
	}

	@Override
	public void onCommit(List<? extends IndexCommit> indexCommits) {
		_lastIndexCommit = indexCommits.get(indexCommits.size() - 1);

		for (int i = 0; i < indexCommits.size() - 1; i++) {
			IndexCommit indexCommit = indexCommits.get(i);

			if (!_segmentsFileNames.contains(
					indexCommit.getSegmentsFileName())) {

				indexCommit.delete();
			}
		}
	}

	@Override
	public void onInit(List<? extends IndexCommit> indexCommits) {
		onCommit(indexCommits);
	}

	private volatile IndexCommit _lastIndexCommit;
	private List<String> _segmentsFileNames =
		new CopyOnWriteArrayList<String>();

}