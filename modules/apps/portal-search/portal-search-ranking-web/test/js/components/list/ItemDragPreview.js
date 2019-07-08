import ItemDragPreview from 'components/list/ItemDragPreview';
import React from 'react';
import {cleanup, render} from '@testing-library/react';
import {mockDocument} from 'test/mock-data';

// Mock pinned document since only pinned results can be dragged.

const MOCK_DOCUMENT = mockDocument(1, {pinned: true});

/**
 * Tests a text string if the value is displayed in the component.
 * @param {string} text The text to test.
 */
function testText(text) {
	const {getByText} = render(<ItemDragPreview {...MOCK_DOCUMENT} />);

	expect(getByText(text, {exact: false})).toBeInTheDocument();
}

describe('ItemDragPreview', () => {
	it('displays the title', () => {
		testText(MOCK_DOCUMENT.title);
	});

	it('displays the description', () => {
		testText(MOCK_DOCUMENT.description);
	});

	it('displays the author', () => {
		testText(MOCK_DOCUMENT.author);
	});

	it('displays the clicks', () => {
		testText(`${MOCK_DOCUMENT.clicks}`);
	});

	it('displays the date', () => {
		testText(`${MOCK_DOCUMENT.date}`);
	});

	it('displays the drag handle', () => {
		const {getByTestId} = render(<ItemDragPreview {...MOCK_DOCUMENT} />);

		expect(getByTestId('DRAG_ICON')).toBeVisible();
	});
});
