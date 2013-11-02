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

import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.lucene.document.Field;

/**
 * @author Brian Wing Shun Chan
 */
public class LuceneFileExtractor {

	public Field getFile(String field, byte[] bytes, String fileExt) {
		InputStream is = new UnsyncByteArrayInputStream(bytes);

		return getFile(field, is, fileExt);
	}

	public Field getFile(String field, File file, String fileExt)
		throws IOException {

		InputStream is = new FileInputStream(file);

		return getFile(field, is, fileExt);
	}

	public Field getFile(String field, InputStream is, String fileExt) {
		String text = FileUtil.extractText(is, fileExt);

		if (Validator.isNotNull(
				PropsValues.LUCENE_FILE_EXTRACTOR_REGEXP_STRIP)) {

			text = regexpStrip(text);
		}

		return LuceneFields.getText(field, text);
	}

	protected String regexpStrip(String text) {
		char[] array = text.toCharArray();

		for (int i = 0; i < array.length; i++) {
			String s = String.valueOf(array[i]);

			if (!s.matches(PropsValues.LUCENE_FILE_EXTRACTOR_REGEXP_STRIP)) {
				array[i] = CharPool.SPACE;
			}
		}

		return new String(array);
	}

}