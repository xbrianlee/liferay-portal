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

package com.liferay.portal.model;

import java.io.IOException;
import java.io.Serializable;

import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public interface LayoutTemplate
	extends Comparable<LayoutTemplate>, Plugin, Serializable {

	public List<String> getColumns();

	public String getContent();

	public String getContextPath();

	public String getLayoutTemplateId();

	public String getName();

	public String getServletContextName();

	public boolean getStandard();

	public String getStaticResourcePath();

	public String getTemplatePath();

	public String getThemeId();

	public String getThumbnailPath();

	public String getUncachedContent() throws IOException;

	public String getUncachedWapContent() throws IOException;

	public String getWapContent();

	public String getWapTemplatePath();

	public boolean getWARFile();

	public boolean hasSetContent();

	public boolean hasSetWapContent();

	public boolean isStandard();

	public boolean isWARFile();

	public void setColumns(List<String> columns);

	public void setContent(String content);

	public void setName(String name);

	public void setServletContext(ServletContext servletContext);

	public void setServletContextName(String servletContextName);

	public void setStandard(boolean standard);

	public void setTemplatePath(String templatePath);

	public void setThemeId(String themeId);

	public void setThumbnailPath(String thumbnailPath);

	public void setWapContent(String wapContent);

	public void setWapTemplatePath(String wapWapTemplatePath);

}