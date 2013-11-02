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

package com.liferay.portlet.journal.service;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.journal.model.JournalStructure;
import com.liferay.portlet.journal.util.JournalTestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Marcellus Tavares
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@SuppressWarnings("deprecation")
@Transactional
public class JournalStructureServiceTest extends BaseJournalServiceTestCase {

	@Override
	public void setUp() throws Exception {
		super.setUp();

		deleteStructures();
	}

	@Test
	public void testAddStrucuture() throws Exception {
		String structureId = generateId();

		JournalStructure structure = addStructure(
			groupId, structureId, getDefultXsd());

		Assert.assertEquals(structureId, structure.getStructureId());
	}

	@Test
	public void testCheckNewLine() throws Exception {
		String structureId = generateId();

		String xsd = getDefultXsd();

		xsd.concat("\\n\\n");

		addStructure(groupId, structureId, xsd);

		JournalStructureLocalServiceUtil.checkNewLine(groupId, structureId);

		JournalStructure structure =
			JournalStructureLocalServiceUtil.getStructure(groupId, structureId);

		Assert.assertFalse(structure.getXsd().contains("\\n"));
	}

	@Test
	public void testCopyStructure() throws Exception {
		String oldStructureId = generateId();

		String xsd = getDefultXsd();

		addStructure(groupId, oldStructureId, xsd);

		String newStructureId = generateId();

		JournalStructure structure =
			JournalStructureLocalServiceUtil.copyStructure(
				TestPropsValues.getUserId(), groupId, oldStructureId,
				newStructureId, false);

		Assert.assertEquals(newStructureId, structure.getStructureId());
		Assert.assertEquals(
			JournalTestUtil.getXsdMap(xsd),
			JournalTestUtil.getXsdMap(structure.getXsd()));
	}

	@Test
	public void testDeleteStructure() throws Exception {
		String structureId = generateId();

		JournalStructure structure = addStructure(
			groupId, structureId, getDefultXsd());

		JournalStructureLocalServiceUtil.deleteStructure(structure);

		boolean hasStructure = hasStructure(structureId, false);

		Assert.assertFalse(hasStructure);
	}

	@Test
	public void testDeleteStructures() throws Exception {
		addStructure(groupId, generateId(), getDefultXsd());
		addStructure(groupId, generateId(), getDefultXsd());

		int structuresCount =
			JournalStructureLocalServiceUtil.getStructuresCount(groupId);

		Assert.assertEquals(2, structuresCount);

		JournalStructureLocalServiceUtil.deleteStructures(groupId);

		structuresCount = JournalStructureLocalServiceUtil.getStructuresCount(
			groupId);

		Assert.assertEquals(0, structuresCount);
	}

	@Test
	public void testGetStructure() throws Exception {
		String structureId = generateId();

		addStructure(groupId, structureId, getDefultXsd());

		boolean hasStructure = hasStructure(structureId, false);

		Assert.assertTrue(hasStructure);
	}

	@Test
	public void testGetStructureGlobalIncluded() throws Exception {
		String structureId = generateId();

		addStructure(getCompanyGroupId(), structureId, getDefultXsd());

		boolean hasStructure = hasStructure(structureId, true);

		Assert.assertTrue(hasStructure);
	}

	@Test
	public void testGetStructures() throws Exception {
		addStructure(groupId, generateId(), getDefultXsd());
		addStructure(groupId, generateId(), getDefultXsd());

		List<JournalStructure> structures =
			JournalStructureLocalServiceUtil.getStructures(groupId);

		Assert.assertEquals(2, structures.size());
	}

	@Test
	public void testSearchByKeywords() throws Exception {
		addStructure(groupId, generateId(), "First Structure", getDefultXsd());
		addStructure(
			getCompanyGroupId(), generateId(), "Second Structure",
			getDefultXsd());

		long[] groupIds = new long[] {getCompanyGroupId(), groupId};

		List<JournalStructure> structures =
			JournalStructureLocalServiceUtil.search(
				companyId, groupIds, "Structure", QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);

		Assert.assertEquals(2, structures.size());
	}

	@Test
	public void testSearchCountByKeywords() throws Exception {
		addStructure(groupId, generateId(), "First Structure", getDefultXsd());
		addStructure(
			getCompanyGroupId(), generateId(), "Second Structure",
			getDefultXsd());

		long[] groupIds = new long[] {groupId};

		int structuresCount =
			JournalStructureLocalServiceUtil.searchCount(
				companyId, groupIds, "Structure");

		Assert.assertEquals(1, structuresCount);
	}

	@Test
	public void testUpdateTemplate() throws Exception {
		String structureId = generateId();

		addStructure(groupId, structureId, getDefultXsd());

		Map<Locale, String> nameMap = new HashMap<Locale, String>();

		nameMap.put(LocaleUtil.US, "New Test Structure");

		StringBundler sb = new StringBundler(5);

		sb.append("<root><dynamic-element name=\"abc\" index-type=\"\" ");
		sb.append("type=\"text\"><meta-data><entry name=\"label\">");
		sb.append("<![CDATA[abc]]></entry><entry name=\"required\">");
		sb.append("<![CDATA[false]]></entry></meta-data></dynamic-element>");
		sb.append("</root>");

		String xsd = sb.toString();

		JournalStructure structure =
			JournalStructureLocalServiceUtil.updateStructure(
				groupId, structureId, null, nameMap, null, xsd,
				getServiceContext());

		Assert.assertEquals(
			"New Test Structure", structure.getName(LocaleUtil.US));
		Assert.assertEquals(
			JournalTestUtil.getXsdMap(xsd),
			JournalTestUtil.getXsdMap(structure.getXsd()));
	}

	protected void deleteStructures() throws Exception {
		JournalStructureLocalServiceUtil.deleteStructures(getCompanyGroupId());

		JournalStructureLocalServiceUtil.deleteStructures(groupId);
	}

	protected boolean hasStructure(
			String structureId, boolean includeGlobalStructures)
		throws Exception {

		try {
			JournalStructureLocalServiceUtil.getStructure(
				groupId, structureId, includeGlobalStructures);

			return true;
		}
		catch (PortalException pe) {
			return false;
		}
	}

}