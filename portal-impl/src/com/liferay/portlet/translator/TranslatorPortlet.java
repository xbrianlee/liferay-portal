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

package com.liferay.portlet.translator;

import com.liferay.portal.kernel.microsofttranslator.MicrosoftTranslatorException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.translator.model.Translation;
import com.liferay.portlet.translator.util.TranslatorUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

/**
 * @author Brian Wing Shun Chan
 */
public class TranslatorPortlet extends MVCPortlet {

	@Override
	public void processAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws PortletException {

		try {
			String fromLanguageId = ParamUtil.getString(
				actionRequest, "fromLanguageId");
			String toLanguageId = ParamUtil.getString(
				actionRequest, "toLanguageId");
			String fromText = ParamUtil.getString(actionRequest, "text");

			if (Validator.isNotNull(fromText)) {
				Translation translation = TranslatorUtil.getTranslation(
					fromLanguageId, toLanguageId, fromText);

				actionRequest.setAttribute(
					WebKeys.TRANSLATOR_TRANSLATION, translation);
			}
		}
		catch (WebCacheException wce) {
			Throwable cause = wce.getCause();

			if (cause instanceof MicrosoftTranslatorException) {
				SessionErrors.add(actionRequest, cause.getClass(), cause);
			}
			else {
				throw new PortletException(wce);
			}
		}
		catch (Exception e) {
			throw new PortletException(e);
		}
	}

}