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

package com.liferay.portal.lar;

import com.liferay.portal.kernel.lar.ExportImportHelperUtil;
import com.liferay.portal.kernel.lar.ManifestSummary;
import com.liferay.portal.kernel.lar.PortletDataContext;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.test.Sync;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.journal.lar.JournalArticleStagedModelDataHandlerTest;
import com.liferay.portlet.journal.model.JournalArticle;
import com.liferay.portlet.journal.model.JournalFolder;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

/**
 * @author Zsolt Berentey
 */
@Sync
public class ManifestSummaryTest
	extends JournalArticleStagedModelDataHandlerTest {

	@Override
	protected void validateExport(
			PortletDataContext portletDataContext, StagedModel stagedModel,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		ManifestSummary manifestSummary =
			portletDataContext.getManifestSummary();

		Map<String, LongWrapper> modelAdditionCounters =
			manifestSummary.getModelAdditionCounters();

		Assert.assertEquals(4, modelAdditionCounters.size());
		Assert.assertEquals(
			1,
			manifestSummary.getModelAdditionCount(
				DDMStructure.class, JournalArticle.class));
		Assert.assertEquals(
			1,
			manifestSummary.getModelAdditionCount(
				DDMTemplate.class, DDMStructure.class));
		Assert.assertEquals(
			1, manifestSummary.getModelAdditionCount(JournalArticle.class));
		Assert.assertEquals(
			1, manifestSummary.getModelAdditionCount(JournalFolder.class));

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		Element headerElement = rootElement.addElement("header");

		_exportDateString = Time.getRFC822();

		headerElement.addAttribute("export-date", _exportDateString);

		ExportImportHelperUtil.writeManifestSummary(document, manifestSummary);

		zipWriter.addEntry("/manifest.xml", document.asXML());
	}

	@Override
	protected void validateImport(
			StagedModel stagedModel, StagedModelAssets stagedModelAssets,
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		ManifestSummary manifestSummary =
			ExportImportHelperUtil.getManifestSummary(
				TestPropsValues.getUserId(), liveGroup.getGroupId(),
				getParameterMap(), zipWriter.getFile());

		Map<String, LongWrapper> modelAdditionCounters =
			manifestSummary.getModelAdditionCounters();

		Assert.assertEquals(4, modelAdditionCounters.size());
		Assert.assertEquals(
			1,
			manifestSummary.getModelAdditionCount(
				DDMStructure.class, JournalArticle.class));
		Assert.assertEquals(
			1,
			manifestSummary.getModelAdditionCount(
				DDMTemplate.class, DDMStructure.class));
		Assert.assertEquals(
			1, manifestSummary.getModelAdditionCount(JournalArticle.class));
		Assert.assertEquals(
			1, manifestSummary.getModelAdditionCount(JournalFolder.class));

		String exportedDateString = Time.getRFC822(
			manifestSummary.getExportDate());

		Assert.assertEquals(_exportDateString, exportedDateString);
	}

	private String _exportDateString;

}