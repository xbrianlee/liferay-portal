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

package com.liferay.portal.kernel.template;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Reader;

import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Tina Tian
 */
public class URLTemplateResource implements TemplateResource {

	/**
	 * The empty constructor is required by {@link java.io.Externalizable}. Do
	 * not use this for any other purpose.
	 */
	public URLTemplateResource() {
	}

	public URLTemplateResource(String templateId, URL templateURL) {
		if (Validator.isNull(templateId)) {
			throw new IllegalArgumentException("Template ID is null");
		}

		if (templateURL == null) {
			throw new IllegalArgumentException("Template URL is null");
		}

		_templateId = templateId;
		_templateURL = templateURL;
		_templateURLExternalForm = templateURL.toExternalForm();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof URLTemplateResource)) {
			return false;
		}

		URLTemplateResource urlTemplateResource = (URLTemplateResource)obj;

		if (_templateId.equals(urlTemplateResource._templateId) &&
			_templateURLExternalForm.equals(
				urlTemplateResource._templateURLExternalForm)) {

			return true;
		}

		return false;
	}

	@Override
	public long getLastModified() {
		URLConnection urlConnection = null;

		try {
			urlConnection = _templateURL.openConnection();

			if (urlConnection instanceof JarURLConnection) {
				JarURLConnection jarURLConnection =
					(JarURLConnection)urlConnection;

				URL url = jarURLConnection.getJarFileURL();

				String protocol = url.getProtocol();

				if (protocol.equals("file")) {
					return new File(url.getFile()).lastModified();
				}

				urlConnection = url.openConnection();
			}

			return urlConnection.getLastModified();
		}
		catch (IOException ioe) {
			_log.error(
				"Unable to get last modified time for template " + _templateId,
				ioe);

			return 0;
		}
		finally {
			if (urlConnection != null) {
				try {
					urlConnection.getInputStream().close();
				}
				catch (IOException ioe) {
				}
			}
		}
	}

	@Override
	public Reader getReader() throws IOException {
		URLConnection urlConnection = _templateURL.openConnection();

		return new InputStreamReader(
			urlConnection.getInputStream(), TemplateConstants.DEFAUT_ENCODING);
	}

	@Override
	public String getTemplateId() {
		return _templateId;
	}

	@Override
	public int hashCode() {
		return _templateId.hashCode() * 11 +
			_templateURLExternalForm.hashCode();
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		_templateId = objectInput.readUTF();
		_templateURLExternalForm = objectInput.readUTF();

		_templateURL = new URL(_templateURLExternalForm);
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeUTF(_templateId);
		objectOutput.writeUTF(_templateURLExternalForm);
	}

	private static Log _log = LogFactoryUtil.getLog(URLTemplateResource.class);

	private String _templateId;
	private URL _templateURL;
	private String _templateURLExternalForm;

}