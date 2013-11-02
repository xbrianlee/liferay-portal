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

package com.liferay.portlet.messageboards.model;

import java.util.List;

/**
 * @author Shuyang Zhou
 */
public interface MBCategoryDisplay {

	public List<MBCategory> getAllCategories();

	public int getAllCategoriesCount();

	public List<MBCategory> getCategories();

	public List<MBCategory> getCategories(MBCategory category);

	public MBCategory getRootCategory();

	public int getSubcategoriesCount(MBCategory category);

	public int getSubcategoriesMessagesCount(MBCategory category);

	public int getSubcategoriesThreadsCount(MBCategory category);

	public void getSubcategoryIds(MBCategory category, List<Long> categoryIds);

}