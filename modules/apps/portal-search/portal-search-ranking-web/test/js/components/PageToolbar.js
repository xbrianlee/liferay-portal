import React from 'react';
import PageToolbar from 'components/PageToolbar';
import {cleanup, render} from '@testing-library/react';

describe('PageToolbar', () => {
	it('disables the publish button', () => {
		const {getByText} = render(
			<PageToolbar
				onCancel={'cancel'}
				onPublish={jest.fn()}
				submitDisabled={true}
			/>
		);

		expect(getByText('Publish')).toHaveAttribute('disabled');
	});

	it('enables the publish button', () => {
		const {getByText} = render(
			<PageToolbar
				onCancel={'cancel'}
				onPublish={jest.fn()}
				submitDisabled={false}
			/>
		);

		expect(getByText('Publish')).not.toHaveAttribute('disabled');
	});
});
