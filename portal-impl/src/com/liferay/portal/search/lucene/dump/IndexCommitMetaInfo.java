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

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.IOException;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.IndexCommit;
import org.apache.lucene.store.Directory;

/**
 * @author Shuyang Zhou
 */
public class IndexCommitMetaInfo implements Serializable {

	public IndexCommitMetaInfo(IndexCommit indexCommit) throws IOException {
		if (indexCommit == null) {
			_empty = true;

			return;
		}

		List<String> fileNames = new ArrayList<String>(
			indexCommit.getFileNames());

		_segments = new ArrayList<Segment>(fileNames.size());

		Directory directory = indexCommit.getDirectory();

		for (String fileName : fileNames) {
			Segment segment = new Segment(
				fileName, directory.fileLength(fileName));

			_segments.add(segment);
		}

		_generation = indexCommit.getGeneration();
	}

	public long getGeneration() {
		return _generation;
	}

	public List<Segment> getSegments() {
		return _segments;
	}

	public boolean isEmpty() {
		return _empty;
	}

	@Override
	public String toString() {
		if (_empty) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(_segments.size() * 5 + 3);

		sb.append("{fileInfos[");

		for (int i = 0; i < _segments.size(); i++) {
			Segment fileInfo = _segments.get(i);

			sb.append("fileName=");
			sb.append(fileInfo._fileName);
			sb.append(", fileSize=");
			sb.append(fileInfo._fileSize);

			if ((i + 1) < _segments.size()) {
				sb.append(", ");
			}
		}

		sb.append("], generation=");
		sb.append(_generation);
		sb.append("}");

		return sb.toString();
	}

	public class Segment implements Serializable {

		public Segment(String fileName, long fileSize) {
			_fileName = fileName;
			_fileSize = fileSize;
		}

		public String getFileName() {
			return _fileName;
		}

		public long getFileSize() {
			return _fileSize;
		}

		@Override
		public String toString() {
			StringBundler sb = new StringBundler(5);

			sb.append("{_fileName=");
			sb.append(_fileName);
			sb.append(", _fileSize=");
			sb.append(_fileSize);
			sb.append("}");

			return sb.toString();
		}

		private String _fileName;
		private long _fileSize;

	}

	private boolean _empty;
	private long _generation;
	private List<Segment> _segments;

}