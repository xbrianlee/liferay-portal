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

package com.liferay.portlet.xslcontent.util;

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayOutputStream;
import com.liferay.portal.kernel.io.unsync.UnsyncStringReader;
import com.liferay.portal.kernel.util.HttpUtil;

import java.io.IOException;

import java.net.URL;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author Brian Wing Shun Chan
 */
public class XSLContentUtil {

	public static final String DEFAULT_XML_URL =
		"@portal_url@/html/portlet/xsl_content/example.xml";

	public static final String DEFAULT_XSL_URL =
		"@portal_url@/html/portlet/xsl_content/example.xsl";

	public static String transform(URL xmlUrl, URL xslUrl)
		throws IOException, TransformerException {

		String xml = HttpUtil.URLtoString(xmlUrl);
		String xsl = HttpUtil.URLtoString(xslUrl);

		StreamSource xmlSource = new StreamSource(new UnsyncStringReader(xml));
		StreamSource xslSource = new StreamSource(new UnsyncStringReader(xsl));

		TransformerFactory transformerFactory =
			TransformerFactory.newInstance();

		Transformer transformer = transformerFactory.newTransformer(xslSource);

		UnsyncByteArrayOutputStream unsyncByteArrayOutputStream =
			new UnsyncByteArrayOutputStream();

		transformer.transform(
			xmlSource, new StreamResult(unsyncByteArrayOutputStream));

		return unsyncByteArrayOutputStream.toString();
	}

}