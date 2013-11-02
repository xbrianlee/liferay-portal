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

package com.liferay.portlet.wiki.importers;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portlet.wiki.model.WikiNode;

import java.io.InputStream;

import java.util.Map;

/**
 * @author Jorge Ferrer
 */
public interface WikiImporter {

	public void importPages(
			long userId, WikiNode node, InputStream[] inputStream,
			Map<String, String[]> options)
		throws PortalException;

}