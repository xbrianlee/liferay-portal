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

package com.liferay.portal.kernel.image;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;

/**
 * @author Alexander Chow
 * @author Ivica Cardic
 */
public interface ImageMagick {

	public Future<?> convert(List<String> arguments) throws Exception;

	public void destroy();

	public String getGlobalSearchPath() throws Exception;

	public Properties getResourceLimitsProperties() throws Exception;

	public String[] identify(List<String> arguments)
		throws Exception;

	public boolean isEnabled();

	public void reset();

}