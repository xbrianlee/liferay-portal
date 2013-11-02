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

package com.liferay.portal.template;

import com.liferay.portal.kernel.io.unsync.UnsyncCharArrayWriter;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.template.TemplateResource;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Reader;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Tina Tian
 */
public class CacheTemplateResource implements TemplateResource {

	/**
	 * The empty constructor is required by {@link java.io.Externalizable}. Do
	 * not use this for any other purpose.
	 */
	public CacheTemplateResource() {
	}

	public CacheTemplateResource(TemplateResource templateResource) {
		if (templateResource == null) {
			throw new IllegalArgumentException("Template resource is null");
		}

		_templateResource = templateResource;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CacheTemplateResource)) {
			return false;
		}

		CacheTemplateResource cacheTemplateResource =
			(CacheTemplateResource)obj;

		if (_templateResource.equals(cacheTemplateResource._templateResource)) {
			return true;
		}

		return false;
	}

	public TemplateResource getInnerTemplateResource() {
		return _templateResource;
	}

	@Override
	public long getLastModified() {
		return _lastModified;
	}

	@Override
	public Reader getReader() throws IOException {
		String templateContent = _templateContent.get();

		if (templateContent != null) {
			return new UnsyncStringReader(templateContent);
		}

		Reader reader = null;

		try {
			reader = _templateResource.getReader();

			char[] buffer = new char[1024];

			int result = -1;

			UnsyncCharArrayWriter unsyncCharArrayWriter =
				new UnsyncCharArrayWriter();

			while ((result = reader.read(buffer)) != -1) {
				unsyncCharArrayWriter.write(buffer, 0, result);
			}

			templateContent = unsyncCharArrayWriter.toString();

			_templateContent.set(templateContent);
		}
		finally {
			if (reader != null) {
				reader.close();
			}
		}

		return new UnsyncStringReader(templateContent);
	}

	@Override
	public String getTemplateId() {
		return _templateResource.getTemplateId();
	}

	@Override
	public int hashCode() {
		return _templateResource.hashCode();
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		_lastModified = objectInput.readLong();
		_templateResource = (TemplateResource)objectInput.readObject();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(_lastModified);
		objectOutput.writeObject(_templateResource);
	}

	private long _lastModified = System.currentTimeMillis();
	private AtomicReference<String> _templateContent =
		new AtomicReference<String>();
	private TemplateResource _templateResource;

}