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

package com.liferay.portal.kernel.atom;

/**
 * @author Igor Spasic
 */
public interface AtomRequestContext {

	public Object getContainerAttribute(String name);

	public String getHeader(String name);

	public int getIntParameter(String name);

	public int getIntParameter(String name, int defaultValue);

	public long getLongParameter(String name);

	public long getLongParameter(String name, long defaultValue);

	public String getParameter(String name);

	public String getParameter(String name, String defaultValue);

	public Object getRequestAttribute(String name);

	public String getResolvedUri();

	public Object getSessionAttribute(String name);

	public String getTargetBasePath();

	public void setContainerAttribute(String name, Object value);

	public void setRequestAttribute(String name, Object value);

	public void setSessionAttribute(String name, Object value);

}