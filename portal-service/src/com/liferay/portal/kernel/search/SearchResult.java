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

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.util.Tuple;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portlet.messageboards.model.MBMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eudaldo Alonso
 */
public class SearchResult {

	public SearchResult(String className, long classPK) {
		_className = className;
		_classPK = classPK;
	}

	public void addFileEntry(FileEntry fileEntry, Summary summary) {
		Tuple tuple = new Tuple(fileEntry, summary);

		_fileEntryTuples.add(tuple);
	}

	public void addMBMessage(MBMessage mbMessage) {
		_mbMessages.add(mbMessage);
	}

	public void addVersion(String version) {
		_versions.add(version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SearchResult)) {
			return false;
		}

		SearchResult searchResult = (SearchResult)obj;

		if (Validator.equals(_classPK, searchResult._classPK) &&
			Validator.equals(_className, searchResult._className)) {

			return true;
		}

		return false;
	}

	public String getClassName() {
		return _className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public List<Tuple> getFileEntryTuples() {
		return _fileEntryTuples;
	}

	public List<MBMessage> getMBMessages() {
		return _mbMessages;
	}

	public Summary getSummary() {
		return _summary;
	}

	public List<String> getVersions() {
		return _versions;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public void setMessages(List<MBMessage> mbMessages) {
		_mbMessages = mbMessages;
	}

	public void setSummary(Summary summary) {
		_summary = summary;
	}

	private String _className;
	private long _classPK;
	private List<Tuple> _fileEntryTuples = new ArrayList<Tuple>();
	private List<MBMessage> _mbMessages = new ArrayList<MBMessage>();
	private Summary _summary;
	private List<String> _versions = new ArrayList<String>();

}