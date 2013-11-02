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

package com.liferay.portal.kernel.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Brian Wing Shun Chan
 */
public class DocumentComparator implements Comparator<Document> {

	public DocumentComparator() {
		this(true, false);
	}

	public DocumentComparator(boolean ascending, boolean caseSensitive) {
		_ascending = ascending;
		_caseSensitive = caseSensitive;
	}

	public void addOrderBy(String name) {
		addOrderBy(name, _ascending, _caseSensitive);
	}

	public void addOrderBy(String name, boolean ascending) {
		addOrderBy(name, ascending, _caseSensitive);
	}

	public void addOrderBy(
		String name, boolean ascending, boolean caseSensitive) {

		DocumentComparatorOrderBy orderBy = new DocumentComparatorOrderBy(
			name, ascending, caseSensitive);

		_columns.add(orderBy);
	}

	@Override
	public int compare(Document doc1, Document doc2) {
		for (DocumentComparatorOrderBy orderBy : _columns) {
			String value1 = doc1.get(orderBy.getName());
			String value2 = doc2.get(orderBy.getName());

			if (!orderBy.isAsc()) {
				String temp = value1;

				value1 = value2;
				value2 = temp;
			}

			int result = 0;

			if ((value1 != null) && (value2 != null)) {
				if (orderBy.isCaseSensitive()) {
					result = value1.compareTo(value2);
				}
				else {
					result = value1.compareToIgnoreCase(value2);
				}
			}

			if (result != 0) {
				return result;
			}
		}

		return 0;
	}

	private boolean _ascending;
	private boolean _caseSensitive;
	private List<DocumentComparatorOrderBy> _columns =
		new ArrayList<DocumentComparatorOrderBy>();

}