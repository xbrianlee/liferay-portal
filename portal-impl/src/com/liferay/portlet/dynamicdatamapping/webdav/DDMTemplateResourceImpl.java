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

package com.liferay.portlet.dynamicdatamapping.webdav;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.webdav.BaseResourceImpl;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;

import java.io.InputStream;

/**
 * @author Juan Fernández
 */
public class DDMTemplateResourceImpl extends BaseResourceImpl {

	public DDMTemplateResourceImpl(
		DDMTemplate template, String parentPath, String name) {

		super(
			parentPath, name, template.getName(template.getDefaultLanguageId()),
			template.getCreateDate(), template.getModifiedDate(),
			template.getScript().length());

		setModel(template);
		setClassName(DDMTemplate.class.getName());
		setPrimaryKey(template.getPrimaryKey());

		_template = template;
	}

	@Override
	public InputStream getContentAsStream() throws WebDAVException {
		try {
			return new UnsyncByteArrayInputStream(
				_template.getScript().getBytes(StringPool.UTF8));
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

	private DDMTemplate _template;

}