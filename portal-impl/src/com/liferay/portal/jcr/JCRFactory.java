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

package com.liferay.portal.jcr;

import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.util.PropsUtil;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

/**
 * @author Michael Young
 */
public interface JCRFactory {

	public static final String NODE_DOCUMENTLIBRARY = PropsUtil.get(
		PropsKeys.JCR_NODE_DOCUMENTLIBRARY);

	public static final String WORKSPACE_NAME = PropsUtil.get(
		PropsKeys.JCR_WORKSPACE_NAME);

	public Session createSession(String workspaceName)
		throws RepositoryException;

	public void initialize() throws RepositoryException;

	public void prepare() throws RepositoryException;

	public void shutdown();

}