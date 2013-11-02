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

package com.liferay.portal.search.lucene;

import com.liferay.portal.kernel.search.BooleanClauseOccur;

/**
 * @author Brian Wing Shun Chan
 */
public class BooleanClauseOccurTranslator {

	public static org.apache.lucene.search.BooleanClause.Occur translate(
		BooleanClauseOccur occur) {

		if (occur.equals(BooleanClauseOccur.MUST)) {
			return org.apache.lucene.search.BooleanClause.Occur.MUST;
		}
		else if (occur.equals(BooleanClauseOccur.MUST_NOT)) {
			return org.apache.lucene.search.BooleanClause.Occur.MUST_NOT;
		}
		else if (occur.equals(BooleanClauseOccur.SHOULD)) {
			return org.apache.lucene.search.BooleanClause.Occur.SHOULD;
		}
		else {
			return null;
		}
	}

}