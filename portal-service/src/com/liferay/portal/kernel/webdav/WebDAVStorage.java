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

package com.liferay.portal.kernel.webdav;

import com.liferay.portal.kernel.webdav.methods.MethodFactory;
import com.liferay.portal.model.Lock;

import java.util.List;

/**
 * @author Brian Wing Shun Chan
 * @author Alexander Chow
 */
public interface WebDAVStorage {

	public int copyCollectionResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite, long depth)
		throws WebDAVException;

	public int copySimpleResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException;

	public int deleteResource(WebDAVRequest webDAVRequest)
		throws WebDAVException;

	public MethodFactory getMethodFactory();

	public Resource getResource(WebDAVRequest webDAVRequest)
		throws WebDAVException;

	public List<Resource> getResources(WebDAVRequest webDAVRequest)
		throws WebDAVException;

	public String getRootPath();

	public String getToken();

	public boolean isAvailable(WebDAVRequest webDAVRequest)
		throws WebDAVException;

	public boolean isSupportsClassTwo();

	public Status lockResource(
			WebDAVRequest webDAVRequest, String owner, long timeout)
		throws WebDAVException;

	public Status makeCollection(WebDAVRequest webDAVRequest)
		throws WebDAVException;

	public int moveCollectionResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException;

	public int moveSimpleResource(
			WebDAVRequest webDAVRequest, Resource resource, String destination,
			boolean overwrite)
		throws WebDAVException;

	public int putResource(WebDAVRequest webDAVRequest) throws WebDAVException;

	public Lock refreshResourceLock(
			WebDAVRequest webDAVRequest, String uuid, long timeout)
		throws WebDAVException;

	public void setRootPath(String rootPath);

	public void setToken(String token);

	public boolean unlockResource(WebDAVRequest webDAVRequest, String token)
		throws WebDAVException;

}