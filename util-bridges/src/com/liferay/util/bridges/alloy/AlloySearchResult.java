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

package com.liferay.util.bridges.alloy;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletURL;

/**
 * @author Brian Wing Shun Chan
 */
public class AlloySearchResult {

	public List<BaseModel<?>> getBaseModels() throws Exception {
		if (baseModels != null) {
			return baseModels;
		}

		List<BaseModel<?>> baseModels = new ArrayList<BaseModel<?>>();

		Document[] documents = hits.getDocs();

		for (int i = 0; i < documents.length; i++) {
			Document document = hits.doc(i);

			long entryClassPK = GetterUtil.getLong(
				document.get(Field.ENTRY_CLASS_PK));

			BaseModel<?> baseModel = alloyServiceInvoker.fetchModel(
				entryClassPK);

			if (baseModel == null) {
				continue;
			}

			baseModels.add(baseModel);
		}

		this.baseModels = baseModels;

		return baseModels;
	}

	public Hits getHits() {
		return hits;
	}

	public PortletURL getPortletURL() {
		return portletURL;
	}

	public int getSize() {
		return size;
	}

	protected void afterPropertiesSet() {
		size = hits.getLength();
	}

	protected void setAlloyServiceInvoker(
		AlloyServiceInvoker alloyServiceInvoker) {

		this.alloyServiceInvoker = alloyServiceInvoker;
	}

	protected void setHits(Hits hits) {
		this.hits = hits;
	}

	protected void setPortletURL(PortletURL portletURL) {
		this.portletURL = portletURL;
	}

	protected AlloyServiceInvoker alloyServiceInvoker;
	protected List<BaseModel<?>> baseModels;
	protected Hits hits;
	protected PortletURL portletURL;
	protected int size;

}