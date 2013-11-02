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

package com.liferay.portal.sharepoint.methods;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.sharepoint.Property;
import com.liferay.portal.sharepoint.ResponseElement;
import com.liferay.portal.sharepoint.SharepointException;
import com.liferay.portal.sharepoint.SharepointRequest;
import com.liferay.portal.sharepoint.SharepointUtil;

import java.util.List;

/**
 * @author Bruno Farache
 */
public abstract class BaseMethodImpl implements Method {

	@Override
	public String getRootPath(SharepointRequest sharepointRequest) {
		return StringPool.BLANK;
	}

	@Override
	public void process(SharepointRequest sharepointRequest)
		throws SharepointException {

		try {
			doProcess(sharepointRequest);
		}
		catch (Exception e) {
			throw new SharepointException(e);
		}
	}

	protected void doProcess(SharepointRequest sharepointRequest)
		throws Exception {

		ServletResponseUtil.write(
			sharepointRequest.getHttpServletResponse(),
			getResponse(sharepointRequest, false));
	}

	protected abstract List<ResponseElement> getElements(
			SharepointRequest sharepointRequest)
		throws Exception;

	protected String getResponse(
			SharepointRequest sharepointRequest, boolean appendNewline)
		throws Exception {

		StringBundler sb = new StringBundler();

		sb.append("<html><head><title>vermeer RPC packet</title></head>");
		sb.append(StringPool.NEW_LINE);
		sb.append("<body>");
		sb.append(StringPool.NEW_LINE);

		Property property = new Property(
			"method", getMethodName() + ":" + SharepointUtil.VERSION);

		sb.append(property.parse());

		List<ResponseElement> elements = getElements(sharepointRequest);

		for (ResponseElement element : elements) {
			sb.append(element.parse());
		}

		sb.append("</body>");
		sb.append(StringPool.NEW_LINE);
		sb.append("</html>");

		if (appendNewline) {
			sb.append(StringPool.NEW_LINE);
		}

		String html = sb.toString();

		if (_log.isDebugEnabled()) {
			_log.debug("Response HTML\n" + html);
		}

		return html;
	}

	private static Log _log = LogFactoryUtil.getLog(BaseMethodImpl.class);

}