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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.DDMTemplateResource;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateException;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.service.DDMTemplateLocalServiceUtil;

/**
 * @author Tina Tian
 * @author Juan Fernández
 */
public class DDMTemplateResourceParser implements TemplateResourceParser {

	@Override
	@SuppressWarnings("deprecation")
	public TemplateResource getTemplateResource(String templateId)
		throws TemplateException {

		int pos = templateId.indexOf(
			TemplateConstants.TEMPLATE_SEPARATOR + StringPool.SLASH);

		if (pos == -1) {

			// Backwards compatibility

			pos = templateId.indexOf(
				TemplateConstants.JOURNAL_SEPARATOR + StringPool.SLASH);

			if (pos == -1) {
				return null;
			}
		}

		try {
			int x = templateId.indexOf(CharPool.SLASH, pos);
			int y = templateId.indexOf(CharPool.SLASH, x + 1);
			int z = templateId.indexOf(CharPool.SLASH, y + 1);

			long companyId = GetterUtil.getLong(templateId.substring(x + 1, y));
			long groupId = GetterUtil.getLong(templateId.substring(y + 1, z));
			String ddmTemplateKey = templateId.substring(z + 1);

			if (_log.isDebugEnabled()) {
				_log.debug(
					"Loading {companyId=" + companyId + ", groupId=" +
						groupId + ", ddmTemplateKey=" + ddmTemplateKey + "}");
			}

			long classNameId = 0;

			DDMTemplate ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
				groupId, classNameId, ddmTemplateKey);

			if (ddmTemplate == null) {
				Group companyGroup = GroupLocalServiceUtil.getCompanyGroup(
					companyId);

				ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
					companyGroup.getGroupId(), classNameId, ddmTemplateKey);

				if (ddmTemplate == null) {
					classNameId = PortalUtil.getClassNameId(DDMStructure.class);

					ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
						groupId, classNameId, ddmTemplateKey);
				}

				if (ddmTemplate == null) {
					ddmTemplate = DDMTemplateLocalServiceUtil.fetchTemplate(
						companyGroup.getGroupId(), classNameId, ddmTemplateKey);
				}
			}

			if (ddmTemplate == null) {
				return null;
			}
			else {
				return new DDMTemplateResource(
					ddmTemplate.getTemplateKey(), ddmTemplate);
			}
		}
		catch (Exception e) {
			throw new TemplateException(
				"Unable to find template " + templateId, e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		DDMTemplateResourceParser.class);

}