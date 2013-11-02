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

package com.liferay.portlet.dynamicdatamapping.service;

import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.service.ServiceTestUtil;
import com.liferay.portal.test.EnvironmentExecutionTestListener;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.TestPropsValues;
import com.liferay.portlet.dynamicdatalists.model.DDLRecord;
import com.liferay.portlet.dynamicdatamapping.RequiredStructureException;
import com.liferay.portlet.dynamicdatamapping.StructureDuplicateElementException;
import com.liferay.portlet.dynamicdatamapping.StructureDuplicateStructureKeyException;
import com.liferay.portlet.dynamicdatamapping.StructureNameException;
import com.liferay.portlet.dynamicdatamapping.StructureXsdException;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructure;
import com.liferay.portlet.dynamicdatamapping.model.DDMStructureConstants;
import com.liferay.portlet.dynamicdatamapping.model.DDMTemplate;
import com.liferay.portlet.dynamicdatamapping.storage.StorageType;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Eduardo Garcia
 */
@ExecutionTestListeners(
	listeners = {
		EnvironmentExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
@Transactional
public class DDMStructureServiceTest extends BaseDDMServiceTestCase {

	@Test
	public void testAddStructureWithDuplicateKey() throws Exception {
		String structureKey = ServiceTestUtil.randomString();
		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, structureKey, "Test Structure 1",
				getTestStructureXsd(storageType), storageType,
				DDMStructureConstants.TYPE_DEFAULT);

			addStructure(
				_classNameId, structureKey, "Test Structure 2",
				getTestStructureXsd(storageType), storageType,
				DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureDuplicateStructureKeyException sdske) {
		}
	}

	@Test
	public void testAddStructureWithoutName() throws Exception {
		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, StringPool.BLANK,
				getTestStructureXsd(storageType), storageType,
				DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureNameException sne) {
		}
	}

	@Test
	public void testAddStructureWithoutXsd() throws Exception {
		try {
			addStructure(
				_classNameId, null, "Test Structure", StringPool.BLANK,
				StorageType.XML.getValue(), DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureXsdException sxe) {
		}
	}

	@Test
	public void testAddStructureWithXsdContainingDuplicateElementName()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, "Test Structure",
				readText("ddm-structure-duplicate-element-name.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureDuplicateElementException sdee) {
		}
	}

	@Test
	public void testAddStructureWithXsdContainingInvalidElementAttribute()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, "Test Structure",
				readText("ddm-structure-invalid-element-attribute.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureXsdException sxe) {
		}
	}

	@Test
	public void testAddStructureWithXsdMissingRequiredElementAttribute()
		throws Exception {

		String storageType = StorageType.XML.getValue();

		try {
			addStructure(
				_classNameId, null, "Test Structure",
				readText("ddm-structure-required-element-attribute.xsd"),
				storageType, DDMStructureConstants.TYPE_DEFAULT);

			Assert.fail();
		}
		catch (StructureXsdException sxe) {
		}
	}

	@Test
	public void testCopyStructure() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		DDMStructure copyStructure = copyStructure(structure);

		Assert.assertEquals(structure.getGroupId(), copyStructure.getGroupId());
		Assert.assertEquals(structure.getXsd(), copyStructure.getXsd());
		Assert.assertEquals(
			structure.getStorageType(), copyStructure.getStorageType());
		Assert.assertEquals(structure.getType(), copyStructure.getType());
	}

	@Test
	public void testDeleteStructure() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		DDMStructureLocalServiceUtil.deleteStructure(
			structure.getStructureId());

		Assert.assertNull(
			DDMStructureLocalServiceUtil.fetchDDMStructure(
				structure.getStructureId()));
	}

	@Test
	public void testDeleteStructureReferencedByTemplates() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		addDisplayTemplate(structure.getPrimaryKey(), "Test Display Template");
		addFormTemplate(structure.getPrimaryKey(), "Test Form Template");

		try {
			DDMStructureLocalServiceUtil.deleteStructure(
				structure.getStructureId());

			Assert.fail();
		}
		catch (RequiredStructureException rse) {
		}
	}

	@Test
	public void testFetchStructure() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		Assert.assertNotNull(
			DDMStructureLocalServiceUtil.fetchStructure(
				structure.getGroupId(), _classNameId,
				structure.getStructureKey()));
	}

	@Test
	public void testGetStructures() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		List<DDMStructure> structures =
			DDMStructureLocalServiceUtil.getStructures(structure.getGroupId());

		Assert.assertTrue(structures.contains(structure));
	}

	@Test
	public void testGetTemplates() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure");

		addDisplayTemplate(structure.getStructureId(), "Test Display Template");
		addFormTemplate(structure.getStructureId(), "Test Form Template");

		List<DDMTemplate> templates = structure.getTemplates();

		Assert.assertEquals(2, templates.size());
	}

	@Test
	public void testSearch() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure 1");

		addStructure(_classNameId, "Test Structure 2");

		List<DDMStructure> structures = DDMStructureLocalServiceUtil.search(
			structure.getCompanyId(), new long[] {structure.getGroupId()},
			new long[] {structure.getClassNameId()}, null, null,
			structure.getStorageType(), structure.getType(), false, 0, 1, null);

		Assert.assertEquals(1, structures.size());
	}

	@Test
	public void testSearchByKeywords() throws Exception {
		DDMStructure structure = addStructure(_classNameId, "Test Structure 1");

		addStructure(_classNameId, "Test Structure 2");

		List<DDMStructure> structures = DDMStructureLocalServiceUtil.search(
			structure.getCompanyId(), new long[] {structure.getGroupId()},
			new long[] {structure.getClassNameId()}, null, 0, 1, null);

		Assert.assertEquals(1, structures.size());
	}

	@Test
	public void testSearchCount() throws Exception {
		int initialCount = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, "Test Structure", null, null,
			DDMStructureConstants.TYPE_DEFAULT, false);

		addStructure(_classNameId, "Test Structure");

		int count = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, "Test Structure", null, null,
			DDMStructureConstants.TYPE_DEFAULT, false);

		Assert.assertEquals(initialCount + 1, count);
	}

	@Test
	public void testSearchCountByKeywords() throws Exception {
		int initialCount = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, null);

		addStructure(_classNameId, "Test Structure");

		int count = DDMStructureLocalServiceUtil.searchCount(
			TestPropsValues.getCompanyId(), new long[] {group.getGroupId()},
			new long[] {_classNameId}, null);

		Assert.assertEquals(initialCount + 1, count);
	}

	protected DDMStructure copyStructure(DDMStructure structure)
		throws Exception {

		return DDMStructureLocalServiceUtil.copyStructure(
			structure.getUserId(), structure.getStructureId(),
			structure.getNameMap(), structure.getDescriptionMap(),
			ServiceTestUtil.getServiceContext(group.getGroupId()));
	}

	protected DDMStructure updateStructure(DDMStructure structure)
		throws Exception {

		return DDMStructureLocalServiceUtil.updateStructure(
			structure.getStructureId(), structure.getParentStructureId(),
			structure.getNameMap(), structure.getDescriptionMap(),
			structure.getXsd(),
			ServiceTestUtil.getServiceContext(group.getGroupId()));
	}

	private long _classNameId = PortalUtil.getClassNameId(DDLRecord.class);

}