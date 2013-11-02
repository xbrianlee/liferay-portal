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

package com.liferay.portal.xsl;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Locale;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;

import org.apache.xml.utils.SAXSourceLocator;
import org.apache.xml.utils.WrappedRuntimeException;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Raymond Augé
 */
public class XSLErrorListener implements ErrorListener {

	public XSLErrorListener(Locale locale) {
		_locale = locale;
	}

	@Override
	public void error(TransformerException exception)
		throws TransformerException {

		setLocation(exception);

		throw exception;
	}

	@Override
	public void fatalError(TransformerException exception)
		throws TransformerException {

		setLocation(exception);

		throw exception;
	}

	public int getColumnNumber() {
		return _columnNumber;
	}

	public int getLineNumber() {
		return _lineNumber;
	}

	public String getLocation() {
		return _location;
	}

	public String getMessage() {
		return _message;
	}

	public String getMessageAndLocation() {
		return _message + " " + _location;
	}

	public void setLocation(Throwable exception) {
		SourceLocator locator = null;
		Throwable cause = exception;
		Throwable rootCause = null;

		while (cause != null) {
			if (cause instanceof SAXParseException) {
				locator = new SAXSourceLocator((SAXParseException)cause);
				rootCause = cause;
			}
			else if (cause instanceof TransformerException) {
				SourceLocator causeLocator =
					((TransformerException)cause).getLocator();

				if (causeLocator != null) {
					locator = causeLocator;
					rootCause = cause;
				}
			}

			if (cause instanceof TransformerException) {
				cause = ((TransformerException)cause).getCause();
			}
			else if (cause instanceof WrappedRuntimeException) {
				cause = ((WrappedRuntimeException)cause).getException();
			}
			else if (cause instanceof SAXException) {
				cause = ((SAXException)cause).getException();
			}
			else {
				cause = null;
			}
		}

		_message = rootCause.getMessage();

		if (locator != null) {
			_lineNumber = locator.getLineNumber();
			_columnNumber = locator.getColumnNumber();

			StringBundler sb = new StringBundler(8);

			sb.append(LanguageUtil.get(_locale, "line"));
			sb.append(" #");
			sb.append(locator.getLineNumber());
			sb.append("; ");
			sb.append(LanguageUtil.get(_locale, "column"));
			sb.append(" #");
			sb.append(locator.getColumnNumber());
			sb.append("; ");

			_location = sb.toString();
		}
		else {
			_location = StringPool.BLANK;
		}
	}

	@Override
	public void warning(TransformerException exception)
		throws TransformerException {

		setLocation(exception);

		throw exception;
	}

	private int _columnNumber;
	private int _lineNumber;
	private Locale _locale;
	private String _location;
	private String _message;

}