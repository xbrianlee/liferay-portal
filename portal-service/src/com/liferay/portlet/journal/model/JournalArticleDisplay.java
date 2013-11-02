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

package com.liferay.portlet.journal.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 * @author Raymond Augé
 */
public interface JournalArticleDisplay extends Serializable {

	public String getArticleId();

	public String[] getAvailableLocales();

	public long getCompanyId();

	public String getContent();

	public int getCurrentPage();

	public String getDDMStructureKey();

	public String getDDMTemplateKey();

	public String getDescription();

	public long getGroupId();

	public long getId();

	public int getNumberOfPages();

	public long getResourcePrimKey();

	public long getSmallImageId();

	public String getSmallImageURL();

	public String getTitle();

	public String getType();

	public String getUrlTitle();

	public long getUserId();

	public double getVersion();

	public boolean isCacheable();

	public boolean isPaginate();

	public boolean isSmallImage();

	public void setCacheable(boolean cacheable);

	public void setContent(String content);

	public void setCurrentPage(int currentPage);

	public void setDDMStructureKey(String ddmStructureKey);

	public void setDDMTemplateKey(String ddmTemplateKey);

	public void setNumberOfPages(int numberOfPages);

	public void setPaginate(boolean paginate);

	public void setSmallImage(boolean smallImage);

	public void setSmallImageId(long smallImageId);

	public void setSmallImageURL(String smallImageURL);

}