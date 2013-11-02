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

package com.liferay.portal.webdav.methods;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webdav.WebDAVException;
import com.liferay.portal.kernel.webdav.WebDAVRequest;
import com.liferay.portal.kernel.webdav.WebDAVUtil;
import com.liferay.portal.kernel.webdav.methods.Method;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.Namespace;
import com.liferay.portal.kernel.xml.QName;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.webdav.InvalidRequestException;
import com.liferay.util.xml.XMLFormatter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public class PropfindMethodImpl extends BasePropMethodImpl implements Method {

	@Override
	public int process(WebDAVRequest webDAVRequest) throws WebDAVException {
		try {
			Set<QName> props = getProps(webDAVRequest);

			return writeResponseXML(webDAVRequest, props);
		}
		catch (InvalidRequestException ire) {
			return HttpServletResponse.SC_BAD_REQUEST;
		}
		catch (Exception e) {
			throw new WebDAVException(e);
		}
	}

	protected Set<QName> generateProps(Set<QName> props) {
		props.add(DISPLAYNAME);
		props.add(RESOURCETYPE);
		props.add(GETCONTENTTYPE);
		props.add(GETCONTENTLENGTH);
		props.add(GETLASTMODIFIED);
		props.add(LOCKDISCOVERY);

		// RFC 3253 Currently Unsupported

		//props.add(new Tuple("checked-in", WebDAVUtil.DAV_URI));
		//props.add(new Tuple("checked-out", WebDAVUtil.DAV_URI));
		//props.add(new Tuple("version-name", WebDAVUtil.DAV_URI));

		return props;
	}

	protected Set<QName> getProps(WebDAVRequest webDAVRequest)
		throws InvalidRequestException {

		try {
			Set<QName> props = new HashSet<QName>();

			HttpServletRequest request = webDAVRequest.getHttpServletRequest();

			String xml = new String(
				FileUtil.getBytes(request.getInputStream()));

			if (Validator.isNull(xml)) {

				// Windows XP does not generate an xml request so the PROPFIND
				// must be generated manually. See LEP-4920.

				return generateProps(props);
			}

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Request XML: \n" +
						XMLFormatter.toString(xml, StringPool.FOUR_SPACES));
			}

			Document document = SAXReaderUtil.read(xml);

			Element rootElement = document.getRootElement();

			if (rootElement.element(ALLPROP.getName()) != null) {

				// Generate props if <allprop> tag is used. See LEP-6162.

				return generateProps(props);
			}

			Element propElement = rootElement.element("prop");

			List<Element> elements = propElement.elements();

			for (Element element : elements) {
				String prefix = element.getNamespacePrefix();
				String uri = element.getNamespaceURI();

				Namespace namespace = WebDAVUtil.createNamespace(prefix, uri);

				props.add(
					SAXReaderUtil.createQName(element.getName(), namespace));
			}

			return props;
		}
		catch (Exception e) {
			throw new InvalidRequestException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(PropfindMethodImpl.class);

}