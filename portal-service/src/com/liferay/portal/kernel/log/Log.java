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

package com.liferay.portal.kernel.log;

/**
 * @author Brian Wing Shun Chan
 */
public interface Log {

	public void debug(Object msg);

	public void debug(Object msg, Throwable t);

	public void debug(Throwable t);

	public void error(Object msg);

	public void error(Object msg, Throwable t);

	public void error(Throwable t);

	public void fatal(Object msg);

	public void fatal(Object msg, Throwable t);

	public void fatal(Throwable t);

	public void info(Object msg);

	public void info(Object msg, Throwable t);

	public void info(Throwable t);

	public boolean isDebugEnabled();

	public boolean isErrorEnabled();

	public boolean isFatalEnabled();

	public boolean isInfoEnabled();

	public boolean isTraceEnabled();

	public boolean isWarnEnabled();

	public void setLogWrapperClassName(String className);

	public void trace(Object msg);

	public void trace(Object msg, Throwable t);

	public void trace(Throwable t);

	public void warn(Object msg);

	public void warn(Object msg, Throwable t);

	public void warn(Throwable t);

}