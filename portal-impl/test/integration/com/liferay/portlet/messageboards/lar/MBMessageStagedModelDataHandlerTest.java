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

package com.liferay.portlet.messageboards.lar;

import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.test.ExecutionTestListeners;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.lar.BaseWorkflowedStagedModelDataHandlerTestCase;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Repository;
import com.liferay.portal.model.StagedModel;
import com.liferay.portal.service.persistence.RepositoryUtil;
import com.liferay.portal.test.LiferayIntegrationJUnitTestRunner;
import com.liferay.portal.test.MainServletExecutionTestListener;
import com.liferay.portal.test.TransactionalExecutionTestListener;
import com.liferay.portlet.messageboards.model.MBCategory;
import com.liferay.portlet.messageboards.model.MBMessage;
import com.liferay.portlet.messageboards.service.MBCategoryLocalServiceUtil;
import com.liferay.portlet.messageboards.service.MBMessageLocalServiceUtil;
import com.liferay.portlet.messageboards.util.MBTestUtil;

import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.runner.RunWith;

/**
 * @author Daniel Kocsis
 */
@ExecutionTestListeners(
	listeners = {
		MainServletExecutionTestListener.class,
		TransactionalExecutionTestListener.class
	})
@RunWith(LiferayIntegrationJUnitTestRunner.class)
public class MBMessageStagedModelDataHandlerTest
	extends BaseWorkflowedStagedModelDataHandlerTestCase {

	@Override
	protected Map<String, List<StagedModel>> addDependentStagedModelsMap(
			Group group)
		throws Exception {

		Map<String, List<StagedModel>> dependentStagedModelsMap =
			new HashMap<String, List<StagedModel>>();

		MBCategory category = MBTestUtil.addCategory(group.getGroupId());

		addDependentStagedModel(
			dependentStagedModelsMap, MBCategory.class, category);

		return dependentStagedModelsMap;
	}

	@Override
	protected StagedModel addStagedModel(
			Group group,
			Map<String, List<StagedModel>> dependentStagedModelsMap)
		throws Exception {

		List<StagedModel> dependentStagedModels = dependentStagedModelsMap.get(
			MBCategory.class.getSimpleName());

		MBCategory category = (MBCategory)dependentStagedModels.get(0);

		List<ObjectValuePair<String, InputStream>> objectValuePairs =
			MBTestUtil.getInputStreamOVPs(
				"attachment.txt", getClass(), StringPool.BLANK);

		MBMessage message = MBTestUtil.addMessageWithWorkflowAndAttachments(
			group.getGroupId(), category.getCategoryId(), true,
			objectValuePairs);

		List<FileEntry> attachmentsFileEntries =
			message.getAttachmentsFileEntries();

		FileEntry fileEntry = attachmentsFileEntries.get(0);

		Folder folder = fileEntry.getFolder();

		while (folder != null) {
			addDependentStagedModel(
				dependentStagedModelsMap, Folder.class, folder);

			folder = folder.getParentFolder();
		}

		addDependentStagedModel(
			dependentStagedModelsMap, FileEntry.class,
			attachmentsFileEntries.get(0));

		Repository repository = RepositoryUtil.fetchByPrimaryKey(
			fileEntry.getRepositoryId());

		addDependentStagedModel(
			dependentStagedModelsMap, Repository.class, repository);

		return message;
	}

	@Override
	protected List<StagedModel> addWorkflowedStagedModels(Group group)
		throws Exception {

		List<StagedModel> stagedModels = new ArrayList<StagedModel>();

		stagedModels.add(
			MBTestUtil.addMessageWithWorkflow(group.getGroupId(), true));

		stagedModels.add(
			MBTestUtil.addMessageWithWorkflow(group.getGroupId(), false));

		return stagedModels;
	}

	@Override
	protected StagedModel getStagedModel(String uuid, Group group) {
		try {
			return MBMessageLocalServiceUtil.getMBMessageByUuidAndGroupId(
				uuid, group.getGroupId());
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Class<? extends StagedModel> getStagedModelClass() {
		return MBMessage.class;
	}

	@Override
	protected void validateImport(
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		List<StagedModel> dependentStagedModels = dependentStagedModelsMap.get(
			MBCategory.class.getSimpleName());

		Assert.assertEquals(1, dependentStagedModels.size());

		MBCategory category = (MBCategory)dependentStagedModels.get(0);

		MBCategoryLocalServiceUtil.getMBCategoryByUuidAndGroupId(
			category.getUuid(), group.getGroupId());
	}

	@Override
	protected void validateImport(
			StagedModel stagedModel, StagedModelAssets stagedModelAssets,
			Map<String, List<StagedModel>> dependentStagedModelsMap,
			Group group)
		throws Exception {

		super.validateImport(
			stagedModel, stagedModelAssets, dependentStagedModelsMap, group);

		MBMessage importedMessage = (MBMessage)getStagedModel(
			stagedModel.getUuid(), group);

		Assert.assertEquals(
			1, importedMessage.getAttachmentsFileEntriesCount());
	}

}