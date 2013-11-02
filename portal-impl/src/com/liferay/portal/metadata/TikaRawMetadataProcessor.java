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

package com.liferay.portal.metadata;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.io.DummyWriter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.process.ClassPathUtil;
import com.liferay.portal.kernel.process.ProcessCallable;
import com.liferay.portal.kernel.process.ProcessException;
import com.liferay.portal.kernel.process.ProcessExecutor;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.util.PropsValues;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.concurrent.Future;

import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.pdfbox.exceptions.CryptographyException;
import org.apache.poi.EncryptedDocumentException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.WriteOutContentHandler;

import org.xml.sax.ContentHandler;

/**
 * @author Miguel Pastor
 * @author Alexander Chow
 * @author Shuyang Zhou
 */
public class TikaRawMetadataProcessor extends XugglerRawMetadataProcessor {

	public void setParser(Parser parser) {
		_parser = parser;
	}

	protected static Metadata extractMetadata(
			InputStream inputStream, Metadata metadata, Parser parser)
		throws IOException {

		if (metadata == null) {
			metadata = new Metadata();
		}

		ParseContext parserContext = new ParseContext();

		parserContext.set(Parser.class, parser);

		ContentHandler contentHandler = new WriteOutContentHandler(
			new DummyWriter());

		try {
			parser.parse(inputStream, contentHandler, metadata, parserContext);
		}
		catch (Exception e) {
			Throwable throwable = ExceptionUtils.getRootCause(e);

			if ((throwable instanceof CryptographyException) ||
				(throwable instanceof EncryptedDocumentException) ||
				(throwable instanceof UnsupportedZipFeatureException)) {

				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to extract metadata from an encrypted file");
				}
			}
			else if (e instanceof TikaException) {
				if (_log.isWarnEnabled()) {
					_log.warn("Unable to extract metadata");
				}
			}
			else {
				_log.error(e, e);
			}

			throw new IOException(e.getMessage());
		}

		// Remove potential security risks

		metadata.remove(XMPDM.ABS_PEAK_AUDIO_FILE_PATH.getName());
		metadata.remove(XMPDM.RELATIVE_PEAK_AUDIO_FILE_PATH.getName());

		return metadata;
	}

	@Override
	protected Metadata extractMetadata(
			String extension, String mimeType, File file)
		throws SystemException {

		Metadata metadata = super.extractMetadata(extension, mimeType, file);

		boolean forkProcess = false;

		if (PropsValues.TEXT_EXTRACTION_FORK_PROCESS_ENABLED) {
			if (ArrayUtil.contains(
					PropsValues.TEXT_EXTRACTION_FORK_PROCESS_MIME_TYPES,
					mimeType)) {

				forkProcess = true;
			}
		}

		if (forkProcess) {
			ExtractMetadataProcessCallable extractMetadataProcessCallable =
				new ExtractMetadataProcessCallable(file, metadata, _parser);

			try {
				Future<Metadata> future = ProcessExecutor.execute(
					ClassPathUtil.getPortalClassPath(),
					extractMetadataProcessCallable);

				return future.get();
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
		}

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(file);

			return extractMetadata(inputStream, metadata, _parser);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}
	}

	@Override
	protected Metadata extractMetadata(
			String extension, String mimeType, InputStream inputStream)
		throws SystemException {

		Metadata metadata = super.extractMetadata(
			extension, mimeType, inputStream);

		boolean forkProcess = false;

		if (PropsValues.TEXT_EXTRACTION_FORK_PROCESS_ENABLED) {
			if (ArrayUtil.contains(
					PropsValues.TEXT_EXTRACTION_FORK_PROCESS_MIME_TYPES,
					mimeType)) {

				forkProcess = true;
			}
		}

		if (forkProcess) {
			File file = FileUtil.createTempFile();

			try {
				FileUtil.write(file, inputStream);

				ExtractMetadataProcessCallable extractMetadataProcessCallable =
					new ExtractMetadataProcessCallable(file, metadata, _parser);

				Future<Metadata> future = ProcessExecutor.execute(
					ClassPathUtil.getPortalClassPath(),
					extractMetadataProcessCallable);

				return future.get();
			}
			catch (Exception e) {
				throw new SystemException(e);
			}
			finally {
				file.delete();
			}
		}

		try {
			return extractMetadata(inputStream, metadata, _parser);
		}
		catch (IOException ioe) {
			throw new SystemException(ioe);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		TikaRawMetadataProcessor.class);

	private Parser _parser;

	private static class ExtractMetadataProcessCallable
		implements ProcessCallable<Metadata> {

		public ExtractMetadataProcessCallable(
			File file, Metadata metadata, Parser parser) {

			_file = file;
			_metadata = metadata;
			_parser = parser;
		}

		@Override
		public Metadata call() throws ProcessException {
			InputStream inputStream = null;

			try {
				inputStream = new FileInputStream(_file);

				return extractMetadata(inputStream, _metadata, _parser);
			}
			catch (IOException ioe) {
				throw new ProcessException(ioe);
			}
			finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					}
					catch (IOException ioe) {
						throw new ProcessException(ioe);
					}
				}
			}
		}

		private static final long serialVersionUID = 1L;

		private File _file;
		private Metadata _metadata;
		private Parser _parser;

	}

}