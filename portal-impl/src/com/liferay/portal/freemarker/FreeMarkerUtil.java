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

package com.liferay.portal.freemarker;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.StringPool;

import freemarker.cache.ClassTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

import java.io.Writer;

/**
 * @author Tariq Dweik
 * @author Brian Wing Shun Chan
 */
public class FreeMarkerUtil {

	public static String process(String name, Object context) throws Exception {
		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		process(name, context, unsyncStringWriter);

		return unsyncStringWriter.toString();
	}

	public static void process(String name, Object context, Writer writer)
		throws Exception {

		Template template = _getConfiguration().getTemplate(name);

		template.process(context, writer);
	}

	private static Configuration _getConfiguration() {
		if (_configuration != null) {
			return _configuration;
		}

		_configuration = new Configuration();

		_configuration.setObjectWrapper(new DefaultObjectWrapper());
		_configuration.setTemplateLoader(
			new ClassTemplateLoader(FreeMarkerUtil.class, StringPool.SLASH));
		_configuration.setTemplateUpdateDelay(Integer.MAX_VALUE);

		return _configuration;
	}

	private static Configuration _configuration;

}