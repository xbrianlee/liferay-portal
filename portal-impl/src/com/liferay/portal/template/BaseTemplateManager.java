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

import com.liferay.portal.kernel.security.pacl.NotPrivileged;
import com.liferay.portal.kernel.template.Template;
import com.liferay.portal.kernel.template.TemplateManager;
import com.liferay.portal.kernel.template.TemplateResource;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public abstract class BaseTemplateManager implements TemplateManager {

	@NotPrivileged
	@Override
	public Template getTemplate(
		TemplateResource templateResource, boolean restricted) {

		return getTemplate(templateResource, null, restricted);
	}

	@NotPrivileged
	@Override
	public Template getTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, boolean restricted) {

		TemplateControlContext templateControlContext =
			templateContextHelper.getTemplateControlContext();

		AccessControlContext accessControlContext =
			templateControlContext.getAccessControlContext();

		ClassLoader classLoader = templateControlContext.getClassLoader();

		if (accessControlContext == null) {
			Map<String, Object> helperUtilities =
				templateContextHelper.getHelperUtilities(
					classLoader, restricted);

			return doGetTemplate(
				templateResource, errorTemplateResource, restricted,
				helperUtilities, false);
		}

		Map<String, Object> helperUtilities = AccessController.doPrivileged(
			new DoGetHelperUtilitiesPrivilegedAction(
				templateContextHelper, classLoader, restricted),
			accessControlContext);

		Template template = AccessController.doPrivileged(
			new DoGetTemplatePrivilegedAction(
				templateResource, errorTemplateResource, restricted,
				helperUtilities));

		return new PrivilegedTemplateWrapper(accessControlContext, template);
	}

	public void setTemplateContextHelper(
		TemplateContextHelper templateContextHelper) {

		this.templateContextHelper = templateContextHelper;
	}

	protected abstract Template doGetTemplate(
		TemplateResource templateResource,
		TemplateResource errorTemplateResource, boolean restricted,
		Map<String, Object> helperUtilities, boolean privileged);

	protected TemplateContextHelper templateContextHelper;

	private class DoGetHelperUtilitiesPrivilegedAction
		implements PrivilegedAction<Map<String, Object>> {

		public DoGetHelperUtilitiesPrivilegedAction(
			TemplateContextHelper templateContextHelper,
			ClassLoader classLoader, boolean restricted) {

			_templateContextHelper = templateContextHelper;
			_classLoader = classLoader;
			_restricted = restricted;
		}

		@Override
		public Map<String, Object> run() {
			return _templateContextHelper.getHelperUtilities(
				_classLoader, _restricted);
		}

		private ClassLoader _classLoader;
		private boolean _restricted;
		private TemplateContextHelper _templateContextHelper;

	}

	private class DoGetTemplatePrivilegedAction
		implements PrivilegedAction<Template> {

		public DoGetTemplatePrivilegedAction(
			TemplateResource templateResource,
			TemplateResource errorTemplateResource, boolean restricted,
			Map<String, Object> helperUtilities) {

			_templateResource = templateResource;
			_errorTemplateResource = errorTemplateResource;
			_restricted = restricted;
			_helperUtilities = helperUtilities;
		}

		@Override
		public Template run() {
			return doGetTemplate(
				_templateResource, _errorTemplateResource, _restricted,
				_helperUtilities, true);
		}

		private TemplateResource _errorTemplateResource;
		private Map<String, Object> _helperUtilities;
		private boolean _restricted;
		private TemplateResource _templateResource;

	}

}