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

package com.liferay.portlet.journal.webdav;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portlet.journal.model.JournalStructure;

import java.io.InputStream;

/**
 * @author Alexander Chow
 */
public class JournalStructureResourceImpl extends BaseResourceImpl {

	public JournalStructureResourceImpl(
		JournalStructure structure, String parentPath, String name) {

		super(
			parentPath, name, structure.getStructureId(),
			structure.getCreateDate(), structure.getModifiedDate(),
			structure.getXsd().length());

		setModel(structure);
		setClassName(JournalStructure.class.getName());
		setPrimaryKey(structure.getPrimaryKey());

		_structure = structure;
	}

	@Override
	public InputStream getContentAsStream() throws WebDAVException {
		try {
			return new UnsyncByteArrayInputStream(
				_structure.getXsd().getBytes(StringPool.UTF8));
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	@Override
	public String getContentType() {
		return ContentTypes.TEXT_XML;
	}

	@Override
	public boolean isCollection() {
		return false;
	}

	private JournalStructure _structure;

}