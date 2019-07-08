import React from 'react';
import SearchBar from 'components/list/SearchBar';
import {cleanup, fireEvent, render} from '@testing-library/react';
import {
	FETCH_VISIBLE_DOCUMENTS_URL,
	getMockResultsData
} from 'test/mock-data.js';
import {resultsDataToMap} from 'utils/util';

jest.mock('utils/api');

const DATA_MAP = resultsDataToMap(
	getMockResultsData(10, 0, '', false).documents
);

const DROPDOWN_TOGGLE_ID = 'dropdown-toggle';

describe('SearchBar', () => {
	it('has an add result button when onAddResultSubmit is defined', () => {
		const {queryByText} = render(
			<SearchBar
				dataMap={DATA_MAP}
				fetchDocumentsUrl={FETCH_VISIBLE_DOCUMENTS_URL}
				onAddResultSubmit={jest.fn()}
				onClickHide={jest.fn()}
				onClickPin={jest.fn()}
				onSelectAll={jest.fn()}
				onSelectClear={jest.fn()}
				resultIds={[102, 104, 103]}
				selectedIds={[]}
			/>
		);

		expect(queryByText('Add a Result')).not.toBeNull();
	});

	it('does not have an add result button when onAddResultSubmit is not defined', () => {
		const {queryByText} = render(
			<SearchBar
				dataMap={DATA_MAP}
				fetchDocumentsUrl={FETCH_VISIBLE_DOCUMENTS_URL}
				onClickHide={jest.fn()}
				onClickPin={jest.fn()}
				onSelectAll={jest.fn()}
				onSelectClear={jest.fn()}
				resultIds={[102, 104, 103]}
				searchBarTerm={'test'}
				selectedIds={[]}
			/>
		);

		expect(queryByText('Add a Result')).toBeNull();
	});

	it('shows what is selected using selectedIds', () => {
		const {queryByText} = render(
			<SearchBar
				dataMap={DATA_MAP}
				fetchDocumentsUrl={FETCH_VISIBLE_DOCUMENTS_URL}
				onAddResultSubmit={jest.fn()}
				onClickHide={jest.fn()}
				onClickPin={jest.fn()}
				onSelectAll={jest.fn()}
				onSelectClear={jest.fn()}
				resultIds={[102, 104, 103]}
				selectedIds={[102, 103]}
			/>
		);

		expect(queryByText('2 of 3 Items Selected')).not.toBeNull();
		expect(queryByText('Add a Result')).toBeNull();
	});

	it('shows the dropdown when clicked on', () => {
		const {container, getByTestId} = render(
			<SearchBar
				dataMap={DATA_MAP}
				fetchDocumentsUrl={FETCH_VISIBLE_DOCUMENTS_URL}
				onAddResultSubmit={jest.fn()}
				onClickHide={jest.fn()}
				onClickPin={jest.fn()}
				onSelectAll={jest.fn()}
				onSelectClear={jest.fn()}
				resultIds={[102, 104, 103]}
				selectedIds={[102, 103]}
			/>
		);

		fireEvent.click(getByTestId(DROPDOWN_TOGGLE_ID));

		expect(container.querySelector('.dropdown-menu')).toHaveClass('show');
	});

	it('shows no items selected with empty selectedIds', () => {
		const {queryByText} = render(
			<SearchBar
				dataMap={DATA_MAP}
				fetchDocumentsUrl={FETCH_VISIBLE_DOCUMENTS_URL}
				onAddResultSubmit={jest.fn()}
				onClickHide={jest.fn()}
				onClickPin={jest.fn()}
				onSelectAll={jest.fn()}
				onSelectClear={jest.fn()}
				resultIds={[102, 104, 103]}
				selectedIds={[]}
			/>
		);

		expect(queryByText('Items Selected')).toBeNull();
		expect(queryByText('Add a Result')).not.toBeNull();
	});
});
