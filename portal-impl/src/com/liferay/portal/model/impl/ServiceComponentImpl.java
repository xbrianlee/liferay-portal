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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

/**
 * @author Brian Wing Shun Chan
 */
public class ServiceComponentImpl extends ServiceComponentBaseImpl {

	public ServiceComponentImpl() {
	}

	@Override
	public String getIndexesSQL() {
		return _getData("indexes-sql");
	}

	@Override
	public String getSequencesSQL() {
		return _getData("sequences-sql");
	}

	@Override
	public String getTablesSQL() {
		return _getData("tables-sql");
	}

	@Override
	public void setData(String data) {
		super.setData(data);

		_dataEl = null;
	}

	private String _getData(String name) {
		try {
			return _getDataEl().elementText(name);
		}
		catch (Exception e) {
			_log.error(e, e);

			return StringPool.BLANK;
		}
	}

	private Element _getDataEl() throws DocumentException {
		if (_dataEl == null) {
			Document doc = SAXReaderUtil.read(getData());

			_dataEl = doc.getRootElement();
		}

		return _dataEl;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceComponentImpl.class);

	private Element _dataEl;

}