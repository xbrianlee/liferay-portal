import ClayEmptyState from 'components/shared/ClayEmptyState';
import React from 'react';
import {cleanup, render} from '@testing-library/react';

describe('ClayEmptyState', () => {
	it('renders', () => {
		const {asFragment} = render(<ClayEmptyState />);

		expect(asFragment()).toMatchSnapshot();
	});

	it('displays a custom title', () => {
		const {getByText} = render(<ClayEmptyState title='Test Title' />);

		expect(getByText('Test Title')).toBeInTheDocument();
	});

	it('displays a custom description', () => {
		const {getByText} = render(
			<ClayEmptyState description='Test Description' />
		);

		expect(getByText('Test Description')).toBeInTheDocument();
	});
});
