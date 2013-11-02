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

package com.liferay.taglib.ui;

import com.liferay.portal.kernel.language.UnicodeLanguageUtil;
import com.liferay.portal.kernel.servlet.taglib.FileAvailabilityUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class IconDeleteTag extends IconTag {

	public void setConfirmation(String confirmation) {
		_confirmation = confirmation;
	}

	public void setTrash(boolean trash) {
		_trash = trash;
	}

	@Override
	protected String getPage() {
		if (FileAvailabilityUtil.isAvailable(servletContext, _PAGE)) {
			return _PAGE;
		}

		if (Validator.isNull(getImage())) {
			if (_trash) {
				setImage("trash");
			}
			else {
				setImage("delete");
			}
		}

		if (_trash && Validator.isNull(getMessage())) {
			setMessage("move-to-the-recycle-bin");
		}

		String url = getUrl();

		if (url.startsWith("javascript:if (confirm('")) {
			return super.getPage();
		}

		if (url.startsWith("javascript:")) {
			url = url.substring(11);
		}

		if (url.startsWith(Http.HTTP_WITH_SLASH) ||
			url.startsWith(Http.HTTPS_WITH_SLASH)) {

			url =
				"submitForm(document.hrefFm, '".concat(
					HttpUtil.encodeURL(url)).concat("');");
		}

		if (url.startsWith("wsrp_rewrite?")) {
			url = StringUtil.replace(
				url, "/wsrp_rewrite",
				"&wsrp-extensions=encodeURL/wsrp_rewrite");
			url = "submitForm(document.hrefFm, '".concat(url).concat("');");
		}

		if (!_trash) {
			StringBundler sb = new StringBundler(5);

			sb.append("javascript:if (confirm('");

			if (Validator.isNotNull(_confirmation)) {
				sb.append(UnicodeLanguageUtil.get(pageContext, _confirmation));
			}
			else {
				String confirmation = "are-you-sure-you-want-to-delete-this";

				sb.append(UnicodeLanguageUtil.get(pageContext, confirmation));
			}

			sb.append("')) { ");
			sb.append(url);
			sb.append(" } else { self.focus(); }");

			url = sb.toString();
		}
		else {
			url = "javascript:".concat(url);
		}

		setUrl(url);

		return super.getPage();
	}

	private static final String _PAGE = "/html/taglib/ui/icon_delete/page.jsp";

	private String _confirmation;
	private boolean _trash;

}