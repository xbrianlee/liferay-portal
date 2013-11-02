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

package com.liferay.portal.servlet.filters.aggregate;

/**
 * @author Raymond Augé
 * @author Eduardo Lundgren
 */
public interface AggregateContext {

	public String getContent(String path);

	public String getFullPath(String path);

	public String getResourcePath(String path);

	public String popPath();

	public void pushPath(String path);

	public String shiftPath();

	public void unshiftPath(String path);

}