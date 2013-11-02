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

package com.liferay.portal.velocity;

import com.liferay.portal.kernel.io.ReaderInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.template.TemplateResource;
import com.liferay.portal.kernel.template.TemplateResourceLoaderUtil;
import com.liferay.portal.util.PropsValues;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.collections.ExtendedProperties;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.resource.Resource;
import org.apache.velocity.runtime.resource.loader.ResourceLoader;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public class LiferayResourceLoader extends ResourceLoader {

	@Override
	public long getLastModified(Resource resource) {
		if (_log.isDebugEnabled()) {
			_log.debug("Get last modified for " + resource.getName());
		}

		return 0;
	}

	@Override
	public InputStream getResourceStream(String source)
		throws ResourceNotFoundException {

		InputStream is = doGetResourceStream(source);

		if (is == null) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to find " + source);
			}

			throw new ResourceNotFoundException(source);
		}

		if (_log.isDebugEnabled()) {
			_log.debug("Successfully found " + source);
		}

		return is;
	}

	@Override
	public void init(ExtendedProperties props) {
		setModificationCheckInterval(
			PropsValues.VELOCITY_ENGINE_RESOURCE_MODIFICATION_CHECK_INTERVAL);
	}

	@Override
	public boolean isSourceModified(Resource resource) {
		if (_log.isDebugEnabled()) {
			_log.debug("Check modified status for " + resource.getName());
		}

		return false;
	}

	@Override
	public boolean resourceExists(String resourceName) {
		InputStream is = null;

		try {
			is = doGetResourceStream(resourceName);

			if (is != null) {
				is.close();
			}
		}
		catch (IOException ioe) {
		}
		catch (ResourceNotFoundException rnfe) {
		}

		if (is != null) {
			return true;
		}
		else {
			return false;
		}
	}

	protected InputStream doGetResourceStream(String source)
		throws ResourceNotFoundException {

		if (_log.isDebugEnabled()) {
			_log.debug("Get resource for " + source);
		}

		try {
			TemplateResource templateResource =
				TemplateResourceLoaderUtil.getTemplateResource(
					TemplateConstants.LANG_TYPE_VM, source);

			return new ReaderInputStream(templateResource.getReader());
		}
		catch (Exception e) {
			return null;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		LiferayResourceLoader.class);

}