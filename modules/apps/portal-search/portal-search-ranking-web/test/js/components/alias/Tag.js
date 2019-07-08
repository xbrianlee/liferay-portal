import React from 'react';
import Tag from 'components/alias/Tag';
import {cleanup, fireEvent, render} from '@testing-library/react';

describe('Tag', () => {
	it('has a corresponding label', () => {
		const {container} = render(
			<Tag label='one' onClickDelete={jest.fn()} />
		);

		const tag = container.querySelector('.label-item-expand');

		expect(tag).toHaveTextContent('one');
	});

	it('calls the onClickDelete function when it gets clicked on', () => {
		const onClickDelete = jest.fn();

		const {container} = render(
			<Tag label='one' onClickDelete={onClickDelete} />
		);

		fireEvent.click(container.querySelector('button.close'));

		expect(onClickDelete.mock.calls.length).toBe(1);
	});
});
